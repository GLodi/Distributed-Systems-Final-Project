package drones;

import admin.entities.DroneEntity;

import java.util.List;

public class DroneModel {
    private final int id;
    private final int port;
    private final List<DroneEntity> droneList;
    private final int x;
    private final int y;
    private final int battery;
    private boolean isMaster;

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

    public List<DroneEntity> getDroneList() {
        return droneList;
    }

    public int getPort() {
        return port;
    }

}
