package drones.election;

import admin.entities.DroneEntity;
import com.progetto.grpc.ElectionServiceGrpc;
import com.progetto.grpc.ElectionServiceOuterClass.ElectedRequest;
import com.progetto.grpc.ElectionServiceOuterClass.ElectedResponse;
import com.progetto.grpc.ElectionServiceOuterClass.ElectionRequest;
import com.progetto.grpc.ElectionServiceOuterClass.ElectionResponse;
import drones.DroneSingleton;
import drones.eventbus.EventBus;
import drones.eventbus.messages.ConfirmedElectedMessage;
import drones.eventbus.messages.ElectedMessage;
import drones.eventbus.messages.ElectionMessage;
import io.grpc.stub.StreamObserver;

public class ElectionServiceImpl extends ElectionServiceGrpc.ElectionServiceImplBase {
    @Override
    public void forwardElection(ElectionRequest request, StreamObserver<ElectionResponse> responseObserver) {
        int ownId = DroneSingleton.getInstance().getId();
        int ownBattery = DroneSingleton.getInstance().getBattery();
        System.out.println("ElectionServiceImpl forwardElection forward received from " + request.getId() +
                " with electionId " + request.getElectionId() +
                " and battery " + request.getBattery());

        if (request.getBattery() > ownBattery || (request.getBattery() == ownBattery && request.getElectionId() > ownId)) {
            // forward message unchanged
            System.out.println("ElectionServiceImpl forwardElection FORWARD MESSAGE UNCHANGED: " + request.getElectionId());
            ElectionSingleton.getInstance().setParticipant();
            responseObserver.onNext(ElectionResponse.newBuilder().build());
            responseObserver.onCompleted();
            EventBus.getInstance().put(new ElectionMessage(request.getElectionId(), request.getBattery()));
        } else if (!ElectionSingleton.getInstance().isParticipant() && request.getBattery() < ownBattery
                || (request.getBattery() == ownBattery && request.getElectionId() < ownId)) {
            // set electionId = id and forward
            System.out.println("ElectionServiceImpl forwardElection SET ELECTION ID: " + ownId);
            ElectionSingleton.getInstance().setParticipant();
            responseObserver.onNext(ElectionResponse.newBuilder().build());
            responseObserver.onCompleted();
            EventBus.getInstance().put(new ElectionMessage(ownId, ownBattery));
        } else if (ElectionSingleton.getInstance().isParticipant() && request.getBattery() < ownBattery
                || (request.getBattery() == ownBattery && request.getElectionId() < ownId)) {
            System.out.println("ElectionServiceImpl forwardElection DISCARDING ELECTION MESSAGE: " + request.getElectionId());
            responseObserver.onNext(ElectionResponse.newBuilder().build());
            responseObserver.onCompleted();
        } else if (request.getElectionId() == ownId) {
            System.out.println("ElectionServiceImpl forwardElection ELECTED ID: " + ownId);
            DroneSingleton.getInstance().setMaster(DroneSingleton.getInstance().getDroneEntity());
            ElectionSingleton.getInstance().setNonParticipant();
            responseObserver.onNext(ElectionResponse.newBuilder().build());
            responseObserver.onCompleted();
            EventBus.getInstance().put(new ElectedMessage(DroneSingleton.getInstance().getId(), DroneSingleton.getInstance().getDroneEntity()));
        }
    }

    @Override
    public void forwardElected(ElectedRequest request, StreamObserver<ElectedResponse> responseObserver) {
        System.out.println("ElectionServiceImpl forwardElected forward received from " + request.getId());
        if (request.getId() != DroneSingleton.getInstance().getId()) {
            ElectionSingleton.getInstance().setNonParticipant();
            DroneSingleton.getInstance().setMaster(new DroneEntity(request.getNewMaster()));
            responseObserver.onNext(ElectedResponse.newBuilder().build());
            responseObserver.onCompleted();
            EventBus.getInstance().put(new ElectedMessage(request.getId(), new DroneEntity(request.getNewMaster())));
        } else {
            System.out.println("ElectionServiceImpl forwardElected received my own ELECTED. ELECTION OVER");
            responseObserver.onNext(ElectedResponse.newBuilder().build());
            responseObserver.onCompleted();
            EventBus.getInstance().put(new ConfirmedElectedMessage());
        }

    }
}
