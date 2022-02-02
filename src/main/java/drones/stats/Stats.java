package drones.stats;

import com.progetto.grpc.StatsOuterClass;

import java.sql.Timestamp;
import java.time.Instant;

public class Stats {
    public int orderId;
    public int droneId;
    public int x;
    public int y;
    public double kmRun;
    public int residualBattery;
    public Timestamp arrivalTs;

    public Stats(int orderId, int droneId, int x, int y, int kmRun, int residualBattery) {
        this.orderId = orderId;
        this.droneId = droneId;
        this.x = x;
        this.y = y;
        this.kmRun = kmRun;
        this.residualBattery = residualBattery;
    }

    public Stats(StatsOuterClass.Stats stats) {
        this.orderId = stats.getOrderId();
        this.droneId = stats.getDroneId();
        this.x = stats.getNewX();
        this.y = stats.getNewY();
        this.kmRun = stats.getKmRun();
        this.arrivalTs = Timestamp.from(Instant.ofEpochSecond(stats.getArrivalTimestamp().getSeconds(), stats.getArrivalTimestamp().getNanos()));
    }
}
