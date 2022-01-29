package drones.recharge;

import admin.entities.DroneEntity;
import com.progetto.grpc.RechargeServiceGrpc;
import com.progetto.grpc.RechargeServiceGrpc.RechargeServiceBlockingStub;
import com.progetto.grpc.RechargeServiceOuterClass.RechargeRequest;
import com.progetto.grpc.RechargeServiceOuterClass.RechargeResponse;
import drones.DroneSingleton;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;

public class RechargeClient extends Thread {
    private final DroneEntity droneEntity;

    public RechargeClient(DroneEntity droneEntity) {
        this.droneEntity = droneEntity;
    }

    @Override
    public void run() {
        System.out.println("RechargeClient started");
        final ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:" + droneEntity.getPort()).usePlaintext().build();
        RechargeServiceBlockingStub stub = RechargeServiceGrpc.newBlockingStub(channel);
        RechargeRequest rechargeRequest = RechargeRequest.newBuilder().setId(DroneSingleton.getInstance().getId()).build();
        RechargeResponse rechargeResponse = stub.withDeadlineAfter(5000, TimeUnit.MILLISECONDS).broadcastRecharge(rechargeRequest);
        channel.shutdown();
        System.out.println("RechargeClient ended");
    }
}
