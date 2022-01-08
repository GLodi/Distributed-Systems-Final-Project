package drones.greetings;

import admin.entities.DroneEntity;
import com.progetto.grpc.GreetingsServiceGrpc;
import com.progetto.grpc.GreetingsServiceGrpc.GreetingsServiceBlockingStub;
import com.progetto.grpc.GreetingsServiceOuterClass.Drone;
import com.progetto.grpc.GreetingsServiceOuterClass.HelloRequest;
import com.progetto.grpc.GreetingsServiceOuterClass.HelloResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GreetingsClient extends Thread {
    private final DroneEntity own;
    private final DroneEntity target;

    GreetingsClient(DroneEntity own, DroneEntity target) {
        this.own = own;
        this.target = target;
    }

    @Override
    public void run() {
        System.out.println("GreetingsClient client started");
        final ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:" + target.getPort()).usePlaintext().build();
        GreetingsServiceBlockingStub stub = GreetingsServiceGrpc.newBlockingStub(channel);
        HelloRequest helloRequest = HelloRequest.newBuilder()
                .setDrone(
                        Drone.newBuilder()
                                .setId(own.getId())
                                .setX(own.getX())
                                .setY(own.getY())
                                .setPort(own.getPort()))
                .build();
        HelloResponse helloResponse = stub.greet(helloRequest);
        System.out.println("greeting da: " + helloResponse.getId());
        channel.shutdown();
        System.out.println("GreetingsClient client ended");
    }
}
