package drones.election;

import admin.entities.DroneEntity;
import com.progetto.grpc.ElectionServiceGrpc;
import com.progetto.grpc.ElectionServiceOuterClass.ElectedRequest;
import com.progetto.grpc.ElectionServiceOuterClass.ElectedResponse;
import com.progetto.grpc.ElectionServiceOuterClass.ElectionRequest;
import com.progetto.grpc.ElectionServiceOuterClass.ElectionResponse;
import drones.DroneSingleton;
import drones.eventbus.EventBus;
import drones.eventbus.messages.ElectedMessage;
import drones.eventbus.messages.ElectionMessage;
import drones.eventbus.messages.NewMasterMessage;
import io.grpc.stub.StreamObserver;

public class ElectionServiceImpl extends ElectionServiceGrpc.ElectionServiceImplBase {
    @Override
    public void forwardElection(ElectionRequest request, StreamObserver<ElectionResponse> responseObserver) {
        int ownId = DroneSingleton.getInstance().getId();
        System.out.println("ElectionServiceImpl forwardElection forward received from " + request.getId() + " with electionId " + request.getElectionId());

        // TODO: da modificare, viene eletto quello con batteria maggiore, se uguale id maggiore
        if (request.getElectionId() > ownId) {
            // forward message unchanged
            System.out.println("ElectionServiceImpl forwardElection FORWARD MESSAGE UNCHANGED: " + request.getElectionId());
            DroneSingleton.getInstance().setParticipant();
            responseObserver.onNext(ElectionResponse.newBuilder().build());
            responseObserver.onCompleted();
            EventBus.getInstance().put(new ElectionMessage(request.getElectionId()));
        } else if (request.getElectionId() < ownId && !DroneSingleton.getInstance().isParticipant()) {
            // set electionId = id and forward
            System.out.println("ElectionServiceImpl forwardElection SET ELECTION ID: " + ownId);
            DroneSingleton.getInstance().setParticipant();
            responseObserver.onNext(ElectionResponse.newBuilder().build());
            responseObserver.onCompleted();
            EventBus.getInstance().put(new ElectionMessage(ownId));
        } else if (request.getElectionId() < ownId && DroneSingleton.getInstance().isParticipant()) {
            System.out.println("ElectionServiceImpl forwardElection DISCARDING ELECTION MESSAGE: " + request.getElectionId());
            responseObserver.onNext(ElectionResponse.newBuilder().build());
            responseObserver.onCompleted();
        } else if (request.getElectionId() == ownId) {
            // EventBus put ElectedMessage
            System.out.println("ElectionServiceImpl forwardElection ELECTED ID: " + ownId);
            DroneSingleton.getInstance().setMaster(DroneSingleton.getInstance().getDroneEntity());
            DroneSingleton.getInstance().setNonParticipant();
            responseObserver.onNext(ElectionResponse.newBuilder().build());
            responseObserver.onCompleted();
            EventBus.getInstance().put(new ElectedMessage(DroneSingleton.getInstance().getId(), DroneSingleton.getInstance().getDroneEntity()));
        }
    }

    @Override
    public void forwardElected(ElectedRequest request, StreamObserver<ElectedResponse> responseObserver) {
        System.out.println("ElectionServiceImpl forwardElected forward received from " + request.getId());
        if (request.getId() != DroneSingleton.getInstance().getId()) {
            DroneSingleton.getInstance().setNonParticipant();
            DroneSingleton.getInstance().setMaster(new DroneEntity(request.getNewMaster()));
            responseObserver.onNext(ElectedResponse.newBuilder().build());
            responseObserver.onCompleted();
            EventBus.getInstance().put(new ElectedMessage(request.getId(), new DroneEntity(request.getNewMaster())));
            EventBus.getInstance().put(new NewMasterMessage());
        } else {
            System.out.println("ElectionServiceImpl forwardElected received my own ELECTED. ELECTION OVER");
            responseObserver.onNext(ElectedResponse.newBuilder().build());
            responseObserver.onCompleted();
        }

    }
}
