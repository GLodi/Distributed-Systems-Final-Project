package drones.greetings;

import admin.entities.DroneEntity;
import com.progetto.grpc.GreetingsServiceGrpc.GreetingsServiceImplBase;
import com.progetto.grpc.GreetingsServiceOuterClass.HelloRequest;
import com.progetto.grpc.GreetingsServiceOuterClass.HelloResponse;
import drones.DroneSingleton;
import drones.eventbus.EventBus;
import drones.eventbus.messages.InsertionMessage;
import io.grpc.stub.StreamObserver;

public class GreetingsServiceImpl extends GreetingsServiceImplBase {
    @Override
    public void greet(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        System.out.println("GreetingsServiceImpl greet received from " + request.getDrone().getId());
        HelloResponse response;
        if (DroneSingleton.getInstance().getMaster().getId() == DroneSingleton.getInstance().getId()) {
            response = HelloResponse.newBuilder().setId(DroneSingleton.getInstance().getId())
                    .setMaster(DroneSingleton.getInstance().getMaster().toDrone()).build();
        } else {
            response = HelloResponse.newBuilder().setId(DroneSingleton.getInstance().getId()).build();
        }
        DroneSingleton.getInstance().addToRing(new DroneEntity(request.getDrone()));
        EventBus.getInstance().put(new InsertionMessage(new DroneEntity(request.getDrone())));
        responseObserver.onNext(response);
        responseObserver.onCompleted();
        System.out.println("GreetingsServiceImpl greet ended");
    }

}
