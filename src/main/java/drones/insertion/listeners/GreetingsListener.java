package drones.insertion.listeners;

import admin.entities.DroneEntity;
import drones.DroneSingleton;
import drones.eventbus.EventBus;
import drones.eventbus.messages.GreetingsMessage;
import drones.insertion.InsertionClient;

import java.util.ArrayList;
import java.util.List;

public class GreetingsListener extends Thread {
    @Override
    public void run() {
        System.out.println("Insertion GreetingsListener waiting for GREETINGS message");
        while (!Thread.currentThread().isInterrupted()) {
            GreetingsMessage message = (GreetingsMessage) EventBus.getInstance().take("GREETINGS");
            if (message != null) {
                System.out.println("Insertion GreetingsListener GREETINGS message received");
                requestInsert(DroneSingleton.getInstance().getId());
            }
        }
    }

    private void requestInsert(int ownId) {
        System.out.println("InsertionLogic requestInsert started");
        List<DroneEntity> droneEntityList = new ArrayList<>(DroneSingleton.getInstance().getDroneList());
        droneEntityList.removeIf(d -> d.getId() >= ownId);

        if (droneEntityList.size() == 0) {
            System.out.println("InsertionLogic requestInsert ended no insertion needed");
            return;
        }

        DroneEntity toRequestInsertionTo = droneEntityList.get(droneEntityList.size() - 1);

        InsertionClient insertionClient = null;
        try {
            insertionClient = new InsertionClient(toRequestInsertionTo);
            insertionClient.start();
            insertionClient.join();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("InsertionLogic requestInsert thread exception verso " + toRequestInsertionTo.getId() + ": " + e.getLocalizedMessage());
        }

        if (insertionClient == null) {
            System.out.println("InsertionLogic requestInsert ended prematurely");
            return;
        }

        DroneSingleton.getInstance().setNext(insertionClient.getNext());

        System.out.println("InsertionLogic requestInsert ended");
    }
}
