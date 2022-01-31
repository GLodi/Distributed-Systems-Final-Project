package drones.recharge;

import drones.recharge.listener.*;

public class RechargeLogic extends Thread {
    @Override
    public void run() {
        listenToBroadcastRecharge();
        listenToSendOkRecharge();
        listenToRecharge();
        listenToInformMaster();
        listenToSendOkToAllRecharge();
        listenToDroneDead();
    }

    private void listenToBroadcastRecharge() {
        try {
            BroadcastRechargeListener broadcastRechargeListener = new BroadcastRechargeListener();
            broadcastRechargeListener.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Recharge RechargeLogic listenToBroadcastRecharge ERROR: " + e.getLocalizedMessage());
        }
    }

    private void listenToSendOkRecharge() {
        try {
            SendOkRechargeListener sendOkRechargeListener = new SendOkRechargeListener();
            sendOkRechargeListener.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Recharge RechargeLogic listenToSendOkRecharge ERROR: " + e.getLocalizedMessage());
        }
    }

    private void listenToSendOkToAllRecharge() {
        try {
            SendOkToAllRechargeListener sendOkToAllRechargeListener = new SendOkToAllRechargeListener();
            sendOkToAllRechargeListener.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Recharge RechargeLogic listenToSendOkToAllRecharge ERROR: " + e.getLocalizedMessage());
        }
    }

    private void listenToDroneDead() {
        try {
            DroneDeadListener droneDeadListener = new DroneDeadListener();
            droneDeadListener.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Recharge RechargeLogic listenToDroneDead ERROR: " + e.getLocalizedMessage());
        }
    }

    private void listenToInformMaster() {
        try {
            InformMasterListener informMasterListener = new InformMasterListener();
            informMasterListener.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Recharge RechargeLogic listenToInformMaster ERROR: " + e.getLocalizedMessage());
        }
    }

    private void listenToRecharge() {
        try {
            RechargeListener rechargeListener = new RechargeListener();
            rechargeListener.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Recharge RechargeLogic listenToRecharge ERROR: " + e.getLocalizedMessage());
        }
    }
}
