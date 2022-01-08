package drones;

import admin.entities.DroneEntity;
import drones.election.DroneElectionThread;
import drones.eventbus.EventBus;
import drones.order.DroneOrderThread;
import drones.sensors.DroneSensorsThread;
import drones.stats.DroneStatsThread;

import java.util.List;

public class DroneSingleton {

    private static DroneSingleton instance;

    // TODO: split this into multiple models, (handled by singletons) so that accessing one piece of info
    //      doesn't require the sync of the entire DroneSingleton
    private DroneModel droneModel;

    private EventBus eventBus;

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

    public synchronized void init(DroneModel model) {
        droneModel = model;
    }

    public synchronized List<DroneEntity> getDroneList() {
        return droneModel.getDroneList();
    }

    public synchronized DroneEntity getDroneEntity() {
        return DroneModel.getEntity(droneModel);
    }

    public synchronized int getPort() {
        return droneModel.getPort();
    }

    public synchronized void startElectionThread() {
        try {
            droneElectionThread = new DroneElectionThread(droneModel.getDroneList());
            droneElectionThread.run();
        } catch (Exception e) {
            System.out.println("esecuzione fallita");
        }
    }

    public synchronized void interruptAll() {
        droneElectionThread.interrupt();
        droneOrderThread.interrupt();
        droneSensorsThread.interrupt();
        droneStatsThread.interrupt();
    }
}
