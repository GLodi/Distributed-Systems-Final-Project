package drones.eventbus.messages;

import drones.eventbus.Message;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SendOkRechargeMessage extends Message {
    public final int id;

    public SendOkRechargeMessage(int id) {
        this.kind = "SEND_OK_RECHARGE";
        this.id = id;
        this.timestamp = new SimpleDateFormat("dd/MM/yyyy_HH:mm:ss.SSS").format(Calendar.getInstance().getTime());
    }
}
