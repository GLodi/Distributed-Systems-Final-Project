package drones.recharge;

import admin.entities.DroneEntity;
import drones.DroneSingleton;
import drones.eventbus.EventBus;
import drones.eventbus.messages.ErrorMessage;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class RechargeBroadcast extends Thread {
    @Override
    public void run() {
        List<RechargeClient> rechargeClientList = new ArrayList<>();
        List<DroneEntity> droneEntityList = new ArrayList<>(DroneSingleton.getInstance().getDroneList());

        RechargeQueue.getInstance().setWantsRecharge();
        RechargeQueue.getInstance().setOwnRequest(new Recharge(DroneSingleton.getInstance().getId(), Timestamp.from(Instant.now())));

        for (DroneEntity droneEntity : droneEntityList) {
            System.out.println("RechargeBroadcast broadcasting recharge request");
            try {
                rechargeClientList.add(new RechargeClient(droneEntity));
                rechargeClientList.stream().reduce((f, s) -> s).get().start();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("RechargeBroadcast broadcasting ESECUZIONE FALLITA su " + droneEntity.getId());
            }
        }
        System.out.println("RechargeBroadcast broadcasting " + rechargeClientList.size() + " threads running");

        try {
            for (RechargeClient rechargeClient : rechargeClientList) {
                rechargeClient.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            EventBus.getInstance().put(new ErrorMessage());
        }
        System.out.println("RechargeBroadcast broadcasting waited for all threads");

        // has received all OKs

        System.out.println("RechargeBroadcast PERFORMING RECHARGE");

        RechargeQueue.getInstance().setRecharging();
        DroneSingleton.getInstance().setRecharging(true);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        RechargeQueue.getInstance().setNotRecharging();
        DroneSingleton.getInstance().setRecharging(false);

        System.out.println("RechargeBroadcast DONE RECHARGING");
    }
}
