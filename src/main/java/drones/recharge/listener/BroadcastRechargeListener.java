package drones.recharge.listener;

import drones.eventbus.EventBus;
import drones.eventbus.messages.ConfirmedElectedMessage;
import drones.recharge.RechargeBroadcast;

public class BroadcastRechargeListener extends Thread {
    @Override
    public void run() {
        System.out.println("Recharge BroadcastRechargeListener waiting for BROADCAST_RECHARGE message");
        while (!Thread.currentThread().isInterrupted()) {
            ConfirmedElectedMessage message = (ConfirmedElectedMessage) EventBus.getInstance().take("BROADCAST_RECHARGE");
            if (message != null) {
                System.out.println("Recharge BroadcastRechargeListener CONFIRMED_ELECTED message received");
                broadcastRecharge();
            }
        }
    }

    private void broadcastRecharge() {
        try {
            RechargeBroadcast rechargeBroadcast = new RechargeBroadcast();
            rechargeBroadcast.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Recharge BroadcastRechargeListener thread exception : " + e.getLocalizedMessage());
        }
    }
}
