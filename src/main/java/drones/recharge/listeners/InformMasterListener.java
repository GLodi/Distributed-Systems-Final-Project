package drones.recharge.listeners;

import drones.eventbus.EventBus;
import drones.eventbus.messages.InformMasterRechargeMessage;
import drones.recharge.RechargeInformMasterClient;

public class InformMasterListener extends Thread {
    @Override
    public void run() {
        System.out.println("Recharge InformMasterListener waiting for INFORM_MASTER_RECHARGE message");
        while (!Thread.currentThread().isInterrupted()) {
            InformMasterRechargeMessage message = (InformMasterRechargeMessage) EventBus.getInstance().take("INFORM_MASTER_RECHARGE");
            if (message != null) {
                System.out.println("Recharge InformMasterListener INFORM_MASTER_RECHARGE message received");
                informMaster();
            }
        }
    }

    private void informMaster() {
        try {
            RechargeInformMasterClient rechargeInformMasterClient = new RechargeInformMasterClient();
            rechargeInformMasterClient.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Recharge InformMasterListener thread exception : " + e.getLocalizedMessage());
        }
    }
}
