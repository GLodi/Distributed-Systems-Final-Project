package drones.checkmasteralive;

import com.progetto.grpc.CheckMasterAliveServiceGrpc;
import com.progetto.grpc.CheckMasterAliveServiceOuterClass;
import drones.DroneSingleton;
import drones.eventbus.EventBus;
import drones.eventbus.messages.ElectionMessage;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class CheckMasterAliveRequestBeat extends Thread {

    @Override
    public void run() {
        System.out.println("CheckMasterAlive RequestBeat requesting beat");
        final ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:" + DroneSingleton.getInstance().getMaster().getPort()).usePlaintext().build();
        CheckMasterAliveServiceGrpc.CheckMasterAliveServiceStub stub = CheckMasterAliveServiceGrpc.newStub(channel);
        CheckMasterAliveServiceOuterClass.HeartbeatRequest request = CheckMasterAliveServiceOuterClass.HeartbeatRequest.newBuilder().setId(DroneSingleton.getInstance().getId()).build();
        stub.beat(request, new StreamObserver<CheckMasterAliveServiceOuterClass.HeartbeatResponse>() {
            @Override
            public void onNext(CheckMasterAliveServiceOuterClass.HeartbeatResponse heartbeatResponse) {

            }

            @Override
            public void onError(Throwable throwable) {
                channel.shutdownNow();
                System.out.println("CheckMasterAlive RequestBeat MASTER CADUTO: " + throwable.getLocalizedMessage());
                EventBus.getInstance().put(new ElectionMessage(DroneSingleton.getInstance().getId()));
            }

            @Override
            public void onCompleted() {
                channel.shutdownNow();
            }
        });
    }
}
