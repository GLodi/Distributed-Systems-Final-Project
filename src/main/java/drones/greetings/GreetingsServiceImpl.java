package drones.greetings;

import com.progetto.grpc.GreetingsServiceGrpc.GreetingsServiceImplBase;
import com.progetto.grpc.GreetingsServiceOuterClass.HelloRequest;
import com.progetto.grpc.GreetingsServiceOuterClass.HelloResponse;
import drones.DroneSingleton;
import io.grpc.stub.StreamObserver;

public class GreetingsServiceImpl extends GreetingsServiceImplBase {
    @Override
    public void greet(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        System.out.println("GreetingsServiceImpl greet received from " + request.getDrone().getId());
        HelloResponse response = HelloResponse.newBuilder().setId(DroneSingleton.getInstance().getId())
                .setMaster(DroneSingleton.getInstance().getMaster() == DroneSingleton.getInstance().getId()).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
        System.out.println("GreetingsServiceImpl greet ended");
    }

}
