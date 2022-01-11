package drones.insertion;

import admin.entities.DroneEntity;
import com.progetto.grpc.InsertionServiceGrpc;
import com.progetto.grpc.InsertionServiceOuterClass.InsertionRequest;
import com.progetto.grpc.InsertionServiceOuterClass.InsertionResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;

public class InsertionClient extends Thread {
    private final int ownId;
    private final DroneEntity target;

    InsertionClient(int ownId, DroneEntity target) {
        this.ownId = ownId;
        this.target = target;
    }

    @Override
    public void run() {
        System.out.println("InsertionClient started");
        final ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:" + target.getPort()).usePlaintext().build();
        InsertionServiceGrpc.InsertionServiceBlockingStub stub = InsertionServiceGrpc.newBlockingStub(channel);
        InsertionRequest insertionRequest = InsertionRequest.newBuilder()
                .setId(ownId)
                .build();
        InsertionResponse insertionResponse = stub.withDeadlineAfter(5000, TimeUnit.MILLISECONDS).insert(insertionRequest);
        System.out.println("InsertionClient response da: " + insertionResponse.getId());
        channel.shutdown();
        System.out.println("InsertionClient ended");
    }
}
