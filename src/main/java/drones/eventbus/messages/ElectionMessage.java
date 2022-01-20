package drones.eventbus.messages;

import drones.eventbus.Message;

import java.text.SimpleDateFormat;
import java.util.Calendar;

// called when the drone is requested to forward an election message to the first neightbour available
public class ElectionMessage extends Message {
    public final int electionId;
    public final int battery;

    public ElectionMessage(int electionId, int battery) {
        this.electionId = electionId;
        this.battery = battery;
        this.kind = "ELECTION";
        this.timestamp = new SimpleDateFormat("dd/MM/yyyy_HH:mm:ss.SSS").format(Calendar.getInstance().getTime());
    }
}
