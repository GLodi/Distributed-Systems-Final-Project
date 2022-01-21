package drones.checkalive;

import admin.entities.DroneEntity;
import drones.DroneSingleton;

public class CheckAliveEveryone extends Thread {
    @Override
    public void run() {
        System.out.println("CheckAlive Everyone requesting beat from entire ring");

        DroneSingleton.getInstance().getDroneList().forEach(this::makeBeatCall);

        System.out.println("CheckAlive Everyone done requesting beat from ring");
    }

    private void makeBeatCall(DroneEntity d) {
        try {
            CheckAliveRequestBeat checkAliveRequestBeat = new CheckAliveRequestBeat(d);
            checkAliveRequestBeat.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("CheckAlive Everyone thread exception : " + e.getLocalizedMessage());
        }
    }
}
