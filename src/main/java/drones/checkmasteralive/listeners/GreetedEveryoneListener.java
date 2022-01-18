package drones.checkmasteralive.listeners;

import drones.checkmasteralive.CheckMasterAliveRequestBeat;
import drones.eventbus.EventBus;
import drones.eventbus.messages.GreetedEveryoneMessage;

// On insertion in ring, request heartbeats from master
public class GreetedEveryoneListener extends Thread {
    @Override
    public void run() {
        System.out.println("CheckMasterAlive GreetedEveryoneListener waiting for GREETED_EVERYONE message");
        while (!Thread.currentThread().isInterrupted()) {
            GreetedEveryoneMessage message = (GreetedEveryoneMessage) EventBus.getInstance().take("GREETED_EVERYONE");
            if (message != null) {
                System.out.println("CheckMasterAlive GreetedEveryoneListener GREETED_EVERYONE message received");
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
            System.out.println("CheckMasterAlive GreetedEveryoneListener thread exception : " + e.getLocalizedMessage());
        }
    }
}
