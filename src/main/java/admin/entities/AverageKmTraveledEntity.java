package admin.entities;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AverageKmTraveledEntity {
    private double kmTraveled;

    public AverageKmTraveledEntity() {
    }

    public AverageKmTraveledEntity(double kmTraveled) {
        this.kmTraveled = kmTraveled;
    }

    public double getKmTraveled() {
        return kmTraveled;
    }

    public void setKmTraveled(double kmTraveled) {
        this.kmTraveled = kmTraveled;
    }
}
