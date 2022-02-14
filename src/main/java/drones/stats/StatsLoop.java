package drones.stats;

import drones.DroneSingleton;

import java.util.List;
import java.util.stream.Collectors;

public class StatsLoop extends Thread {
    @Override
    public void run() {
        while (true) {
            try {
                List<Stats> statsList = StatsSingleton.getInstance().getList().stream().filter(s -> s.droneId == DroneSingleton.getInstance().getId()).collect(Collectors.toList());

                System.out.println("--- RECAP ---");
                System.out.println("Numero consegne effettuate: " + statsList.size());
                System.out.println("Km percorsi: " + statsList.stream().map(s -> s.kmRun).reduce(Double::sum).orElse(0.0));
                System.out.println("Batteria residua: " + DroneSingleton.getInstance().getBattery());
                System.out.println("-------------");

                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
