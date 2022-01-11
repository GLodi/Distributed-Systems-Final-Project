package drones.insertion;

import com.progetto.grpc.InsertionServiceGrpc.InsertionServiceImplBase;
import com.progetto.grpc.InsertionServiceOuterClass.InsertionRequest;
import com.progetto.grpc.InsertionServiceOuterClass.InsertionResponse;
import drones.DroneSingleton;
import io.grpc.stub.StreamObserver;

public class InsertionServiceImpl extends InsertionServiceImplBase {
    @Override
    public void insert(InsertionRequest request, StreamObserver<InsertionResponse> responseObserver) {
        System.out.println("InsertionServiceImpl insert request received from " + request.getId());
        InsertionResponse response = InsertionResponse.newBuilder()
                .setId(DroneSingleton.getInstance().getId())
                .setNextId(DroneSingleton.getInstance().getNextId())
                .build();
        DroneSingleton.getInstance().setNextId(request.getId());
        responseObserver.onNext(response);
        responseObserver.onCompleted();
        System.out.println("InsertionServiceImpl insert request ended");
    }
}
