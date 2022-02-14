package drones.order.client;

import com.progetto.grpc.OrderServiceGrpc.OrderServiceImplBase;
import com.progetto.grpc.OrderServiceOuterClass.OrderRequest;
import com.progetto.grpc.OrderServiceOuterClass.OrderResponse;
import com.progetto.grpc.StatsOuterClass;
import drones.DroneSingleton;
import drones.sensors.AverageMeasurement;
import drones.sensors.SensorsBuffer;
import drones.stats.Stats;
import drones.stats.StatsSingleton;
import io.grpc.stub.StreamObserver;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl extends OrderServiceImplBase {

    @Override
    public void makeDelivery(OrderRequest request, StreamObserver<OrderResponse> responseObserver) {
        System.out.println("Order OrderServiceImpl received order " + request.getOrder().getId());

        if (DroneSingleton.getInstance().getBattery() < 15 || DroneSingleton.getInstance().isRecharging()) {
            responseObserver.onError(new Throwable("Order OrderServiceImpl REFUSE WORK"));
            return;
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        double kmRun = distance(
                DroneSingleton.getInstance().getX(), DroneSingleton.getInstance().getY(),
                request.getOrder().getPickupX(), request.getOrder().getPickupY()) +
                distance(request.getOrder().getPickupX(), request.getOrder().getPickupY(),
                        request.getOrder().getDropX(), request.getOrder().getPickupY());

        int residualBattery = DroneSingleton.getInstance().makeDelivery(request.getOrder().getDropX(), request.getOrder().getDropY());

        List<AverageMeasurement> averages = new ArrayList<>();
        if (StatsSingleton.getInstance().getList().size() != 0) {
            Timestamp lastDelivery = StatsSingleton.getInstance().getList().get(StatsSingleton.getInstance().getList().size() - 1).arrivalTs;
            SensorsBuffer.getInstance().readAllAndClean();
            SensorsBuffer.getInstance().getAverageMeasurementList().stream().filter(am ->
                    am.getTimestamp().toInstant().isAfter(lastDelivery.toInstant())).forEach(averages::add);
        }
        List<StatsOuterClass.Stats.AverageMeasurement> protoList = new ArrayList<>();
        averages.forEach(a -> protoList.add(a.toProto()));

        Instant time = Instant.now();
        StatsOuterClass.Stats stats = StatsOuterClass.Stats.newBuilder()
                .setArrivalTimestamp(com.google.protobuf.Timestamp.newBuilder().setSeconds(time.getEpochSecond()).setNanos(time.getNano()).build())
                .setNewX(DroneSingleton.getInstance().getX())
                .setNewY(DroneSingleton.getInstance().getY())
                .setKmRun(kmRun)
                .setOrderId(request.getOrder().getId())
                .setDroneId(DroneSingleton.getInstance().getId())
                .setResidualBattery(residualBattery)
                .addAllAverageMeasurements(protoList)
                .build();
        responseObserver.onNext(OrderResponse.newBuilder().setStats(stats).build());

        System.out.println("Order OrderServiceImpl done delivering " + request.getOrder().getId());

        StatsSingleton.getInstance().add(new Stats(stats));

        responseObserver.onCompleted();

        if (DroneSingleton.getInstance().getBattery() < 15) {
            DroneSingleton.getInstance().interruptAll();
            System.out.println("Order OrderServiceImpl BATTERY DEAD AFTER ORDER " + request.getOrder().getId());
        }
    }

    private double distance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
}
