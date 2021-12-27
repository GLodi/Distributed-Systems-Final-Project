package admin.server.beans;

import admin.messages.DroneAccepted;
import admin.models.Drone;
import admin.utils.Constants;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Drones {

    private static Drones instance;
    @XmlElement(name = "my_drones")
    private final List<Drone> dronelist;

    private Drones() {
        dronelist = new ArrayList<Drone>();
    }

    public synchronized static Drones getInstance() {
        if (instance == null)
            instance = new Drones();
        return instance;
    }

    public synchronized List<Drone> getDroneList() {
        return new ArrayList<>(dronelist);
    }

    public synchronized DroneAccepted add(Drone d) throws Exception {
        if (dronelist.stream().anyMatch(dd -> dd.equals(d))) {
            throw new Exception("Drone with same id already in grid");
        }

        int[] coordinates = findRandomCoordinates();

        d.setCoordinates(coordinates[0], coordinates[1]);
        dronelist.add(d);

        return new DroneAccepted(dronelist, coordinates[0], coordinates[1]);
    }

    private synchronized int[] findRandomCoordinates() {
        int[] res = new int[2];
        while (true) {
            int randomX = (int) (Math.random() * Constants.GRID_SIZE);
            int randomY = (int) (Math.random() * Constants.GRID_SIZE);
            if (!dronelist.stream().anyMatch(dd -> dd.getY() == randomX && dd.getY() == randomY)) {
                res[0] = randomX;
                res[1] = randomY;
                break;
            }
        }
        return res;
    }

    public synchronized boolean remove(Drone d) {
        return dronelist.remove(d);
    }

    // NON METTERE SYNCHRONIZED:
    // chi vuole fare operazioni sulla lista dei droni, ne prende una copia
    // (in sync) e poi ci opera sopra senza bloccare gli altri.
    public Drone getById(int id) {
        List<Drone> droneListCopy = getDroneList();
        for (Drone d : droneListCopy)
            if (d.getId() == id)
                return d;
        return null;
    }

}