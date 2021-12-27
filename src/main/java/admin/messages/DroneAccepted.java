package admin.messages;

import admin.models.Drone;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class DroneAccepted {
    private List<Drone> drones;
    private int x;
    private int y;

    public DroneAccepted(List<Drone> drones, int x, int y) {
        this.drones = drones;
        this.x = x;
        this.y = y;
    }

    public List<Drone> getDrones() {
        return drones;
    }

    public void setDrones(List<Drone> drones) {
        this.drones = drones;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
