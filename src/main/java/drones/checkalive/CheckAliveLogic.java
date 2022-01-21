package drones.checkalive;

import drones.checkalive.listeners.GreetedEveryoneListener;
import drones.checkalive.listeners.InsertionListener;

public class CheckAliveLogic extends Thread {
    @Override
    public void run() {
        listenToGreetedEveryoneMessages();
        listenToInsertionMessages();
    }

    private void listenToGreetedEveryoneMessages() {
        try {
            GreetedEveryoneListener greetedEveryoneListener = new GreetedEveryoneListener();
            greetedEveryoneListener.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("CheckAliveLogic listenToGreetedEveryoneMessages ERROR: " + e.getLocalizedMessage());
        }
    }

    private void listenToInsertionMessages() {
        try {
            InsertionListener insertionListener = new InsertionListener();
            insertionListener.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("CheckAliveLogic listenToInsertionMessages ERROR: " + e.getLocalizedMessage());
        }
    }
}
