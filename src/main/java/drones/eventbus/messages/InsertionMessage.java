package drones.eventbus.messages;

import admin.entities.DroneEntity;
import drones.eventbus.Message;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class InsertionMessage extends Message {
    public final DroneEntity newDrone;

    public InsertionMessage(DroneEntity newDrone) {
        this.kind = "INSERTION";
        this.newDrone = newDrone;
        this.timestamp = new SimpleDateFormat("dd/MM/yyyy_HH:mm:ss.SSS").format(Calendar.getInstance().getTime());
    }

}
