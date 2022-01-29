package drones.eventbus.messages;

import admin.entities.DroneEntity;
import drones.eventbus.Message;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SendOkRechargeMessage extends Message {
    public final DroneEntity droneEntity;

    public SendOkRechargeMessage(DroneEntity droneEntity) {
        this.kind = "SEND_OK_RECHARGE";
        this.droneEntity = droneEntity;
        this.timestamp = new SimpleDateFormat("dd/MM/yyyy_HH:mm:ss.SSS").format(Calendar.getInstance().getTime());
    }
}
