package drones;

import admin.entities.DroneEntity;
import drones.election.DroneElectionThread;
import drones.eventbus.ErrorMessage;
import drones.eventbus.EventBus;
import drones.greetings.GreetingsLogic;
import drones.insertion.InsertionLogic;
import drones.order.DroneOrderThread;
import drones.register.RegistrationLogic;
import drones.sensors.DroneSensorsThread;
import drones.stats.DroneStatsThread;

import java.util.List;

public class DroneSingleton {

    private static DroneSingleton instance;

    // TODO: split this into multiple models, (handled by singletons) so that accessing one piece of info
    //      doesn't require the sync of the entire DroneSingleton
    private DroneModel droneModel;

    private RegistrationLogic registrationLogic;
    private GreetingsLogic greetingsLogic;
    private InsertionLogic insertionLogic;
    private DroneElectionThread droneElectionThread;
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
            registrationLogic.run();
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
            greetingsLogic.run();
            greetingsLogic.join();
        } catch (Exception e) {
            System.out.println("DroneSingleton startGreetingsService esecuzione fallita");
            EventBus.getInstance().put(new ErrorMessage());
        }
    }

    public void startInsertionService() {
        try {
            insertionLogic = new InsertionLogic();
            insertionLogic.run();
            insertionLogic.join();
        } catch (Exception e) {
            System.out.println("DroneSingleton startInsertionService esecuzione fallita");
            EventBus.getInstance().put(new ErrorMessage());
        }
    }

    public void listenForErrors() {
        while (true) {
            if (EventBus.getInstance().take("ERROR") != null) {

            }
        }
    }

    // TODO: subscribe to eventbus for error handling. If error message shows up, kill all threads.
    public synchronized void interruptAll() {
        if (greetingsLogic != null) {
            greetingsLogic.interrupt();
        }
        if (droneElectionThread != null) {
            droneElectionThread.interrupt();
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

    public synchronized DroneEntity getDroneEntity() {
        return DroneModel.getEntity(droneModel);
    }

    public synchronized int getPort() {
        return droneModel.port;
    }

    public synchronized int getId() {
        return droneModel.id;
    }

    public synchronized int getNextId() {
        return droneModel.nextId;
    }
}
