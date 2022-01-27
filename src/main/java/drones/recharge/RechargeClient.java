package drones.recharge;

import admin.entities.DroneEntity;

public class RechargeClient extends Thread {
    private final DroneEntity droneEntity;
    private volatile boolean result;

    public RechargeClient(DroneEntity droneEntity) {
        this.droneEntity = droneEntity;
    }

    @Override
    public void run() {

    }

    public boolean getResult() {
        return result;
    }
}
