package drones.stats;

import drones.stats.listeners.ConfirmedElectedListener;

public class StatsLogic extends Thread {
    @Override
    public void run() {
        loop();
        listenToConfirmedElected();
    }

    private void loop() {
        try {
            StatsLoop statsLoop = new StatsLoop();
            statsLoop.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Stats StatsLogic loop ERROR: " + e.getLocalizedMessage());
        }
    }

    private void listenToConfirmedElected() {
        try {
            ConfirmedElectedListener confirmedElectedListener = new ConfirmedElectedListener();
            confirmedElectedListener.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Stats StatsLogic listenToConfirmedElected ERROR: " + e.getLocalizedMessage());
        }
    }
}
