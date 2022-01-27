package drones.order.master;

import admin.entities.DroneEntity;
import com.progetto.grpc.OrderServiceGrpc;
import com.progetto.grpc.OrderServiceGrpc.OrderServiceBlockingStub;
import com.progetto.grpc.OrderServiceOuterClass.OrderRequest;
import com.progetto.grpc.OrderServiceOuterClass.OrderResponse;
import drones.DroneSingleton;
import drones.eventbus.EventBus;
import drones.eventbus.messages.NewOrderMessage;
import drones.eventbus.messages.SendOrderMessage;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.concurrent.TimeUnit;

public class OrderClient extends Thread {
    private final SendOrderMessage sendOrderMessage;

    public OrderClient(SendOrderMessage sendOrderMessage) {
        this.sendOrderMessage = sendOrderMessage;
    }

    @Override
    public void run() {
        System.out.println("Order OrderClient start with order: " + sendOrderMessage.order.getId());

        ManagedChannel channel = null;
        int failCount = 0;
        for (DroneEntity droneEntity : sendOrderMessage.orderedList) {
            channel = ManagedChannelBuilder.forTarget("localhost:" + droneEntity.getPort()).usePlaintext().build();
            OrderServiceBlockingStub stub = OrderServiceGrpc.newBlockingStub(channel);
            OrderRequest orderRequest = OrderRequest.newBuilder()
                    .setOrder(sendOrderMessage.order.toOrder()).build();
            try {
                DroneSingleton.getInstance().setDelivering(droneEntity.getId(), true);
                OrderResponse orderResponse = stub.withDeadlineAfter(10000, TimeUnit.MILLISECONDS).makeDelivery(orderRequest);

                System.out.println("Order OrderClient reply: " + orderResponse.getArrivalTimestamp());

                // TODO: do smth with answer

                DroneSingleton.getInstance().setDelivering(droneEntity.getId(), false);
            } catch (StatusRuntimeException e) {
                System.out.println("Order OrderClient ERROR ORDERING DELIVERY " + orderRequest.getOrder().getId() + " TO " + droneEntity.getId() + ". Moving to next in ordered list.");
                channel.shutdown();
                DroneSingleton.getInstance().setDelivering(droneEntity.getId(), false);
                failCount += 1;
                continue;
            }
            System.out.println("Order OrderClient SUCCESSFULLY ORDERED DELIVERY " + orderRequest.getOrder().getId() + " TO " + droneEntity.getId());
            channel.shutdown();
            break;
        }

        if (channel != null && !channel.isShutdown()) {
            channel.shutdown();
        }

        if (failCount == sendOrderMessage.orderedList.size()) {
            System.out.println("Order OrderClient no one answered for delivery. Adding order to queue.");
            OrderQueue.getInstance().put(sendOrderMessage.order);
        }

        if (OrderQueue.getInstance().size() >= 1) {
            System.out.println("Order OrderClient done ordering delivery of " + sendOrderMessage.order.getId() + ". There's something in queue. New order");
            EventBus.getInstance().put(new NewOrderMessage());
        }

    }
}
