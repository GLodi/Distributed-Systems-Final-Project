package drones.recharge;

import admin.entities.DroneEntity;
import com.google.protobuf.Timestamp;
import com.progetto.grpc.RechargeServiceGrpc;
import com.progetto.grpc.RechargeServiceGrpc.RechargeServiceBlockingStub;
import com.progetto.grpc.RechargeServiceOuterClass.RechargeRequest;
import com.progetto.grpc.RechargeServiceOuterClass.RechargeResponse;
import drones.DroneSingleton;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class RechargeBroadcastClient extends Thread {
    private final DroneEntity droneEntity;

    public RechargeBroadcastClient(DroneEntity droneEntity) {
        this.droneEntity = droneEntity;
    }

    @Override
    public void run() {
        System.out.println("Recharge RechargeBroadcastClient started");
        final ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:" + droneEntity.getPort()).usePlaintext().build();
        RechargeServiceBlockingStub stub = RechargeServiceGrpc.newBlockingStub(channel);
        Instant instant = Instant.now();
        RechargeRequest rechargeRequest = RechargeRequest.newBuilder()
                .setId(DroneSingleton.getInstance().getId())
                .setTimestamp(Timestamp.newBuilder().setSeconds(instant.getEpochSecond()).setNanos(instant.getNano()).build())
                .build();
        RechargeResponse rechargeResponse = stub.withDeadlineAfter(5000, TimeUnit.MILLISECONDS).broadcastRecharge(rechargeRequest);
        channel.shutdown();
        System.out.println("Recharge RechargeBroadcastClient ended");
    }
}
