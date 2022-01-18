package drones.election;

import com.progetto.grpc.ElectionServiceGrpc;
import com.progetto.grpc.ElectionServiceOuterClass.ElectedRequest;
import com.progetto.grpc.ElectionServiceOuterClass.ElectedResponse;
import com.progetto.grpc.ElectionServiceOuterClass.ElectionRequest;
import com.progetto.grpc.ElectionServiceOuterClass.ElectionResponse;
import drones.DroneSingleton;
import drones.eventbus.EventBus;
import drones.eventbus.messages.ElectedMessage;
import drones.eventbus.messages.ElectionMessage;
import io.grpc.stub.StreamObserver;

public class ElectionServiceImpl extends ElectionServiceGrpc.ElectionServiceImplBase {
    @Override
    public void forwardElection(ElectionRequest request, StreamObserver<ElectionResponse> responseObserver) {
        int ownId = DroneSingleton.getInstance().getId();
        System.out.println("ElectionServiceImpl forwardElection forward received from " + request.getId() + " with electionId " + request.getElectionId());

        if (request.getElectionId() > ownId) {
            // forward message unchanged
            System.out.println("ElectionServiceImpl forwardElection FORWARD MESSAGE UNCHANGED: " + request.getElectionId());
            EventBus.getInstance().put(new ElectionMessage(request.getElectionId()));
            DroneSingleton.getInstance().setParticipant();
        } else if (request.getElectionId() < ownId && !DroneSingleton.getInstance().isParticipant()) {
            // set electionId = id and forward
            System.out.println("ElectionServiceImpl forwardElection SET ELECTION ID: " + ownId);
            EventBus.getInstance().put(new ElectionMessage(ownId));
            DroneSingleton.getInstance().setParticipant();
        } else if (request.getElectionId() < ownId && DroneSingleton.getInstance().isParticipant()) {
            System.out.println("ElectionServiceImpl forwardElection DISCARDING ELECTION MESSAGE: " + request.getElectionId());
        } else if (request.getElectionId() == ownId) {
            // EventBus put ElectedMessage
            System.out.println("ElectionServiceImpl forwardElection ELECTED ID: " + ownId);
            EventBus.getInstance().put(new ElectedMessage());
        }
        responseObserver.onNext(ElectionResponse.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void forwardElected(ElectedRequest request, StreamObserver<ElectedResponse> responseObserver) {
        System.out.println("ElectionServiceImpl forwardElected forward received from " + request.getElectedId());
        responseObserver.onCompleted();
        EventBus.getInstance().put(new ElectedMessage());
    }
}
