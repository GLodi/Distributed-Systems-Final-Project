package drones.recharge;

import admin.entities.DroneEntity;
import drones.DroneSingleton;
import drones.eventbus.EventBus;
import drones.eventbus.messages.RechargeMessage;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class RechargeBroadcast extends Thread {
    @Override
    public void run() {
        System.out.println("RechargeBroadcast broadcasting recharge request");
        List<RechargeBroadcastClient> rechargeBroadcastClientList = new ArrayList<>();
        List<DroneEntity> droneEntityList = new ArrayList<>(DroneSingleton.getInstance().getDroneList());

        if (droneEntityList.size() == 0) {
            EventBus.getInstance().put(new RechargeMessage());
            System.out.println("RechargeBroadcast ALONE! Recharging.");
            return;
        }

        RechargeQueue.getInstance().setWantsRecharge();
        RechargeQueue.getInstance().setOkToReceive(DroneSingleton.getInstance().getDroneList().size());
        RechargeQueue.getInstance().setOwnRequest(new Recharge(DroneSingleton.getInstance().getId(), Timestamp.from(Instant.now())));

        for (DroneEntity droneEntity : droneEntityList) {
            try {
                rechargeBroadcastClientList.add(new RechargeBroadcastClient(droneEntity));
                rechargeBroadcastClientList.stream().reduce((f, s) -> s).get().start();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("RechargeBroadcast broadcasting ESECUZIONE FALLITA su " + droneEntity.getId());
            }
        }
        System.out.println("RechargeBroadcast broadcasting " + rechargeBroadcastClientList.size() + " threads running. Waiting for permissions to recharge.");
    }
}
