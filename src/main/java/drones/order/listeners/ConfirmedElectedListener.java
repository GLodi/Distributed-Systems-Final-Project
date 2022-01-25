package drones.order.listeners;

import drones.DroneSingleton;
import drones.eventbus.EventBus;
import drones.eventbus.messages.ConfirmedElectedMessage;

public class ConfirmedElectedListener extends Thread {
    @Override
    public void run() {
        System.out.println("Order ConfirmedElectedListener waiting for CONFIRMED_ELECTED message");
        while (!Thread.currentThread().isInterrupted()) {
            ConfirmedElectedMessage message = (ConfirmedElectedMessage) EventBus.getInstance().take("CONFIRMED_ELECTED");
            if (message != null) {
                System.out.println("Order ConfirmedElectedListener CONFIRMED_ELECTED message received");
                subToMQTT();
            }
        }
    }

    private void subToMQTT() {
        DroneSingleton.getInstance().startOrderMQTTThread();
    }
}
