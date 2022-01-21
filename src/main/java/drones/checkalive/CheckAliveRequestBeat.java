package drones.checkalive;

import admin.entities.DroneEntity;
import com.progetto.grpc.CheckAliveServiceGrpc;
import com.progetto.grpc.CheckAliveServiceGrpc.CheckAliveServiceStub;
import com.progetto.grpc.CheckAliveServiceOuterClass.HeartbeatRequest;
import com.progetto.grpc.CheckAliveServiceOuterClass.HeartbeatResponse;
import drones.DroneSingleton;
import drones.eventbus.EventBus;
import drones.eventbus.messages.ElectionMessage;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class CheckAliveRequestBeat extends Thread {
    private final DroneEntity droneToCheck;

    public CheckAliveRequestBeat(DroneEntity droneToCheck) {
        this.droneToCheck = droneToCheck;
    }

    @Override
    public void run() {
        System.out.println("CheckAlive RequestBeat requesting beat");
        final ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:" + droneToCheck.getPort()).usePlaintext().build();
        CheckAliveServiceStub stub = CheckAliveServiceGrpc.newStub(channel);
        HeartbeatRequest request = HeartbeatRequest.newBuilder().setId(DroneSingleton.getInstance().getId()).build();
        stub.beat(request, new StreamObserver<HeartbeatResponse>() {
            @Override
            public void onNext(HeartbeatResponse heartbeatResponse) {

            }

            @Override
            public void onError(Throwable throwable) {
                channel.shutdownNow();
                System.out.println("CheckAlive RequestBeat DRONE CADUTO: " + throwable.getLocalizedMessage());
                DroneSingleton.getInstance().removeFromRing(droneToCheck.getId());
                if (droneToCheck.getId() == DroneSingleton.getInstance().getMaster().getId()) {
                    System.out.println("CheckAlive RequestBeat MASTER CADUTO: " + throwable.getLocalizedMessage());
                    EventBus.getInstance().put(new ElectionMessage(DroneSingleton.getInstance().getId(), DroneSingleton.getInstance().getBattery()));
                }
            }

            @Override
            public void onCompleted() {
                channel.shutdownNow();
            }
        });
    }
}
