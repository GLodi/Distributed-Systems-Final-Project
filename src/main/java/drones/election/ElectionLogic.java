package drones.election;

import drones.election.listeners.ElectionListener;

public class ElectionLogic extends Thread {

    public void run() {
        listenToElection();
    }

    private void listenToElection() {
        try {
            ElectionListener electionListener = new ElectionListener();
            electionListener.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ElectionLogic listenToElection ERROR: " + e.getLocalizedMessage());
        }
    }

}
