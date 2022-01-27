package drones.eventbus.messages;

import drones.eventbus.Message;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BroadcastRechargeMessage extends Message {
    public BroadcastRechargeMessage() {
        this.kind = "BROADCAST_RECHARGE";
        this.timestamp = new SimpleDateFormat("dd/MM/yyyy_HH:mm:ss.SSS").format(Calendar.getInstance().getTime());
    }
}
