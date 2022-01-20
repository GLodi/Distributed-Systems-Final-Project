package drones.eventbus.messages;

import drones.eventbus.Message;

import java.text.SimpleDateFormat;
import java.util.Calendar;

// Sent when drone receives info that a new master has been elected
public class NewMasterMessage extends Message {
    public NewMasterMessage() {
        this.kind = "NEW_MASTER";
        this.timestamp = new SimpleDateFormat("dd/MM/yyyy_HH:mm:ss.SSS").format(Calendar.getInstance().getTime());
    }
}
