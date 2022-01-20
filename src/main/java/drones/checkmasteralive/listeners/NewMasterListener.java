package drones.checkmasteralive.listeners;

import drones.checkmasteralive.CheckMasterAliveRequestBeat;
import drones.eventbus.EventBus;
import drones.eventbus.messages.NewMasterMessage;

public class NewMasterListener extends Thread {
    @Override
    public void run() {
        System.out.println("CheckMasterAlive NewMasterListener waiting for NEW_MASTER message");
        while (!Thread.currentThread().isInterrupted()) {
            NewMasterMessage message = (NewMasterMessage) EventBus.getInstance().take("NEW_MASTER");
            if (message != null) {
                System.out.println("CheckMasterAlive NewMasterListener NEW_MASTER message received");
                requestBeat();
            }
        }
    }

    private void requestBeat() {
        try {
            CheckMasterAliveRequestBeat checkMasterAliveRequestBeat = new CheckMasterAliveRequestBeat();
            checkMasterAliveRequestBeat.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("CheckMasterAlive NewMasterListener thread exception : " + e.getLocalizedMessage());
        }
    }
}
