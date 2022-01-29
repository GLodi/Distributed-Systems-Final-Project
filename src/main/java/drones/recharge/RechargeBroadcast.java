package drones.recharge;

import admin.entities.DroneEntity;
import drones.DroneSingleton;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class RechargeBroadcast extends Thread {
    @Override
    public void run() {
        List<RechargeClient> rechargeClientList = new ArrayList<>();
        List<DroneEntity> droneEntityList = new ArrayList<>(DroneSingleton.getInstance().getDroneList());

        // TODO: if dronelist empty, recharge immediately

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
        System.out.println("RechargeBroadcast broadcasting " + rechargeClientList.size() + " threads running. Waiting for permissions to recharge.");

        /**
         RechargeQueue.getInstance().setRecharging();
         DroneSingleton.getInstance().setRecharging(true);

         // TODO: NO. Non aspettare, potrebbe metterci un sacco. Crea una nuova chiamata grpc per ritornare l'ok

         try {
         Thread.sleep(10000);
         } catch (InterruptedException e) {
         e.printStackTrace();
         }

         RechargeQueue.getInstance().setNotRecharging();
         DroneSingleton.getInstance().setRecharging(false);

         System.out.println("RechargeBroadcast DONE RECHARGING");
         **/
    }
}
