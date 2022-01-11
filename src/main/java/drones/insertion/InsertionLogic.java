package drones.insertion;

import drones.insertion.listeners.GreetingsListener;

public class InsertionLogic extends Thread {
    @Override
    public void run() {
        listenToAllGreetings();
    }

    private void listenToAllGreetings() {
        try {
            GreetingsListener greetingsListener = new GreetingsListener();
            greetingsListener.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("InsertionLogic listenToAllGreetings ERROR: " + e.getLocalizedMessage());
        }
    }
}
