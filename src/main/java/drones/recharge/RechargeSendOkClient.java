package drones.recharge;

import admin.entities.DroneEntity;
import com.progetto.grpc.RechargeServiceGrpc;
import com.progetto.grpc.RechargeServiceGrpc.RechargeServiceBlockingStub;
import com.progetto.grpc.RechargeServiceOuterClass.SendOkRequest;
import com.progetto.grpc.RechargeServiceOuterClass.SendOkResponse;
import drones.DroneSingleton;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;

public class RechargeSendOkClient extends Thread {
    private final DroneEntity droneEntity;

    public RechargeSendOkClient(DroneEntity droneEntity) {
        this.droneEntity = droneEntity;
    }

    @Override
    public void run() {
        System.out.println("RechargeSendOkClient started");
        final ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:" + droneEntity.getPort()).usePlaintext().build();
        RechargeServiceBlockingStub stub = RechargeServiceGrpc.newBlockingStub(channel);
        SendOkRequest sendOkRequest = SendOkRequest.newBuilder().setId(DroneSingleton.getInstance().getId()).build();
        SendOkResponse sendOkResponse = stub.withDeadlineAfter(5000, TimeUnit.MILLISECONDS).sendOk(sendOkRequest);
        channel.shutdown();
        System.out.println("RechargeSendOkClient ended");
    }

}
