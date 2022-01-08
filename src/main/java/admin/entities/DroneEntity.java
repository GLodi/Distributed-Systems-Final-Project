package admin.entities;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement
public class DroneEntity {
    private int id;
    private int x;
    private int y;
    private int port;
    private int battery;

    public DroneEntity() {
    }

    public DroneEntity(int id, int port) {
        this.id = id;
        this.port = port;
    }

    public DroneEntity(int id, int x, int y, int port, int battery) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.port = port;
        this.battery = battery;
    }

    public void setCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getId() {
        return id;
    }

    public int getPort() {
        return port;
    }

    public int getBattery() {
        return battery;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DroneEntity droneEntity = (DroneEntity) o;
        return id == droneEntity.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
