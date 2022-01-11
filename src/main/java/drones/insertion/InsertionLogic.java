package drones.insertion;

import admin.entities.DroneEntity;
import drones.DroneSingleton;

import java.util.ArrayList;
import java.util.List;

public class InsertionLogic extends Thread {
    @Override
    public void run() {
        DroneEntity own = DroneSingleton.getInstance().getDroneEntity();

        requestInsert(own.getId());
        startServer();
    }

    private void requestInsert(int id) {
        System.out.println("InsertionLogic requestInsert started");
        List<DroneEntity> droneEntityList = new ArrayList<>(DroneSingleton.getInstance().getDroneList());
        droneEntityList.removeIf(d -> d.getId() >= id);

        if (droneEntityList.size() == 0) {
            System.out.println("InsertionLogic requestInsert ended no insertion needed");
            return;
        }

        DroneEntity toRequestInsertionTo = droneEntityList.get(droneEntityList.size() - 1);

        try {
            InsertionClient insertionClient = new InsertionClient(id, toRequestInsertionTo);
            insertionClient.run();
        } catch (Exception e) {
            System.out.println("InsertionLogic requestInsert ESECUZIONE FALLITA verso " + toRequestInsertionTo.getId());
        }

        System.out.println("InsertionLogic requestInsert ended");
    }

    private void startServer() {

    }
}
