package drones.insertion;

import admin.entities.DroneEntity;
import com.progetto.grpc.InsertionServiceGrpc.InsertionServiceImplBase;
import com.progetto.grpc.InsertionServiceOuterClass.InsertionRequest;
import com.progetto.grpc.InsertionServiceOuterClass.InsertionResponse;
import drones.DroneSingleton;
import io.grpc.stub.StreamObserver;

public class InsertionServiceImpl extends InsertionServiceImplBase {
    @Override
    public void insert(InsertionRequest request, StreamObserver<InsertionResponse> responseObserver) {
        System.out.println("InsertionServiceImpl insert request received from " + request.getToInsert().getId());
        InsertionResponse response;
        if (DroneSingleton.getInstance().getNext() != null) {
            response = InsertionResponse.newBuilder()
                    .setId(DroneSingleton.getInstance().getId())
                    .setNext(DroneSingleton.getInstance().getNext().toDrone())
                    .build();
        } else {
            response = InsertionResponse.newBuilder()
                    .setId(DroneSingleton.getInstance().getId())
                    .setNext(DroneSingleton.getInstance().getDroneEntity().toDrone())
                    .build();
        }

        DroneSingleton.getInstance().setNext(new DroneEntity(request.getToInsert()));
        responseObserver.onNext(response);
        responseObserver.onCompleted();
        System.out.println("InsertionServiceImpl insert request ended");
    }
}
