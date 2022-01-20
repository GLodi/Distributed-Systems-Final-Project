package drones.election;

import admin.entities.DroneEntity;
import com.progetto.grpc.ElectionServiceGrpc;
import com.progetto.grpc.ElectionServiceGrpc.ElectionServiceBlockingStub;
import com.progetto.grpc.ElectionServiceOuterClass.ElectionRequest;
import com.progetto.grpc.ElectionServiceOuterClass.ElectionResponse;
import drones.DroneSingleton;
import drones.eventbus.EventBus;
import drones.eventbus.messages.ConfirmedElectedMessage;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ElectionElectionClient extends Thread {
    private final int electionId;
    private final int battery;

    public ElectionElectionClient(int electionId, int battery) {
        this.electionId = electionId;
        this.battery = battery;
    }

    @Override
    public void run() {
        System.out.println("ElectionElectionClient started");
        int ownId = DroneSingleton.getInstance().getId();
        List<DroneEntity> droneEntityList = new ArrayList<>(DroneSingleton.getInstance().getDroneList());

        List<DroneEntity> orderedEntityList = new ArrayList<>();
        droneEntityList.stream().filter(d -> d.getId() > ownId).forEach(orderedEntityList::add);
        droneEntityList.stream().filter(d -> d.getId() < ownId).forEach(orderedEntityList::add);

        System.out.println("ElectionElectionClient sending electionId: " + electionId + " to:");
        orderedEntityList.stream().forEach(d -> System.out.println(d.getId()));

        ManagedChannel channel = null;
        int failCount = 0;
        for (DroneEntity droneEntity : orderedEntityList) {
            channel = ManagedChannelBuilder.forTarget("localhost:" + droneEntity.getPort()).usePlaintext().build();
            ElectionServiceBlockingStub stub = ElectionServiceGrpc.newBlockingStub(channel);
            ElectionRequest electionRequest = ElectionRequest.newBuilder().setId(ownId).setElectionId(electionId).setBattery(battery).build();
            try {
                ElectionResponse electionResponse = stub.withDeadlineAfter(5000, TimeUnit.MILLISECONDS).forwardElection(electionRequest);
            } catch (StatusRuntimeException e) {
                System.out.println("ElectionElectionClient ERROR FORWARDING " + electionRequest.getElectionId() + " TO " + droneEntity.getId() + ". Moving to next in ring.");
                channel.shutdown();
                failCount += 1;
                continue;
            }
            System.out.println("ElectionElectionClient SUCCESSFULLY FORWARDED " + electionRequest.getElectionId() + " TO " + droneEntity.getId());
            channel.shutdown();
            break;
        }

        if (channel != null && !channel.isShutdown()) {
            channel.shutdown();
        }

        if (failCount == orderedEntityList.size()) {
            System.out.println("ElectionElectionClient no one is answering, assuming last so elect myself to master");
            DroneSingleton.getInstance().setDroneList(new ArrayList<>());
            DroneSingleton.getInstance().setMaster(DroneSingleton.getInstance().getDroneEntity());
            EventBus.getInstance().put(new ConfirmedElectedMessage());
        }

        System.out.println("ElectionElectionClient ended");
    }
}
