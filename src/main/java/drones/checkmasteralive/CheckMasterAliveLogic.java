package drones.checkmasteralive;

import drones.checkmasteralive.listeners.GreetedEveryoneListener;

public class CheckMasterAliveLogic extends Thread {
    @Override
    public void run() {
        listenToHeartbeatRequests();
    }

    private void listenToHeartbeatRequests() {
        try {
            GreetedEveryoneListener greetedEveryoneListener = new GreetedEveryoneListener();
            greetedEveryoneListener.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("CheckMasterAliveLogic listenToHeartbeatRequests ERROR: " + e.getLocalizedMessage());
        }
    }
}
