package drones.eventbus;

import java.util.ArrayList;


// Each thread will be notified on any new message, but will only process if interested.
public class EventBus {

    private static EventBus instance;

    public ArrayList<Message> buffer = new ArrayList<>();

    private EventBus() {
    }

    public synchronized static EventBus getInstance() {
        if (instance == null)
            instance = new EventBus();
        return instance;
    }

    public synchronized void put(Message message) {
        buffer.add(message);
        System.out.println("EVENTBUS message: " + message.kind + " at " + message.timestamp);
        notifyAll();
    }

    public synchronized Message take(String kind) {
        Message message = null;

        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (buffer.size() > 0 && buffer.get(buffer.size() - 1).kind.equals(kind)) {
            message = buffer.get(buffer.size() - 1);
        }

        return message;
    }
}
