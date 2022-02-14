package drones.stats.listeners;

import drones.eventbus.EventBus;
import drones.eventbus.messages.ConfirmedElectedMessage;
import drones.stats.StatsMasterLoop;

public class ConfirmedElectedListener extends Thread {
    @Override
    public void run() {
        System.out.println("Stats ConfirmedElectedListener waiting for CONFIRMED_ELECTED message");
        while (!Thread.currentThread().isInterrupted()) {
            ConfirmedElectedMessage message = (ConfirmedElectedMessage) EventBus.getInstance().take("CONFIRMED_ELECTED");
            if (message != null) {
                System.out.println("Stats ConfirmedElectedListener CONFIRMED_ELECTED message received");
                sendAveragesToServer();
            }
        }
    }

    private void sendAveragesToServer() {
        try {
            StatsMasterLoop statsMasterLoop = new StatsMasterLoop();
            statsMasterLoop.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Stats StatsMasterLogic loop ERROR: " + e.getLocalizedMessage());
        }
    }
}
