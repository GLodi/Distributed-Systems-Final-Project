package drones.recharge;

import drones.DroneSingleton;
import drones.eventbus.EventBus;
import drones.eventbus.messages.InformMasterRechargeMessage;
import drones.eventbus.messages.SendOkToAllRechargeMessage;

public class RechargeRecharge extends Thread {
    @Override
    public void run() {
        System.out.println("Recharge RechargeRechargeClient started");

        RechargeQueue.getInstance().setRecharging();
        DroneSingleton.getInstance().setRecharging(true);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        DroneSingleton.getInstance().updateDrone(DroneSingleton.getInstance().getId(), 0, 0, 100);

        RechargeQueue.getInstance().setNotRecharging();
        RechargeQueue.getInstance().resetOkCounter();

        DroneSingleton.getInstance().setRecharging(false);

        System.out.println("Recharge RechargeRechargeClient ended");

        EventBus.getInstance().put(new InformMasterRechargeMessage());
        EventBus.getInstance().put(new SendOkToAllRechargeMessage());

    }
}
