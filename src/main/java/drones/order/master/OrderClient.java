package drones.order.master;

import admin.entities.DroneEntity;

public class OrderClient extends Thread {
    private final DroneEntity droneToCheck;

    public OrderClient(DroneEntity droneToCheck) {
        this.droneToCheck = droneToCheck;
    }

    @Override
    public void run() {
        System.out.println("CheckAlive RequestBeat requesting beat to " + droneToCheck.getId());

    }
}
