package drones.election;

import admin.entities.DroneEntity;
import com.progetto.grpc.ElectionServiceGrpc;
import com.progetto.grpc.ElectionServiceGrpc.ElectionServiceBlockingStub;
import com.progetto.grpc.ElectionServiceOuterClass.ElectedRequest;
import com.progetto.grpc.ElectionServiceOuterClass.ElectedResponse;
import drones.DroneSingleton;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ElectionElectedClient extends Thread {
    private final int electedId;

    public ElectionElectedClient(int electedId) {
        this.electedId = electedId;
    }

    @Override
    public void run() {
        System.out.println("ElectionElectedClient started");
        List<DroneEntity> droneEntityList = new ArrayList<>(DroneSingleton.getInstance().getDroneList());

        for (DroneEntity droneEntity : droneEntityList) {
            final ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:" + droneEntity.getPort()).usePlaintext().build();
            ElectionServiceBlockingStub stub = ElectionServiceGrpc.newBlockingStub(channel);
            ElectedRequest electedRequest = ElectedRequest.newBuilder().setElectedId(electedId).build();
            try {
                ElectedResponse electedResponse = stub.withDeadlineAfter(5000, TimeUnit.MILLISECONDS).forwardElected(electedRequest);
            } catch (StatusRuntimeException e) {
                System.out.println("ElectionElectedClient ERROR FORWARDING TO " + droneEntity.getPort() + ". Moving to next in ring.");
                channel.shutdown();
                continue;
            }
            channel.shutdown();
            break;
        }

        // TODO: reset isParticipant to false
        System.out.println("ElectionElectedClient ended");
    }
}
