package drones.stats;

public class StatsLogic extends Thread {
    @Override
    public void run() {
        loop();
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
}
