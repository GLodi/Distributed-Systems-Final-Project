package drones.eventbus.messages;

import drones.eventbus.Message;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class InformMasterRechargeMessage extends Message {
    public InformMasterRechargeMessage() {
        this.kind = "INFORM_MASTER_RECHARGE";
        this.timestamp = new SimpleDateFormat("dd/MM/yyyy_HH:mm:ss.SSS").format(Calendar.getInstance().getTime());
    }
}
