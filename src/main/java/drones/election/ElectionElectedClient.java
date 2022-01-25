package drones.election;

import admin.entities.DroneEntity;
import com.progetto.grpc.ElectionServiceGrpc;
import com.progetto.grpc.ElectionServiceGrpc.ElectionServiceBlockingStub;
import com.progetto.grpc.ElectionServiceOuterClass.ElectedRequest;
import com.progetto.grpc.ElectionServiceOuterClass.ElectedResponse;
import drones.DroneSingleton;
import drones.eventbus.EventBus;
import drones.eventbus.messages.ConfirmedElectedMessage;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ElectionElectedClient extends Thread {
    private final int electedId;
    private final DroneEntity newMaster;

    public ElectionElectedClient(int electedId, DroneEntity newMaster) {
        this.electedId = electedId;
        this.newMaster = newMaster;
    }

    @Override
    public void run() {
        int ownId = DroneSingleton.getInstance().getId();
        List<DroneEntity> droneEntityList = new ArrayList<>(DroneSingleton.getInstance().getDroneList());

        List<DroneEntity> orderedEntityList = new ArrayList<>();
        droneEntityList.stream().filter(d -> d.getId() > ownId).forEach(orderedEntityList::add);
        droneEntityList.stream().filter(d -> d.getId() < ownId).forEach(orderedEntityList::add);

        System.out.println("ElectionElectedClient sending electedId: " + electedId + " to:");
        orderedEntityList.forEach(d -> System.out.println(d.getId()));

        ManagedChannel channel = null;
        int failCount = 0;
        for (DroneEntity droneEntity : orderedEntityList) {
            channel = ManagedChannelBuilder.forTarget("localhost:" + droneEntity.getPort()).usePlaintext().build();
            ElectionServiceBlockingStub stub = ElectionServiceGrpc.newBlockingStub(channel);
            ElectedRequest electedRequest = ElectedRequest.newBuilder().setId(electedId).setNewMaster(newMaster.toDrone()).build();
            try {
                ElectedResponse electedResponse = stub.withDeadlineAfter(5000, TimeUnit.MILLISECONDS).forwardElected(electedRequest);
            } catch (StatusRuntimeException e) {
                System.out.println("ElectionElectedClient ERROR FORWARDING TO " + droneEntity.getId() + ". Moving to next in ring.");
                channel.shutdown();
                failCount += 1;
                continue;
            }
            System.out.println("ElectionElectedClient SUCCESSFULLY FORWARDED TO " + droneEntity.getId());
            channel.shutdown();
            break;
        }

        if (channel != null && !channel.isShutdown()) {
            channel.shutdown();
        }

        if (failCount == orderedEntityList.size()) {
            System.out.println("ElectionElectedClient no one is answering, assuming last so elect myself to master");
            DroneSingleton.getInstance().setDroneList(new ArrayList<>());
            DroneSingleton.getInstance().setMaster(DroneSingleton.getInstance().getDroneEntity());
            EventBus.getInstance().put(new ConfirmedElectedMessage());
        }

        System.out.println("ElectionElectedClient ended");
    }

}
