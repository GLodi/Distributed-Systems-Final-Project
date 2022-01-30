package drones.eventbus.messages;

import drones.eventbus.Message;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SendOkToAllRechargeMessage extends Message {
    public SendOkToAllRechargeMessage() {
        this.kind = "SEND_OK_TO_ALL_RECHARGE";
        this.timestamp = new SimpleDateFormat("dd/MM/yyyy_HH:mm:ss.SSS").format(Calendar.getInstance().getTime());
    }
}
