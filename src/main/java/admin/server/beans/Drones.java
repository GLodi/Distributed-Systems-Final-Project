package admin.server.beans;

import admin.entities.DroneEntity;

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
    private final List<DroneEntity> dronelist;

    private Drones() {
        dronelist = new ArrayList<DroneEntity>();
    }

    public synchronized static Drones getInstance() {
        if (instance == null)
            instance = new Drones();
        return instance;
    }

    public synchronized List<DroneEntity> getDroneList() {
        return new ArrayList<>(dronelist);
    }

    public synchronized List<DroneEntity> add(DroneEntity d) throws Exception {
        if (dronelist.stream().anyMatch(dd -> dd.getId() == d.getId())) {
            throw new Exception("Drone with same id already in grid");
        }

        int[] coordinates = findRandomCoordinates();

        d.setCoordinates(coordinates[0], coordinates[1]);
        dronelist.add(d);

        return dronelist;
    }

    private synchronized int[] findRandomCoordinates() {
        int[] res = new int[2];
        while (true) {
            int randomX = (int) (Math.random() * 10);
            int randomY = (int) (Math.random() * 10);
            if (!dronelist.stream().anyMatch(dd -> dd.getY() == randomX && dd.getY() == randomY)) {
                res[0] = randomX;
                res[1] = randomY;
                break;
            }
        }
        return res;
    }

    public synchronized boolean remove(DroneEntity d) {
        return dronelist.remove(d);
    }

    // NON METTERE SYNCHRONIZED:
    // chi vuole fare operazioni sulla lista dei droni, ne prende una copia
    // (in sync) e poi ci opera sopra senza bloccare gli altri.
    public DroneEntity getById(int id) {
        List<DroneEntity> droneEntityListCopy = getDroneList();
        for (DroneEntity d : droneEntityListCopy)
            if (d.getId() == id)
                return d;
        return null;
    }

}