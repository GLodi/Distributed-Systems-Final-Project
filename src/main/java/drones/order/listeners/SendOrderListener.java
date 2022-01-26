package drones.order.listeners;

import drones.eventbus.EventBus;
import drones.eventbus.messages.SendOrderMessage;
import drones.order.master.OrderClient;

public class SendOrderListener extends Thread {
    @Override
    public void run() {
        System.out.println("Order SendOrderListener waiting for SEND_ORDER message");
        while (!Thread.currentThread().isInterrupted()) {
            SendOrderMessage message = (SendOrderMessage) EventBus.getInstance().take("SEND_ORDER");
            if (message != null) {
                System.out.println("Order SendOrderListener SEND_ORDER message received");
                sendOrderToDrone(message);
            }
        }
    }

    private void sendOrderToDrone(SendOrderMessage message) {
        try {
            OrderClient orderClient = new OrderClient(message);
            orderClient.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Order chooseDroneForDelivery thread exception : " + e.getLocalizedMessage());
        }
    }
}
