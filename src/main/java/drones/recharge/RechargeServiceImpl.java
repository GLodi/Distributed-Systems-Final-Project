package drones.recharge;

import com.progetto.grpc.RechargeServiceGrpc.RechargeServiceImplBase;
import com.progetto.grpc.RechargeServiceOuterClass.*;
import drones.DroneSingleton;
import drones.eventbus.EventBus;
import drones.eventbus.messages.RechargeMessage;
import drones.eventbus.messages.SendOkRechargeMessage;
import io.grpc.stub.StreamObserver;

import java.sql.Timestamp;
import java.time.Instant;

public class RechargeServiceImpl extends RechargeServiceImplBase {
    @Override
    public void broadcastRecharge(RechargeRequest request, StreamObserver<RechargeResponse> responseObserver) {
        System.out.println("Recharge RechargeServiceImpl broadcastRecharge started from " + request.getId());
        switch (RechargeQueue.getInstance().getRechargeState()) {
            case RECHARGING: {
                System.out.println("Recharge RechargeServiceImpl broadcastRecharge received while recharging");
                // non rispondo, metto in queue
                responseObserver.onNext(RechargeResponse.newBuilder().build());
                responseObserver.onCompleted();
                if (DroneSingleton.getInstance().getDroneList().stream().anyMatch(d -> d.getId() == request.getId())) {
                    RechargeQueue.getInstance().put(new Recharge(request.getId(), Timestamp.from(Instant.now())));
                }
                break;
            }
            case NOT_RECHARGING: {
                System.out.println("Recharge RechargeServiceImpl broadcastRecharge received while not recharging");
                // mando ok
                responseObserver.onNext(RechargeResponse.newBuilder().build());
                responseObserver.onCompleted();
                if (DroneSingleton.getInstance().getDroneList().stream().anyMatch(d -> d.getId() == request.getId())) {
                    EventBus.getInstance().put(new SendOkRechargeMessage(request.getId()));
                }
                break;
            }
            case WANTING_TO_RECHARGE: {
                System.out.println("Recharge RechargeServiceImpl broadcastRecharge received while wanting to recharge");
                // compara ts: se il mio e' piu' basso, metto richiesta in queue. Altrimenti mando ok.
                responseObserver.onNext(RechargeResponse.newBuilder().build());
                responseObserver.onCompleted();
                if (DroneSingleton.getInstance().getDroneList().stream().anyMatch(d -> d.getId() == request.getId())) {
                    if (RechargeQueue.getInstance().getOwnRequest().getTimestamp().before(Timestamp.from(Instant.ofEpochSecond(request.getTimestamp().getSeconds(), request.getTimestamp().getNanos())))) {
                        RechargeQueue.getInstance().put(new Recharge(request.getId(), Timestamp.from(Instant.ofEpochSecond(request.getTimestamp().getSeconds(), request.getTimestamp().getNanos()))));
                    } else {
                        EventBus.getInstance().put(new SendOkRechargeMessage(request.getId()));
                    }
                }
                break;
            }
        }
        System.out.println("Recharge RechargeServiceImpl broadcastRecharge ended from " + request.getId());
    }

    @Override
    public void informMaster(InformMasterRequest request, StreamObserver<InformMasterResponse> responseObserver) {
        // as master, set this drone to position (0,0) and battery 100
        System.out.println("Recharge RechargeServiceImpl informMaster received from " + request.getId());
        responseObserver.onNext(InformMasterResponse.newBuilder().build());
        responseObserver.onCompleted();

        DroneSingleton.getInstance().updateDrone(request.getId(), 0, 0, 100);

        System.out.println("Recharge RechargeServiceImpl informMaster ended from " + request.getId());
    }

    @Override
    public void sendOk(SendOkRequest request, StreamObserver<SendOkResponse> responseObserver) {
        System.out.println("Recharge RechargeServiceImpl sendOk received from: " + request.getId());
        // aggiungi ok alla RechargeQueue. Quando counter == droni allora ricarica. alla fine della ricarica chiama informmaster
        responseObserver.onNext(SendOkResponse.newBuilder().build());
        responseObserver.onCompleted();

        RechargeQueue.getInstance().increaseOkCounter();

        if (RechargeQueue.getInstance().getOkCounter() >= RechargeQueue.getInstance().getOkToReceive() &&
                RechargeQueue.getInstance().getRechargeState() != RechargeStateEnum.RECHARGING) {
            // fai ricarica
            EventBus.getInstance().put(new RechargeMessage());
        }
        System.out.println("Recharge RechargeServiceImpl sendOk ended from " + request.getId());
    }
}
