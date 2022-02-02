package drones.stats;

import java.util.ArrayList;
import java.util.List;

public class StatsSingleton {
    private static StatsSingleton instance;

    private final ArrayList<Stats> list = new ArrayList<>();

    public synchronized static StatsSingleton getInstance() {
        if (instance == null)
            instance = new StatsSingleton();
        return instance;
    }

    public synchronized void add(Stats stats) {
        list.add(stats);
    }

    public synchronized List<Stats> getList() {
        return list;
    }
}
