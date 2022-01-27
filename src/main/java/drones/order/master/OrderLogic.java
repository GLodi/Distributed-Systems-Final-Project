package drones.order.master;

import drones.order.listeners.ConfirmedElectedListener;
import drones.order.listeners.NewOrderListener;
import drones.order.listeners.SendOrderListener;

public class OrderLogic extends Thread {
    public void run() {
        listenToConfirmedElected();
        listenToNewOrder();
        listenToSendOrder();
    }

    private void listenToConfirmedElected() {
        try {
            ConfirmedElectedListener confirmedElectedListener = new ConfirmedElectedListener();
            confirmedElectedListener.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Order listenToConfirmedElected ERROR: " + e.getLocalizedMessage());
        }
    }

    private void listenToNewOrder() {
        try {
            NewOrderListener newOrderListener = new NewOrderListener();
            newOrderListener.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Order listenToNewOrder ERROR: " + e.getLocalizedMessage());
        }
    }

    private void listenToSendOrder() {
        try {
            SendOrderListener sendOrderListener = new SendOrderListener();
            sendOrderListener.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Order listenToSendOrder ERROR: " + e.getLocalizedMessage());
        }
    }

    @Override
    public void interrupt() {
        super.interrupt();
    }
}