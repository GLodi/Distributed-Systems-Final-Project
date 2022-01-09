package drones.eventbus;

public class GreetingsMessage extends Message {
    GreetingsMessage() {
        this.kind = "GREETINGS";
    }
}
