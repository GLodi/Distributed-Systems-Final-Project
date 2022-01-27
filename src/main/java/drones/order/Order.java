package drones.order;

import com.progetto.grpc.OrderOuterClass;

public class Order {
    private int id;
    private int pickupX;
    private int pickupY;
    private int dropX;
    private int dropY;

    public Order(int id, int pickupX, int pickupY, int dropX, int dropY) {
        this.id = id;
        this.pickupX = pickupX;
        this.pickupY = pickupY;
        this.dropX = dropX;
        this.dropY = dropY;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPickupX() {
        return pickupX;
    }

    public void setPickupX(int pickupX) {
        this.pickupX = pickupX;
    }

    public int getPickupY() {
        return pickupY;
    }

    public void setPickupY(int pickupY) {
        this.pickupY = pickupY;
    }

    public int getDropX() {
        return dropX;
    }

    public void setDropX(int dropX) {
        this.dropX = dropX;
    }

    public int getDropY() {
        return dropY;
    }

    public void setDropY(int dropY) {
        this.dropY = dropY;
    }

    public OrderOuterClass.Order toOrder() {
        return OrderOuterClass.Order.newBuilder().setId(id).setDropX(dropX).setDropY(dropY).setPickupX(pickupX).setPickupY(pickupY).build();
    }
}
