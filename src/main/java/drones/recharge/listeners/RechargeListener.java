package drones.recharge.listeners;

import drones.eventbus.EventBus;
import drones.eventbus.messages.RechargeMessage;
import drones.recharge.RechargeRecharge;

public class RechargeListener extends Thread {
    @Override
    public void run() {
        System.out.println("Recharge RechargeListener waiting for RECHARGE message");
        while (!Thread.currentThread().isInterrupted()) {
            RechargeMessage message = (RechargeMessage) EventBus.getInstance().take("RECHARGE");
            if (message != null) {
                System.out.println("Recharge RechargeListener RECHARGE message received");
                recharge();
            }
        }
    }

    private void recharge() {
        try {
            RechargeRecharge rechargeRecharge = new RechargeRecharge();
            rechargeRecharge.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Recharge RechargeListener thread exception : " + e.getLocalizedMessage());
        }
    }
}
