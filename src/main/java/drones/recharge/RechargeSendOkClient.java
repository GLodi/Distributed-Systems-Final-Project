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
    private final int id;

    public RechargeSendOkClient(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Recharge RechargeSendOkClient started");
        DroneEntity droneEntity = DroneSingleton.getInstance().getDroneList().stream().filter(d -> d.getId() == id).findFirst().get();
        System.out.println("Recharge RechargeSendOkClient started to " + id);
        final ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:" + droneEntity.getPort()).usePlaintext().build();
        RechargeServiceBlockingStub stub = RechargeServiceGrpc.newBlockingStub(channel);
        SendOkRequest sendOkRequest = SendOkRequest.newBuilder().setId(DroneSingleton.getInstance().getId()).build();
        SendOkResponse sendOkResponse = stub.withDeadlineAfter(5000, TimeUnit.MILLISECONDS).sendOk(sendOkRequest);
        channel.shutdown();
        System.out.println("Recharge RechargeSendOkClient ended to " + id);
    }

}
