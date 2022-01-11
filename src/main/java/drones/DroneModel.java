package drones;

import admin.entities.DroneEntity;

import java.util.List;

public class DroneModel {
    public final int id;
    public final int port;
    public final int x;
    public final int y;
    public final int battery;
    public final List<DroneEntity> droneList;
    public boolean isMaster;
    public int nextId;

    public DroneModel(int id, int port, List<DroneEntity> droneEntityList) {
        this.id = id;
        this.x = droneEntityList.stream().filter(d -> d.getId() == id).findFirst().get().getX();
        this.y = droneEntityList.stream().filter(d -> d.getId() == id).findFirst().get().getY();
        this.droneList = droneEntityList;
        this.battery = 100;
        this.port = port;
    }

    public static DroneEntity getEntity(DroneModel d) {
        return new DroneEntity(d.id, d.x, d.y, d.port, d.battery);
    }
}
