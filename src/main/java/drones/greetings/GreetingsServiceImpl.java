package drones.greetings;

import admin.entities.DroneEntity;
import com.progetto.grpc.GreetingsServiceGrpc;
import com.progetto.grpc.GreetingsServiceOuterClass.HelloRequest;
import com.progetto.grpc.GreetingsServiceOuterClass.HelloResponse;
import io.grpc.stub.StreamObserver;

public class GreetingsServiceImpl extends GreetingsServiceGrpc.GreetingsServiceImplBase {
    private final DroneEntity own;

    GreetingsServiceImpl(DroneEntity own) {
        this.own = own;
    }

    @Override
    public void greet(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        System.out.println("GreetingsServiceImpl greet called");
        HelloResponse response = HelloResponse.newBuilder().setId(own.getId()).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
        System.out.println("GreetingsServiceImpl greet ended");
    }
}