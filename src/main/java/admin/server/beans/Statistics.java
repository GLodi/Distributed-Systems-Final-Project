package admin.server.beans;

import admin.entities.StatisticEntity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Statistics {

    private static Statistics instance;
    @XmlElement(name = "my_stats")
    private final List<StatisticEntity> statisticEntityList;

    private Statistics() {
        statisticEntityList = new ArrayList<StatisticEntity>();
    }

    public synchronized static Statistics getInstance() {
        if (instance == null)
            instance = new Statistics();
        return instance;
    }

    public synchronized List<StatisticEntity> getStatisticList() {
        return new ArrayList<>(statisticEntityList);
    }

    public synchronized void add(StatisticEntity s) {
        statisticEntityList.add(s);
    }

    public synchronized boolean remove(StatisticEntity s) {
        return statisticEntityList.remove(s);
    }
}