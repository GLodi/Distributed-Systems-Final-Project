package drones.order.master;

import drones.order.Order;

import java.util.ArrayList;

public class OrderQueue {
    private static OrderQueue instance;

    private final ArrayList<Order> queue = new ArrayList<>();

    public synchronized static OrderQueue getInstance() {
        if (instance == null)
            instance = new OrderQueue();
        return instance;
    }

    public synchronized int size() {
        return queue.size();
    }

    public synchronized void put(Order o) {
        queue.add(o);
    }

    public synchronized Order pop() {
        return queue.remove(0);
    }

    public synchronized void clear() {
        queue.clear();
    }
}
