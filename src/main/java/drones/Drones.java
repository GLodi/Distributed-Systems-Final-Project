package drones;

/**
 * Created by civi on 26/04/16.
 */

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
    private List<Drone> dronelist;

    private Drones() {
        dronelist = new ArrayList<Drone>();
    }

    //singleton
    public synchronized static Drones getInstance() {
        if (instance == null)
            instance = new Drones();
        return instance;
    }

    public synchronized List<Drone> getDroneListCopy() {
        return new ArrayList<>(dronelist);
    }

    public synchronized void setDronelist(List<Drone> dronelist) {
        this.dronelist = dronelist;
    }

    public synchronized void add(Drone d) {
        dronelist.add(d);
    }

    public synchronized boolean remove(Drone d) {
        return dronelist.remove(d);
    }

    public Drone getById(int id) {
        List<Drone> droneListCopy = getDroneListCopy();
        for (Drone d : droneListCopy)
            if (d.getId() == id)
                return d;
        return null;
    }

}