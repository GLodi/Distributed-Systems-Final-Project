package drones.eventbus.messages;

import drones.eventbus.Message;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ElectionMessage extends Message {
    public final int electionId;

    public ElectionMessage(int electionId) {
        this.electionId = electionId;
        this.kind = "ELECTION";
        this.timestamp = new SimpleDateFormat("dd/MM/yyyy_HH:mm:ss.SSS").format(Calendar.getInstance().getTime());
    }
}
