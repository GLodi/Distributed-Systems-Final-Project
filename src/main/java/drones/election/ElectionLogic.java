package drones.election;

import drones.election.listeners.ElectedListener;
import drones.election.listeners.ElectionListener;

public class ElectionLogic extends Thread {

    public void run() {
        listenToElection();
        listenToElected();
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

    private void listenToElected() {
        try {
            ElectedListener electedListener = new ElectedListener();
            electedListener.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ElectionLogic listenToElected ERROR: " + e.getLocalizedMessage());
        }
    }

}
