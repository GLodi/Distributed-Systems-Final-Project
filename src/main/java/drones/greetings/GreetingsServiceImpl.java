package drones.greetings;

import admin.entities.DroneEntity;
import com.progetto.grpc.GreetingsServiceGrpc.GreetingsServiceImplBase;
import com.progetto.grpc.GreetingsServiceOuterClass.HelloRequest;
import com.progetto.grpc.GreetingsServiceOuterClass.HelloResponse;
import io.grpc.stub.StreamObserver;

public class GreetingsServiceImpl extends GreetingsServiceImplBase {
    private final DroneEntity own;

    GreetingsServiceImpl(DroneEntity own) {
        this.own = own;
    }

    @Override
    public void greet(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        System.out.println("GreetingsServiceImpl greet received from " + request.getDrone().getId());
        HelloResponse response = HelloResponse.newBuilder().setId(own.getId()).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
        System.out.println("GreetingsServiceImpl greet ended");
    }

}
