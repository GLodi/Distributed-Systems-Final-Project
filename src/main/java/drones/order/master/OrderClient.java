package drones.order.master;

import admin.entities.DroneEntity;
import com.progetto.grpc.ElectionServiceGrpc;
import drones.eventbus.messages.SendOrderMessage;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

public class OrderClient extends Thread {
    private final SendOrderMessage sendOrderMessage;

    public OrderClient(SendOrderMessage sendOrderMessage) {
        this.sendOrderMessage = sendOrderMessage;
    }

    @Override
    public void run() {
        // come per election, trova il primo nella lista ordinata che ti risponde
        System.out.println("Order OrderClient start with order: " + sendOrderMessage.order.getId());

        ManagedChannel channel = null;
        int failCount = 0;
        for (DroneEntity droneEntity : sendOrderMessage.orderedList) {
            channel = ManagedChannelBuilder.forTarget("localhost:" + droneEntity.getPort()).usePlaintext().build();
            ElectionServiceGrpc.ElectionServiceBlockingStub stub = ElectionServiceGrpc.newBlockingStub(channel);
            //ElectionServiceOuterClass.ElectionRequest electionRequest = ElectionServiceOuterClass.ElectionRequest.newBuilder().setId(ownId).setElectionId(electionId).setBattery(battery).build();
            try {
                //ElectionServiceOuterClass.ElectionResponse electionResponse = stub.withDeadlineAfter(5000, TimeUnit.MILLISECONDS).forwardElection(electionRequest);
            } catch (StatusRuntimeException e) {
                //System.out.println("ElectionElectionClient ERROR FORWARDING " + electionRequest.getElectionId() + " TO " + droneEntity.getId() + ". Moving to next in ring.");
                channel.shutdown();
                failCount += 1;
                continue;
            }
            //System.out.println("ElectionElectionClient SUCCESSFULLY FORWARDED " + electionRequest.getElectionId() + " TO " + droneEntity.getId());
            channel.shutdown();
            break;
        }

    }
}
