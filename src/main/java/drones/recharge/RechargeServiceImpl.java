package drones.recharge;

import com.progetto.grpc.RechargeServiceGrpc.RechargeServiceImplBase;
import com.progetto.grpc.RechargeServiceOuterClass.InformMasterRequest;
import com.progetto.grpc.RechargeServiceOuterClass.InformMasterResponse;
import com.progetto.grpc.RechargeServiceOuterClass.RechargeRequest;
import com.progetto.grpc.RechargeServiceOuterClass.RechargeResponse;
import drones.DroneSingleton;
import io.grpc.stub.StreamObserver;

public class RechargeServiceImpl extends RechargeServiceImplBase {
    @Override
    public void broadcastRecharge(RechargeRequest request, StreamObserver<RechargeResponse> responseObserver) {
        // 3 opzioni:
        //      se NON STO ricaricando: mando OK
        //      se STO ricaricando: non rispondi e metti richiesta in queue
        //      se voglio ricaricarmi, ma non l'ho ancora fatto compara i timestamp della tua e sua request.timestamp:
        //          se il mio e' minore
    }

    @Override
    public void informMaster(InformMasterRequest request, StreamObserver<InformMasterResponse> responseObserver) {
        // as master, set this drone to position (0,0) and battery 100
        DroneSingleton.getInstance().updateDrone(request.getId(), 0, 0, 100);
        responseObserver.onNext(InformMasterResponse.newBuilder().build());
        responseObserver.onCompleted();
    }
}
