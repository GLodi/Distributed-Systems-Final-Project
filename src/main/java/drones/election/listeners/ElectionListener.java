package drones.election.listeners;

import drones.election.ElectionElectionClient;
import drones.eventbus.EventBus;
import drones.eventbus.messages.ElectionMessage;

public class ElectionListener extends Thread {
    @Override
    public void run() {
        System.out.println("Election ElectionListener waiting for ELECTION message");
        while (!Thread.currentThread().isInterrupted()) {
            ElectionMessage message = (ElectionMessage) EventBus.getInstance().take("ELECTION");
            if (message != null) {
                System.out.println("Election ElectionListener ELECTION message received");
                processElectionMessage(message.electionId);
            }
        }
    }

    private void processElectionMessage(int electionId) {
        try {
            ElectionElectionClient electionElectionClient = new ElectionElectionClient(electionId);
            electionElectionClient.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Election processElectionMessage thread exception : " + e.getLocalizedMessage());
        }
    }
}
