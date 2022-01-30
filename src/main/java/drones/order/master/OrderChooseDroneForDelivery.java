package drones.order.master;

import admin.entities.DroneEntity;
import drones.DroneSingleton;
import drones.eventbus.EventBus;
import drones.eventbus.messages.SendOrderMessage;
import drones.order.Order;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class OrderChooseDroneForDelivery extends Thread {
    @Override
    public void run() {
        List<DroneEntity> candidates = new ArrayList<>(DroneSingleton.getInstance().getDroneList());
        candidates.add(DroneSingleton.getInstance().getDroneEntity());

        candidates.removeIf(DroneEntity::isDelivering);

        if (!candidates.isEmpty()) {
            Order o = OrderQueue.getInstance().pop();
            candidates.sort(new Comparator<DroneEntity>() {
                @Override
                public int compare(DroneEntity o1, DroneEntity o2) {
                    int result = Double.compare(distance(o1, o.getPickupX(), o.getPickupY()), distance(o2, o.getPickupX(), o.getPickupY()));
                    if (result == 0) {
                        result = Double.compare(o2.getBattery(), o1.getBattery());
                    }
                    if (result == 0) {
                        result = Double.compare(o2.getId(), o1.getId());
                    }
                    return result;
                }
            });

            System.out.println("Order OrderChooseDroneForDelivery candidates for order " + o.getId());
            candidates.forEach(d -> System.out.println(d.getId()));

            EventBus.getInstance().put(new SendOrderMessage(candidates, o));
        } else {
            System.out.println("Order OrderChooseDroneForDelivery no available candidates");
        }
    }

    private double distance(DroneEntity d, int x, int y) {
        return Math.sqrt(Math.pow(d.getX() - x, 2) + Math.pow(d.getY() - y, 2));
    }
}
