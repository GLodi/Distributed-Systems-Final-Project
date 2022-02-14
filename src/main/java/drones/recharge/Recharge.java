package drones.recharge;

import java.sql.Timestamp;

public class Recharge {
    private int id;
    private Timestamp timestamp;

    public Recharge(int id, Timestamp timestamp) {
        this.id = id;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
