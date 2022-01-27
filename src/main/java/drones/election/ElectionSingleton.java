package drones.election;

public class ElectionSingleton {

    private static ElectionSingleton instance;
    private boolean participant = false;

    public synchronized static ElectionSingleton getInstance() {
        if (instance == null)
            instance = new ElectionSingleton();
        return instance;
    }

    public synchronized void setParticipant() {
        participant = true;
    }

    public synchronized void setNonParticipant() {
        participant = false;
    }

    public synchronized boolean isParticipant() {
        return participant;
    }
}
