package drones.recharge.listener;

import drones.eventbus.EventBus;
import drones.eventbus.messages.DroneDeadMessage;
import drones.eventbus.messages.RechargeMessage;
import drones.recharge.RechargeQueue;
import drones.recharge.RechargeStateEnum;

public class DroneDeadListener extends Thread {
    @Override
    public void run() {
        System.out.println("Recharge DroneDeadListener waiting for DRONE_DEAD message");
        while (!Thread.currentThread().isInterrupted()) {
            DroneDeadMessage message = (DroneDeadMessage) EventBus.getInstance().take("DRONE_DEAD");
            if (message != null) {
                System.out.println("Recharge DroneDeadListener DRONE_DEAD message received");
                droneDead();
            }
        }
    }

    private void droneDead() {
        RechargeQueue.getInstance().lowerOkToReceive();
        if (RechargeQueue.getInstance().getOkCounter() >= RechargeQueue.getInstance().getOkToReceive() &&
                RechargeQueue.getInstance().getRechargeState() == RechargeStateEnum.WANTING_TO_RECHARGE) {
            EventBus.getInstance().put(new RechargeMessage());
        }
    }
}
