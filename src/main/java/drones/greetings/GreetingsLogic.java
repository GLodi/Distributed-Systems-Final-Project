package drones.greetings;

import admin.entities.DroneEntity;
import drones.DroneSingleton;
import drones.eventbus.EventBus;
import drones.eventbus.messages.ConfirmedElectedMessage;
import drones.eventbus.messages.ErrorMessage;
import drones.eventbus.messages.GreetedEveryoneMessage;

import java.util.ArrayList;
import java.util.List;

public class GreetingsLogic extends Thread {

    @Override
    public void run() {
        DroneEntity own = new DroneEntity(DroneSingleton.getInstance().getDroneEntity());
        List<DroneEntity> droneEntityList = new ArrayList<>(DroneSingleton.getInstance().getDroneList());

        greetEveryone(droneEntityList, own);
    }

    private void greetEveryone(List<DroneEntity> droneEntityList, DroneEntity own) {
        List<GreetingsClient> greetingsClientList = new ArrayList<>();
        for (DroneEntity droneEntity : droneEntityList) {
            System.out.println("GreetingsLogic greetEveryone greeting " + droneEntity.getId());
            try {
                greetingsClientList.add(new GreetingsClient(droneEntity));
                greetingsClientList.stream().reduce((f, s) -> s).get().start();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("GreetingsLogic greetEveryone ESECUZIONE FALLITA su " + droneEntity.getId());
            }
        }
        System.out.println("GreetingsLogic greetEveryone " + greetingsClientList.size() + " threads running");

        try {
            for (GreetingsClient greetingsClient : greetingsClientList) {
                greetingsClient.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            EventBus.getInstance().put(new ErrorMessage());
        }
        System.out.println("GreetingsLogic greetEveryone waited for all threads");

        List<DroneEntity> repliedDrones = new ArrayList<>();
        greetingsClientList.stream().filter(gc -> gc.getDroneReceived() != null).forEach(gc -> repliedDrones.add(gc.getDroneReceived()));
        System.out.println("GreetingsLogic greetEveryone received " + repliedDrones.size() + " replies");

        DroneSingleton.getInstance().setDroneList(repliedDrones);
        //DroneSingleton.getInstance().getDroneList().removeIf(d -> repliedDrones.stream().noneMatch(r -> r.getId() == d.getId()));
        System.out.println("GreetingsLogic greetEveryone cleaned replies");

        if (DroneSingleton.getInstance().getDroneList().size() == 0) {
            System.out.println("GreetingsLogic greetEveryone Alone. Elect myself to master.");
            DroneSingleton.getInstance().setMaster(DroneSingleton.getInstance().getDroneEntity());
            EventBus.getInstance().put(new ConfirmedElectedMessage());
        } else {
            EventBus.getInstance().put(new GreetedEveryoneMessage());
        }
    }

}

