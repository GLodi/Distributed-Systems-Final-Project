package drones.order.listeners;

import drones.eventbus.EventBus;
import drones.eventbus.messages.NewOrderMessage;
import drones.order.master.OrderChooseDroneForDelivery;

public class NewOrderListener extends Thread {
    @Override
    public void run() {
        System.out.println("Order NewOrderListener waiting for NEW_ORDER message");
        while (!Thread.currentThread().isInterrupted()) {
            NewOrderMessage message = (NewOrderMessage) EventBus.getInstance().take("NEW_ORDER");
            if (message != null) {
                System.out.println("Order NewOrderListener NEW_ORDER message received");
                chooseDroneForDelivery();
            }
        }
    }

    private void chooseDroneForDelivery() {
        try {
            OrderChooseDroneForDelivery orderChooseDroneForDelivery = new OrderChooseDroneForDelivery();
            orderChooseDroneForDelivery.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Order chooseDroneForDelivery thread exception : " + e.getLocalizedMessage());
        }
    }
}
