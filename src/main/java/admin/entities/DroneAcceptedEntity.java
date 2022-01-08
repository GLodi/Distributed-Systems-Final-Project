package admin.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class DroneAcceptedEntity {
    private List<DroneEntity> droneEntities;
    private int x;
    private int y;

    public DroneAcceptedEntity() {
    }

    public DroneAcceptedEntity(List<DroneEntity> droneEntities, int x, int y) {
        this.droneEntities = droneEntities;
        this.x = x;
        this.y = y;
    }

    @XmlElementWrapper(name = "drone_entities")
    @XmlElement(name = "drone_entity", type = DroneEntity.class)
    public List<DroneEntity> getDrones() {
        return droneEntities;
    }

    public void setDrones(List<DroneEntity> droneEntities) {
        this.droneEntities = droneEntities;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
