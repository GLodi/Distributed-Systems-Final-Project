package drones.election.listeners;

import admin.entities.DroneEntity;
import drones.election.ElectionElectedClient;
import drones.eventbus.EventBus;
import drones.eventbus.messages.ElectedMessage;

public class ElectedListener extends Thread {
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            ElectedMessage message = (ElectedMessage) EventBus.getInstance().take("ELECTED");
            if (message != null) {
                System.out.println("Election ElectedListener ELECTED message received");
                processElectedMessage(message.electedId, message.newMaster);
            }
        }
    }

    private void processElectedMessage(int electedId, DroneEntity newMaster) {
        try {
            ElectionElectedClient electionElectedClient = new ElectionElectedClient(electedId, newMaster);
            electionElectedClient.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Election processElectedMessage thread exception : " + e.getLocalizedMessage());
        }
    }
}
