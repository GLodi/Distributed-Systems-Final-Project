package drones.eventbus.messages;

import admin.entities.DroneEntity;
import drones.eventbus.Message;
import drones.order.Order;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class SendOrderMessage extends Message {
    public List<DroneEntity> orderedList;
    public Order order;

    public SendOrderMessage(List<DroneEntity> orderedList, Order order) {
        this.kind = "SEND_ORDER";
        this.orderedList = orderedList;
        this.order = order;
        this.timestamp = new SimpleDateFormat("dd/MM/yyyy_HH:mm:ss.SSS").format(Calendar.getInstance().getTime());
    }
}
