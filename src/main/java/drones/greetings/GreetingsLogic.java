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

    // TODO: FAI UNA JOIN SUI THREAD CHE USI PER CONTATTARE GLI ALTRI DRONI IN BROADCAST
    // TODO: crea un thread per ogni chiamata broadcast a tutti i droni
    //      presenti in DroneSingleton.droneModel.droneList.
    //      per ognuno fai una chiamata grpc con info su se stesso (id, x, y, port, non batteria xk si assume 100%)
    private List<Integer> greetEveryone(List<DroneEntity> droneEntityList, DroneEntity own) {
        List<GreetingsClient> greetingsClientList = new ArrayList<>();
        for (DroneEntity droneEntity : droneEntityList) {
            System.out.println("GreetingsLogic client greeting " + droneEntity.getId());
            try {
                greetingsClientList.add(new GreetingsClient(own, droneEntity));
                greetingsClientList.stream().reduce((f, s) -> s).get().run();
            } catch (Exception e) {
                System.out.println("GreetingsLogic client ESECUZIONE FALLITA su " + droneEntity.getId());
            }
        }
        System.out.println("GreetingsLogic client " + greetingsClientList.size() + " threads running");

        try {
            for (GreetingsClient greetingsClient : greetingsClientList) {
                greetingsClient.join();
            }
        } catch (InterruptedException e) {
            EventBus.getInstance().put(new ErrorMessage());
        }
        System.out.println("GreetingsLogic client waited for all threads");

        List<Integer> repliedIds = new ArrayList<>();
        for (GreetingsClient greetingsClient : greetingsClientList) {
            if (greetingsClient.getDroneIdReceived() != 0) {
                repliedIds.add(greetingsClient.getDroneIdReceived());
            }
        }
        System.out.println("GreetingsLogic client received " + repliedIds.size() + " replies");

        return repliedIds;
    }

    private void processReplies(List<DroneEntity> droneEntityList, List<Integer> repliesIds) {

    }

    private void startServer(int port, DroneEntity own) {
        try {
            System.out.println("GreetingsLogic server started");
            Server server = ServerBuilder.forPort(port)
                    .addService(new GreetingsServiceImpl(own))
                    .build();
            server.start();
            System.out.println("GreetingsLogic server ended");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("GreetingsLogic server ended");
        }
    }

    @Override
    public void run() {
        DroneEntity own = DroneSingleton.getInstance().getDroneEntity();
        List<DroneEntity> droneEntityList = new ArrayList<>(DroneSingleton.getInstance().getDroneList());
        droneEntityList.removeIf(d -> d.getId() == own.getId());
        int port = DroneSingleton.getInstance().getPort();

        startServer(port, own);
        List<Integer> repliesIds = greetEveryone(droneEntityList, own);
        processReplies(droneEntityList, repliesIds);
    }
}

