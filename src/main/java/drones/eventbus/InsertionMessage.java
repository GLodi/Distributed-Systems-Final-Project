package drones.eventbus;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class InsertionMessage extends Message {
    public final int id; // id of inserted drone

    public InsertionMessage(int id) {
        this.kind = "INSERTION";
        this.id = id;
        this.timestamp = new SimpleDateFormat("dd/MM/yyyy_HH:mm:ss.SSS").format(Calendar.getInstance().getTime());
    }
}
