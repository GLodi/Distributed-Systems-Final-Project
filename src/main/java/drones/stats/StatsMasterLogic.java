package drones.stats;

public class StatsMasterLogic extends Thread {
    @Override
    public void run() {
        sendAveragesToServer();
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
