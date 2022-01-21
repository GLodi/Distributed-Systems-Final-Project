package drones.checkalive.listeners;

import drones.checkalive.CheckAliveEveryone;
import drones.eventbus.EventBus;
import drones.eventbus.messages.GreetedEveryoneMessage;

// On insertion in ring, request heartbeats from everyone
public class GreetedEveryoneListener extends Thread {
    @Override
    public void run() {
        System.out.println("CheckAlive GreetedEveryoneListener waiting for GREETED_EVERYONE message");
        while (!Thread.currentThread().isInterrupted()) {
            GreetedEveryoneMessage message = (GreetedEveryoneMessage) EventBus.getInstance().take("GREETED_EVERYONE");
            if (message != null) {
                System.out.println("CheckAlive GreetedEveryoneListener GREETED_EVERYONE message received");
                beatEveryone();
            }
        }
    }

    private void beatEveryone() {
        try {
            CheckAliveEveryone checkAliveEveryone = new CheckAliveEveryone();
            checkAliveEveryone.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("CheckAlive GreetedEveryoneListener thread exception : " + e.getLocalizedMessage());
        }
    }
}
