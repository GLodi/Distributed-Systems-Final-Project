package drones.eventbus;

public class InsertionMessage extends Message {
    InsertionMessage() {
        this.kind = "INSERTION";
    }
}
