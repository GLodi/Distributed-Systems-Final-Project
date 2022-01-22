package drones.greetings;

import admin.entities.DroneEntity;
import com.progetto.grpc.GreetingsServiceGrpc;
import com.progetto.grpc.GreetingsServiceGrpc.GreetingsServiceBlockingStub;
import com.progetto.grpc.GreetingsServiceOuterClass.HelloRequest;
import com.progetto.grpc.GreetingsServiceOuterClass.HelloResponse;
import drones.DroneSingleton;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;

public class GreetingsClient extends Thread {
    private final DroneEntity target;
    private volatile DroneEntity droneReceived = null;

    GreetingsClient(DroneEntity target) {
        this.target = target;
    }

    @Override
    public void run() {
        System.out.println("GreetingsClient started");
        final ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:" + target.getPort()).usePlaintext().build();
        GreetingsServiceBlockingStub stub = GreetingsServiceGrpc.newBlockingStub(channel);
        HelloRequest helloRequest = HelloRequest.newBuilder()
                .setDrone(DroneSingleton.getInstance().getDroneEntity().toDrone())
                .build();
        HelloResponse helloResponse = stub.withDeadlineAfter(5000, TimeUnit.MILLISECONDS).greet(helloRequest);
        if (helloResponse.getIsMaster()) {
            DroneSingleton.getInstance().setMaster(new DroneEntity(helloResponse.getDrone()));
            System.out.println("greeting da master: " + helloResponse.getDrone().getId());
        } else {
            System.out.println("greeting da: " + helloResponse.getDrone().getId());
        }
        channel.shutdown();
        System.out.println("GreetingsClient ended");
        this.droneReceived = new DroneEntity(helloResponse.getDrone());
    }

    public DroneEntity getDroneReceived() {
        return droneReceived;
    }
}
