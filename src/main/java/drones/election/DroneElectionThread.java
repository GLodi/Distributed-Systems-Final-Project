package drones.election;

import admin.entities.DroneEntity;

import java.util.List;

public class DroneElectionThread extends Thread {

    private final List<DroneEntity> droneEntityList;
    private ElectionStateEnum electionStateEnum;

    public DroneElectionThread(List<DroneEntity> droneEntityList) {
        this.droneEntityList = droneEntityList;
    }

    public void run() {
        // lista messaggi:
        //      WELCOME(id, porta, posizione, batteria si assume al 100)
    }
}
