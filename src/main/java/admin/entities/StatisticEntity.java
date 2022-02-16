package admin.entities;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement
public class StatisticEntity {
    private long timestamp;
    private double averageDeliveryDone;
    private double averageKmTraveled;
    private double averagePollutionLevel;
    private double averageBatteryLevel;

    public StatisticEntity() {
    }

    public StatisticEntity(double averageDeliveryDone, double averageKmTraveled, double averagePollutionLevel, double averageBatteryLevel, long timestamp) {
        this.averageDeliveryDone = averageDeliveryDone;
        this.averageKmTraveled = averageKmTraveled;
        this.averagePollutionLevel = averagePollutionLevel;
        this.averageBatteryLevel = averageBatteryLevel;
        this.timestamp = timestamp;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public double getAverageDeliveryDone() {
        return averageDeliveryDone;
    }

    public void setAverageDeliveryDone(double averageDeliveryDone) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatisticEntity that = (StatisticEntity) o;
        return averageDeliveryDone == that.averageDeliveryDone && averageKmTraveled == that.averageKmTraveled && averagePollutionLevel == that.averagePollutionLevel && averageBatteryLevel == that.averageBatteryLevel && timestamp == that.timestamp;
    }

    @Override
    public int hashCode() {
        return Objects.hash(averageDeliveryDone, averageKmTraveled, averagePollutionLevel, averageBatteryLevel, timestamp);
    }
}
