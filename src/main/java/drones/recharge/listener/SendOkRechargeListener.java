package drones.recharge.listener;

import drones.eventbus.EventBus;
import drones.eventbus.messages.SendOkRechargeMessage;
import drones.recharge.RechargeSendOkClient;

public class SendOkRechargeListener extends Thread {
    @Override
    public void run() {
        System.out.println("Recharge SendOkRechargeListener waiting for SEND_OK_RECHARGE message");
        while (!Thread.currentThread().isInterrupted()) {
            SendOkRechargeMessage message = (SendOkRechargeMessage) EventBus.getInstance().take("SEND_OK_RECHARGE");
            if (message != null) {
                System.out.println("Recharge SendOkRechargeListener SEND_OK_RECHARGE message received");
                sendOk(message);
            }
        }
    }

    private void sendOk(SendOkRechargeMessage message) {
        try {
            RechargeSendOkClient sendOkClient = new RechargeSendOkClient(message.id);
            sendOkClient.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Recharge SendOkRechargeListener thread exception : " + e.getLocalizedMessage());
        }
    }
}
