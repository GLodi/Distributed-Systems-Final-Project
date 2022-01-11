package drones.greetings;

import admin.entities.DroneEntity;
import drones.DroneSingleton;
import drones.eventbus.ErrorMessage;
import drones.eventbus.EventBus;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GreetingsLogic extends Thread {

    @Override
    public void run() {
        DroneEntity own = DroneSingleton.getInstance().getDroneEntity();
        List<DroneEntity> droneEntityList = new ArrayList<>(DroneSingleton.getInstance().getDroneList());
        droneEntityList.removeIf(d -> d.getId() == own.getId());
        int port = DroneSingleton.getInstance().getPort();

        startServer(port, own);
        greetEveryone(droneEntityList, own);
    }

    private void greetEveryone(List<DroneEntity> droneEntityList, DroneEntity own) {
        List<GreetingsClient> greetingsClientList = new ArrayList<>();
        for (DroneEntity droneEntity : droneEntityList) {
            System.out.println("GreetingsLogic greetEveryone greeting " + droneEntity.getId());
            try {
                greetingsClientList.add(new GreetingsClient(own, droneEntity));
                greetingsClientList.stream().reduce((f, s) -> s).get().run();
            } catch (Exception e) {
                System.out.println("GreetingsLogic greetEveryone ESECUZIONE FALLITA su " + droneEntity.getId());
            }
        }
        System.out.println("GreetingsLogic greeteveryone " + greetingsClientList.size() + " threads running");

        try {
            for (GreetingsClient greetingsClient : greetingsClientList) {
                greetingsClient.join();
            }
        } catch (InterruptedException e) {
            EventBus.getInstance().put(new ErrorMessage());
        }
        System.out.println("GreetingsLogic greetEveryone waited for all threads");

        List<Integer> repliedIds = new ArrayList<>();
        greetingsClientList.stream().filter(gc -> gc.getDroneIdReceived() != 0).forEach(gc -> repliedIds.add(gc.getDroneIdReceived()));
        System.out.println("GreetingsLogic greetEveryone received " + repliedIds.size() + " replies");

        DroneSingleton.getInstance().getDroneList().removeIf(d -> !repliedIds.stream().anyMatch(r -> r == d.getId()) && d.getId() != own.getId());
        System.out.println("GreetingsLogic greetEveryone cleaned replies");
    }

    private void startServer(int port, DroneEntity own) {
        try {
            System.out.println("GreetingsLogic server starting");
            Server server = ServerBuilder.forPort(port)
                    .addService(new GreetingsServiceImpl(own))
                    .build();
            server.start();
            System.out.println("GreetingsLogic server started");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("GreetingsLogic server ON ERROR" + e.getLocalizedMessage());
        }
    }

}

