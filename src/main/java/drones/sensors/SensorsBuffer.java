package drones.sensors;

import drones.sensors.sim.Buffer;
import drones.sensors.sim.Measurement;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class SensorsBuffer implements Buffer {

    private static SensorsBuffer instance;
    private final List<Measurement> measurementList = new ArrayList<>();
    private final List<AverageMeasurement> averageMeasurementList = new ArrayList<>();

    public synchronized static SensorsBuffer getInstance() {
        if (instance == null)
            instance = new SensorsBuffer();
        return instance;
    }

    @Override
    public synchronized void addMeasurement(Measurement m) {
        //System.out.println("SensorsBuffer addMeasurement: " + m);
        measurementList.add(m);
        if (measurementList.size() == 8) {
            averageMeasurementList.add(new AverageMeasurement(measurementList.stream().map(Measurement::getValue).reduce(Double::sum).get() / 8.0, Timestamp.from(Instant.now())));
            //System.out.println("SensorsBuffer reached 8th measurement. Average: " + averageMeasurementList.get(averageMeasurementList.size() - 1).getValue());
            measurementList.subList(0, 4).clear();
        }
    }

    @Override
    public synchronized List<Measurement> readAllAndClean() {
        List<Measurement> copy = new ArrayList<>(measurementList);
        measurementList.clear();
        return copy;
    }

    public synchronized List<AverageMeasurement> getAverageMeasurementList() {
        return averageMeasurementList;
    }
}
