package drones.order.client;

import com.google.protobuf.Timestamp;
import com.progetto.grpc.OrderServiceGrpc.OrderServiceImplBase;
import com.progetto.grpc.OrderServiceOuterClass.OrderRequest;
import com.progetto.grpc.OrderServiceOuterClass.OrderResponse;
import drones.DroneSingleton;
import io.grpc.stub.StreamObserver;

import java.time.Instant;

public class OrderServiceImpl extends OrderServiceImplBase {

    @Override
    public void makeDelivery(OrderRequest request, StreamObserver<OrderResponse> responseObserver) {
        System.out.println("Order OrderServiceImpl received order " + request.getOrder().getId());
        // as client, simulate delivery with 5 sec sleep
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        double kmRun = distance(
                DroneSingleton.getInstance().getX(), DroneSingleton.getInstance().getY(),
                request.getOrder().getPickupX(), request.getOrder().getPickupY()) +
                distance(request.getOrder().getPickupX(), request.getOrder().getPickupY(),
                        request.getOrder().getDropX(), request.getOrder().getPickupY());

        DroneSingleton.getInstance().makeDelivery(request.getOrder().getDropX(), request.getOrder().getDropY());

        Instant time = Instant.now();
        // TODO: metti media misurazioni
        responseObserver.onNext(OrderResponse.newBuilder()
                .setArrivalTimestamp(Timestamp.newBuilder().setSeconds(time.getEpochSecond()).setNanos(time.getNano()).build())
                .setNewX(DroneSingleton.getInstance().getX())
                .setNewY(DroneSingleton.getInstance().getY())
                .setKmRun(kmRun)
                .build());

        if (DroneSingleton.getInstance().getBattery() <= 15) {
            DroneSingleton.getInstance().startClosingService();
        }
        responseObserver.onCompleted();
    }

    private double distance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
}
