package drones;

import admin.entities.DroneEntity;
import drones.checkmasteralive.CheckMasterAliveLogic;
import drones.checkmasteralive.CheckMasterAliveServiceImpl;
import drones.election.ElectionLogic;
import drones.election.ElectionServiceImpl;
import drones.eventbus.EventBus;
import drones.eventbus.messages.ErrorMessage;
import drones.greetings.GreetingsLogic;
import drones.greetings.GreetingsServiceImpl;
import drones.order.DroneOrderThread;
import drones.register.RegistrationLogic;
import drones.sensors.DroneSensorsThread;
import drones.stats.DroneStatsThread;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DroneSingleton {

    private static DroneSingleton instance;

    // TODO: split this into multiple models, (handled by singletons) so that accessing one piece of info
    //      doesn't require the sync of the entire DroneSingleton
    private DroneModel droneModel;

    private RegistrationLogic registrationLogic;
    private GreetingsLogic greetingsLogic;
    private CheckMasterAliveLogic checkMasterAliveLogic;
    private ElectionLogic electionLogic;
    private DroneOrderThread droneOrderThread;
    private DroneSensorsThread droneSensorsThread;
    private DroneStatsThread droneStatsThread;

    private DroneSingleton() {
    }

    public synchronized static DroneSingleton getInstance() {
        if (instance == null)
            instance = new DroneSingleton();
        return instance;
    }


    public void startRegisterService(int id, String address, int port) {
        try {
            registrationLogic = new RegistrationLogic(id, address, port);
            registrationLogic.start();
            registrationLogic.join();
            droneModel = registrationLogic.getDroneModel();
        } catch (Exception e) {
            System.out.println("DroneSingleton startRegisterService esecuzione fallita");
            EventBus.getInstance().put(new ErrorMessage());
        }
    }

    public void startGreetingsService() {
        try {
            greetingsLogic = new GreetingsLogic();
            greetingsLogic.start();
        } catch (Exception e) {
            System.out.println("DroneSingleton startGreetingsService esecuzione fallita");
            EventBus.getInstance().put(new ErrorMessage());
        }
    }

    public void startCheckMasterAliveService() {
        try {
            checkMasterAliveLogic = new CheckMasterAliveLogic();
            checkMasterAliveLogic.start();
        } catch (Exception e) {
            System.out.println("DroneSingleton startCheckMasterAliveService esecuzione fallita");
        }
    }

    public void startElectionService() {
        try {
            electionLogic = new ElectionLogic();
            electionLogic.start();
        } catch (Exception e) {
            System.out.println("DroneSingleton startElectionService esecuzione fallita");
        }
    }

    public void listenForErrors() {
        while (true) {
            if (EventBus.getInstance().take("ERROR") != null) {

            }
        }
    }

    public synchronized void startGRPCServers() {
        try {
            System.out.println("GRPC servers starting");
            Server server = ServerBuilder.forPort(DroneSingleton.getInstance().getPort())
                    .addService(new GreetingsServiceImpl())
                    .addService(new CheckMasterAliveServiceImpl())
                    .addService(new ElectionServiceImpl())
                    .build();
            server.start();
            System.out.println("GRPC servers started");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("GRPC server ERROR:" + e.getLocalizedMessage());
        }
    }

    // TODO: subscribe to eventbus for error handling. If error message shows up, kill all threads.
    public synchronized void interruptAll() {
        if (greetingsLogic != null) {
            greetingsLogic.interrupt();
        }
        if (electionLogic != null) {
            electionLogic.interrupt();
        }
        if (droneOrderThread != null) {
            droneOrderThread.interrupt();
        }
        if (droneSensorsThread != null) {
            droneSensorsThread.interrupt();
        }
        if (droneStatsThread != null) {
            droneStatsThread.interrupt();
        }
    }

    public synchronized boolean initiated() {
        return droneModel != null;
    }

    public synchronized List<DroneEntity> getDroneList() {
        return droneModel.droneList;
    }

    public synchronized void setDroneList(List<DroneEntity> droneEntityList) {
        droneModel.droneList = new ArrayList<>(droneEntityList);
    }

    public synchronized DroneEntity getDroneEntity() {
        return DroneModel.getEntity(droneModel);
    }

    public synchronized int getPort() {
        return droneModel.port;
    }

    public synchronized int getId() {
        return droneModel.id;
    }

    public synchronized DroneEntity getMaster() {
        return droneModel.master;
    }

    public synchronized void setMaster(DroneEntity master) {
        droneModel.master = master;
    }

    public synchronized void setParticipant() {
        droneModel.electionParticipant = true;
    }

    public synchronized void setNonParticipant() {
        droneModel.electionParticipant = false;
    }

    public synchronized boolean isParticipant() {
        return droneModel.electionParticipant;
    }

    public synchronized void addToRing(DroneEntity droneEntity) {
        droneModel.droneList.add(droneEntity);
        droneModel.droneList.sort(Comparator.comparingInt(DroneEntity::getId));
    }

    public synchronized void setIsBeingElected(boolean b) {
        droneModel.isBeingElected = b;
    }

    public synchronized boolean isBeingElected() {
        return droneModel.isBeingElected;
    }

    public synchronized int getBattery() {
        return droneModel.battery;
    }
}
