package drones.checkalive.listeners;

import admin.entities.DroneEntity;
import drones.checkalive.CheckAliveRequestBeat;
import drones.eventbus.EventBus;
import drones.eventbus.messages.InsertionMessage;

public class InsertionListener extends Thread {
    @Override
    public void run() {
        System.out.println("CheckAlive InsertionListener waiting for INSERTION message");
        while (!Thread.currentThread().isInterrupted()) {
            InsertionMessage message = (InsertionMessage) EventBus.getInstance().take("INSERTION");
            if (message != null) {
                System.out.println("CheckAlive InsertionListener INSERTION message received");
                beat(message.newDrone);
            }
        }
    }

    private void beat(DroneEntity newDrone) {
        try {
            CheckAliveRequestBeat checkAliveRequestBeat = new CheckAliveRequestBeat(newDrone);
            checkAliveRequestBeat.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("CheckAlive InsertionListener thread exception : " + e.getLocalizedMessage());
        }
    }
}
