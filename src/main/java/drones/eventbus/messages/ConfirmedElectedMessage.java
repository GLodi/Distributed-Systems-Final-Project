package drones.eventbus.messages;

import drones.eventbus.Message;

import java.text.SimpleDateFormat;
import java.util.Calendar;

// Sent after 2nd phase of chang and roberts: drone has broadcasted the fact that he's master
public class ConfirmedElectedMessage extends Message {
    public ConfirmedElectedMessage() {
        this.kind = "CONFIRMED_ELECTED";
        this.timestamp = new SimpleDateFormat("dd/MM/yyyy_HH:mm:ss.SSS").format(Calendar.getInstance().getTime());
    }
}
