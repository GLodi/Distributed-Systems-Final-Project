package drones.eventbus.messages;

import admin.entities.DroneEntity;
import drones.eventbus.Message;

import java.text.SimpleDateFormat;
import java.util.Calendar;

// called when the drone is requested to forward an elected message to the first neighbour available
public class ElectedMessage extends Message {
    public final int electedId;
    public final DroneEntity newMaster;

    public ElectedMessage(int electedId, DroneEntity newMaster) {
        this.electedId = electedId;
        this.newMaster = newMaster;
        this.kind = "ELECTED";
        this.timestamp = new SimpleDateFormat("dd/MM/yyyy_HH:mm:ss.SSS").format(Calendar.getInstance().getTime());
    }
}
