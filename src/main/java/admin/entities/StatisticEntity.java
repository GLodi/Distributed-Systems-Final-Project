package admin.entities;

import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Timestamp;
import java.util.Objects;

@XmlRootElement
public class StatisticEntity {
    private int deliveryDone;
    private int kmTraveled;
    private int pollutionLevel;
    private int batteryLevel;
    private Timestamp ts;

    public StatisticEntity(int deliveryDone, int kmTraveled, int pollutionLevel, int batteryLevel, Timestamp ts) {
        this.deliveryDone = deliveryDone;
        this.kmTraveled = kmTraveled;
        this.pollutionLevel = pollutionLevel;
        this.batteryLevel = batteryLevel;
        this.ts = ts;
    }

    public Timestamp getTs() {
        return ts;
    }

    public void setTs(Timestamp ts) {
        this.ts = ts;
    }

    public int getDeliveryDone() {
        return deliveryDone;
    }

    public void setDeliveryDone(int deliveryDone) {
        this.deliveryDone = deliveryDone;
    }

    public int getKmTraveled() {
        return kmTraveled;
    }

    public void setKmTraveled(int kmTraveled) {
        this.kmTraveled = kmTraveled;
    }

    public int getPollutionLevel() {
        return pollutionLevel;
    }

    public void setPollutionLevel(int pollutionLevel) {
        this.pollutionLevel = pollutionLevel;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatisticEntity stats = (StatisticEntity) o;
        return deliveryDone == stats.deliveryDone && kmTraveled == stats.kmTraveled && pollutionLevel == stats.pollutionLevel && batteryLevel == stats.batteryLevel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(deliveryDone, kmTraveled, pollutionLevel, batteryLevel);
    }

}
