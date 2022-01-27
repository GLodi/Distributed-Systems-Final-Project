package drones.recharge;

import drones.recharge.listener.BroadcastRechargeListener;

public class RechargeLogic extends Thread {
    @Override
    public void run() {
        listenToBroadcastRecharge();
    }

    private void listenToBroadcastRecharge() {
        try {
            BroadcastRechargeListener broadcastRechargeListener = new BroadcastRechargeListener();
            broadcastRechargeListener.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Recharge RechargeLogic listenToBroadcastRecharge ERROR: " + e.getLocalizedMessage());
        }
    }
}
