package admin.entities;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AverageDeliveriesEntity {
    private double averageDeliveries;

    public AverageDeliveriesEntity() {
    }

    public AverageDeliveriesEntity(double averageDeliveries) {
        this.averageDeliveries = averageDeliveries;
    }

    public double getAverageDeliveries() {
        return averageDeliveries;
    }

    public void setAverageDeliveries(double averageDeliveries) {
        this.averageDeliveries = averageDeliveries;
    }
}
