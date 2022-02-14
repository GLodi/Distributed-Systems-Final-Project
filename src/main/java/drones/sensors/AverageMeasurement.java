package drones.sensors;

import com.progetto.grpc.StatsOuterClass;

import java.sql.Timestamp;
import java.time.Instant;

public class AverageMeasurement {
    private double value;
    private Timestamp timestamp;

    public AverageMeasurement(double value, Timestamp timestamp) {
        this.value = value;
        this.timestamp = timestamp;
    }

    public AverageMeasurement(StatsOuterClass.Stats.AverageMeasurement averageMeasurement) {
        this.value = averageMeasurement.getValue();
        this.timestamp = Timestamp.from(Instant.ofEpochSecond(averageMeasurement.getTimestamp().getSeconds(), averageMeasurement.getTimestamp().getNanos()));
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public StatsOuterClass.Stats.AverageMeasurement toProto() {
        return StatsOuterClass.Stats.AverageMeasurement.newBuilder().setTimestamp(com.google.protobuf.Timestamp.newBuilder().setSeconds(timestamp.getSeconds()).setNanos(timestamp.getNanos()).build()).setValue(value).build();
    }
}
