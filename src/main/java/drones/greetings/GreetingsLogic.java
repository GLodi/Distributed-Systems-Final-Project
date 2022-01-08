package drones.greetings;

import admin.entities.DroneEntity;
import drones.DroneSingleton;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GreetingsLogic {

    //      AGGIUNTA IN BROADCAST SFRUTTANDO ID (SEMPRE PARALLELO) SULLA LISTA ESISTENTE
    // INSERIMENTO AVVIENE SEQUENZIALMENTE RISPETTO AL MAIN,
    // FAI UNA JOIN SUI THREAD CHE USI PER CONTATTARE GLI ALTRI DRONI
    // IN BROADCAST

    // TODO: crea un thread per ogni chiamata broadcast a tutti i droni
    //      presenti in DroneSingleton.droneModel.droneList.
    //      per ognuno fai una chiamata grpc con info su se stesso (id, x, y, port, non batteria xk si assume 100%)
    public void greetEveryone() {
        System.out.println("GreetingsLogic client started");
        for (DroneEntity droneEntity : DroneSingleton.getInstance().getDroneList()) {
            try {
                GreetingsClient greetingsClient = new GreetingsClient(DroneSingleton.getInstance().getDroneEntity(), droneEntity);
                greetingsClient.run();
            } catch (Exception e) {
                System.out.println("esecuzione fallita");
                System.out.println("GreetingsLogic client started");
            }
        }
        System.out.println("GreetingsLogic client started");
    }

    public void startServer() {
        try {
            System.out.println("GreetingsLogic server started");
            Server server = ServerBuilder.forPort(DroneSingleton.getInstance().getPort())
                    .addService(new GreetingsServiceImpl(DroneSingleton.getInstance().getDroneEntity()))
                    .build();
            server.start();
            server.awaitTermination();
            System.out.println("GreetingsLogic server ended");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("GreetingsLogic server ended");
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("GreetingsLogic server ended");
        }
    }
}

