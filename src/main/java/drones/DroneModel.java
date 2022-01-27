package drones;

import admin.entities.DroneEntity;

import java.util.List;

public class DroneModel {
    public final int id;
    public final String serverAddress;
    public final int port;
    public int x;
    public int y;
    public int battery;
    public List<DroneEntity> droneList;
    public DroneEntity master;
    public boolean delivering = false;
    public boolean recharging = false;

    public DroneModel(int id, int x, int y, String address, int port, List<DroneEntity> droneEntityList) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.serverAddress = address;
        this.droneList = droneEntityList;
        this.battery = 100 - 10 * id;
        this.port = port;
    }

    public static DroneEntity getEntity(DroneModel d) {
        return new DroneEntity(d.id, d.x, d.y, d.port, d.battery, d.delivering);
    }
}
