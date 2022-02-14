package drones.recharge.listeners;

import drones.eventbus.EventBus;
import drones.eventbus.messages.BroadcastRechargeMessage;
import drones.recharge.RechargeBroadcast;

public class BroadcastRechargeListener extends Thread {
    @Override
    public void run() {
        System.out.println("Recharge BroadcastRechargeListener waiting for BROADCAST_RECHARGE message");
        while (!Thread.currentThread().isInterrupted()) {
            BroadcastRechargeMessage message = (BroadcastRechargeMessage) EventBus.getInstance().take("BROADCAST_RECHARGE");
            if (message != null) {
                System.out.println("Recharge BroadcastRechargeListener BROADCAST_RECHARGE message received");
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
