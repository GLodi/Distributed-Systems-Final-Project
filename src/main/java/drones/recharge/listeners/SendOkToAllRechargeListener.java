package drones.recharge.listeners;

import drones.eventbus.EventBus;
import drones.eventbus.messages.SendOkToAllRechargeMessage;
import drones.recharge.Recharge;
import drones.recharge.RechargeQueue;
import drones.recharge.RechargeSendOkClient;

import java.util.List;

public class SendOkToAllRechargeListener extends Thread {
    @Override
    public void run() {
        System.out.println("Recharge SendOkToAllRechargeListener waiting for SEND_OK_TO_ALL_RECHARGE message");
        while (!Thread.currentThread().isInterrupted()) {
            SendOkToAllRechargeMessage message = (SendOkToAllRechargeMessage) EventBus.getInstance().take("SEND_OK_TO_ALL_RECHARGE");
            if (message != null) {
                System.out.println("Recharge SendOkToAllRechargeListener SEND_OK_TO_ALL_RECHARGE message received");
                sendOkToAll();
            }
        }
    }

    private void sendOkToAll() {
        List<Recharge> list = RechargeQueue.getInstance().reset();
        System.out.println("Recharge SendOkToAllRechargeListener LIST TO EMPTY: ");

        list.forEach(r -> System.out.println(r.getId()));
        for (Recharge r : list) {
            try {
                RechargeSendOkClient sendOkClient = new RechargeSendOkClient(r.getId());
                sendOkClient.start();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Recharge SendOkToAllRechargeListener thread exception : " + e.getLocalizedMessage());
            }
        }

    }
}
