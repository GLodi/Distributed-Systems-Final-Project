package drones.recharge;

import com.progetto.grpc.RechargeServiceGrpc;
import com.progetto.grpc.RechargeServiceGrpc.RechargeServiceBlockingStub;
import com.progetto.grpc.RechargeServiceOuterClass.InformMasterRequest;
import com.progetto.grpc.RechargeServiceOuterClass.InformMasterResponse;
import drones.DroneSingleton;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;

public class RechargeInformMasterClient extends Thread {
    @Override
    public void run() {
        System.out.println("Recharge RechargeInformMasterClient started");
        final ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:" + DroneSingleton.getInstance().getMaster().getPort()).usePlaintext().build();
        RechargeServiceBlockingStub stub = RechargeServiceGrpc.newBlockingStub(channel);
        InformMasterRequest informMasterRequest = InformMasterRequest.newBuilder().setId(DroneSingleton.getInstance().getId()).build();
        InformMasterResponse informMasterResponse = stub.withDeadlineAfter(5000, TimeUnit.MILLISECONDS).informMaster(informMasterRequest);
        channel.shutdown();
        System.out.println("Recharge RechargeInformMasterClient ended");
    }
}
