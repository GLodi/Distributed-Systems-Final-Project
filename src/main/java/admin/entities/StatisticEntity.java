package admin.entities;

import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Timestamp;
import java.util.Objects;

@XmlRootElement
public class StatisticEntity {
    private double averageDeliveryDone;
    private double averageKmTraveled;
    private double averagePollutionLevel;
    private double averageBatteryLevel;
    private Timestamp ts;

    public StatisticEntity(double averageDeliveryDone, double averageKmTraveled, double averagePollutionLevel, double averageBatteryLevel, Timestamp ts) {
        this.averageDeliveryDone = averageDeliveryDone;
        this.averageKmTraveled = averageKmTraveled;
        this.averagePollutionLevel = averagePollutionLevel;
        this.averageBatteryLevel = averageBatteryLevel;
        this.ts = ts;
    }

    public double getAverageDeliveryDone() {
        return averageDeliveryDone;
    }

    public void setAverageDeliveryDone(int averageDeliveryDone) {
        this.averageDeliveryDone = averageDeliveryDone;
    }

    public double getAverageKmTraveled() {
        return averageKmTraveled;
    }

    public void setAverageKmTraveled(double averageKmTraveled) {
        this.averageKmTraveled = averageKmTraveled;
    }

    public double getAveragePollutionLevel() {
        return averagePollutionLevel;
    }

    public void setAveragePollutionLevel(double averagePollutionLevel) {
        this.averagePollutionLevel = averagePollutionLevel;
    }

    public double getAverageBatteryLevel() {
        return averageBatteryLevel;
    }

    public void setAverageBatteryLevel(double averageBatteryLevel) {
        this.averageBatteryLevel = averageBatteryLevel;
    }

    public Timestamp getTs() {
        return ts;
    }

    public void setTs(Timestamp ts) {
        this.ts = ts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatisticEntity that = (StatisticEntity) o;
        return averageDeliveryDone == that.averageDeliveryDone && averageKmTraveled == that.averageKmTraveled && averagePollutionLevel == that.averagePollutionLevel && averageBatteryLevel == that.averageBatteryLevel && Objects.equals(ts, that.ts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(averageDeliveryDone, averageKmTraveled, averagePollutionLevel, averageBatteryLevel, ts);
    }
}
