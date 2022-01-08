package drones.eventbus;

import java.sql.Timestamp;

public abstract class Message {
    public Timestamp timestamp;
    public String kind;
}

