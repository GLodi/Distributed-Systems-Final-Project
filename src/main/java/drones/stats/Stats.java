package drones.stats;

import com.progetto.grpc.StatsOuterClass;
import drones.sensors.AverageMeasurement;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

public class Stats {
    public int orderId;
    public int droneId;
    public int x;
    public int y;
    public double kmRun;
    public int residualBattery;
    public List<AverageMeasurement> averageMeasurementList;
    public Timestamp arrivalTs;

    public Stats(int orderId, int droneId, int x, int y, int kmRun, List<AverageMeasurement> averageMeasurementList, int residualBattery) {
        this.orderId = orderId;
        this.droneId = droneId;
        this.x = x;
        this.y = y;
        this.kmRun = kmRun;
        this.averageMeasurementList = averageMeasurementList;
        this.residualBattery = residualBattery;
    }

    public Stats(StatsOuterClass.Stats stats) {
        this.orderId = stats.getOrderId();
        this.droneId = stats.getDroneId();
        this.x = stats.getNewX();
        this.y = stats.getNewY();
        this.kmRun = stats.getKmRun();
        this.residualBattery = stats.getResidualBattery();
        this.averageMeasurementList = stats.getAverageMeasurementsList().stream().map(AverageMeasurement::new).collect(Collectors.toList());
        this.arrivalTs = Timestamp.from(Instant.ofEpochSecond(stats.getArrivalTimestamp().getSeconds(), stats.getArrivalTimestamp().getNanos()));
    }

    public int getDroneId() {
        return droneId;
    }
}
