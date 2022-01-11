package drones.eventbus;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ElectedMessage extends Message {
    private final int id; // id of elected drone

    public ElectedMessage(int id) {
        this.kind = "ELECTED";
        this.id = id;
        this.timestamp = new SimpleDateFormat("dd/MM/yyyy_HH:mm:ss.SSS").format(Calendar.getInstance().getTime());
    }
}
