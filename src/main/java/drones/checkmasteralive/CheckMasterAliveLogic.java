package drones.checkmasteralive;

import drones.checkmasteralive.listeners.GreetedEveryoneListener;
import drones.checkmasteralive.listeners.NewMasterListener;

public class CheckMasterAliveLogic extends Thread {
    @Override
    public void run() {
        listenToGreetedEveryoneMessages();
        listenToNewMasterMessages();
    }

    private void listenToGreetedEveryoneMessages() {
        try {
            GreetedEveryoneListener greetedEveryoneListener = new GreetedEveryoneListener();
            greetedEveryoneListener.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("CheckMasterAliveLogic listenToGreetedEveryoneMessages ERROR: " + e.getLocalizedMessage());
        }
    }

    private void listenToNewMasterMessages() {
        try {
            NewMasterListener newMasterListener = new NewMasterListener();
            newMasterListener.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("CheckMasterAliveLogic listenToNewMasterMessages ERROR: " + e.getLocalizedMessage());
        }
    }
}
