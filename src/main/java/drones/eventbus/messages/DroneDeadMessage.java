package drones.eventbus.messages;

import drones.eventbus.Message;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DroneDeadMessage extends Message {
    public int id;

    public DroneDeadMessage(int id) {
        this.kind = "DRONE_DEAD";
        this.id = id;
        this.timestamp = new SimpleDateFormat("dd/MM/yyyy_HH:mm:ss.SSS").format(Calendar.getInstance().getTime());
    }
}
