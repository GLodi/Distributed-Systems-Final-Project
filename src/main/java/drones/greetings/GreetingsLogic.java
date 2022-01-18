package drones.greetings;

import admin.entities.DroneEntity;
import drones.DroneSingleton;
import drones.eventbus.EventBus;
import drones.eventbus.messages.ErrorMessage;
import drones.eventbus.messages.GreetedEveryoneMessage;

import java.util.ArrayList;
import java.util.List;

public class GreetingsLogic extends Thread {

    @Override
    public void run() {
        DroneEntity own = new DroneEntity(DroneSingleton.getInstance().getDroneEntity());
        List<DroneEntity> droneEntityList = new ArrayList<>(DroneSingleton.getInstance().getDroneList());
        droneEntityList.removeIf(d -> d.getId() == own.getId());

        greetEveryone(droneEntityList, own);
    }

    private void greetEveryone(List<DroneEntity> droneEntityList, DroneEntity own) {
        List<GreetingsClient> greetingsClientList = new ArrayList<>();
        for (DroneEntity droneEntity : droneEntityList) {
            System.out.println("GreetingsLogic greetEveryone greeting " + droneEntity.getId());
            try {
                greetingsClientList.add(new GreetingsClient(own, droneEntity));
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

        List<Integer> repliedIds = new ArrayList<>();
        greetingsClientList.stream().filter(gc -> gc.getDroneIdReceived() != 0).forEach(gc -> repliedIds.add(gc.getDroneIdReceived()));
        System.out.println("GreetingsLogic greetEveryone received " + repliedIds.size() + " replies");

        DroneSingleton.getInstance().getDroneList().removeIf(d -> repliedIds.stream().noneMatch(r -> r == d.getId()) && d.getId() != own.getId());
        System.out.println("GreetingsLogic greetEveryone cleaned replies");

        if (DroneSingleton.getInstance().getDroneList().size() == 1) {
            System.out.println("GreetingsLogic greetEveryone Alone. Elect myself to master.");
            DroneSingleton.getInstance().setMaster(DroneSingleton.getInstance().getDroneEntity());
        } else {
            EventBus.getInstance().put(new GreetedEveryoneMessage());
        }
    }

}

