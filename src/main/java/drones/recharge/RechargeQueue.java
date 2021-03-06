package drones.recharge;

import java.util.ArrayList;
import java.util.List;

public class RechargeQueue {
    private static RechargeQueue instance;

    private final ArrayList<Recharge> queue = new ArrayList<>();

    private RechargeStateEnum rechargeStateEnum = RechargeStateEnum.NOT_RECHARGING;
    private Recharge ownRequest;
    private int okCounter = 0;
    private int okToReceive = 0;

    public synchronized static RechargeQueue getInstance() {
        if (instance == null)
            instance = new RechargeQueue();
        return instance;
    }

    public synchronized int size() {
        return queue.size();
    }

    public synchronized void put(Recharge o) {
        queue.add(o);
    }

    public synchronized List<Recharge> reset() {
        ArrayList<Recharge> res = new ArrayList<>(queue);
        queue.clear();
        return res;
    }

    public synchronized void setWantsRecharge() {
        rechargeStateEnum = RechargeStateEnum.WANTING_TO_RECHARGE;
    }

    public synchronized void setRecharging() {
        rechargeStateEnum = RechargeStateEnum.RECHARGING;
    }

    public synchronized void setNotRecharging() {
        rechargeStateEnum = RechargeStateEnum.NOT_RECHARGING;
    }

    public synchronized RechargeStateEnum getRechargeState() {
        return rechargeStateEnum;
    }

    public synchronized Recharge getOwnRequest() {
        return ownRequest;
    }

    public synchronized void setOwnRequest(Recharge request) {
        ownRequest = request;
    }

    public synchronized void increaseOkCounter() {
        okCounter += 1;
    }

    public synchronized int getOkCounter() {
        return okCounter;
    }

    public synchronized void resetOkCounter() {
        okCounter = 0;
    }

    public synchronized int getOkToReceive() {
        return okToReceive;
    }

    public synchronized void setOkToReceive(int ok) {
        okToReceive = ok;
    }

    public synchronized List<Recharge> rechargeList() {
        return queue;
    }

    public synchronized void lowerOkToReceive() {
        okToReceive -= 1;
    }
}
