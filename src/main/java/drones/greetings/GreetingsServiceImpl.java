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
        System.out.println("GreetingsServiceImpl greet called from " + request.getDrone().getId());
        HelloResponse response = HelloResponse.newBuilder().setId(own.getId()).build();
        // TODO: NON AGGIORNARE rappresentazione interna dei droni quando arriva qualcun altro: se gestisci
        //      l'anello tenendo il nextId, i droni normali non hanno bisogno di sapere chi sono tutti i droni.
        //      Quando poi uno diventera' master dovra' comunque chiedere a tutti chi e dove sono.
        responseObserver.onNext(response);
        responseObserver.onCompleted();
        System.out.println("GreetingsServiceImpl greet ended");
    }

}
