package admin.server.beans;

import admin.models.Statistic;

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
    private final List<Statistic> statisticList;

    private Statistics() {
        statisticList = new ArrayList<Statistic>();
    }

    public synchronized static Statistics getInstance() {
        if (instance == null)
            instance = new Statistics();
        return instance;
    }

    public synchronized List<Statistic> getStatisticList() {
        return new ArrayList<>(statisticList);
    }

    public synchronized void add(Statistic s) {
        statisticList.add(s);
    }

    public synchronized boolean remove(Statistic s) {
        return statisticList.remove(s);
    }
}