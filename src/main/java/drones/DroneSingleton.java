package drones;

import admin.entities.DroneEntity;
import drones.checkalive.CheckAliveLogic;
import drones.checkalive.CheckAliveServiceImpl;
import drones.closing.ClosingLogic;
import drones.election.ElectionLogic;
import drones.election.ElectionServiceImpl;
import drones.eventbus.EventBus;
import drones.eventbus.messages.ErrorMessage;
import drones.greetings.GreetingsLogic;
import drones.greetings.GreetingsServiceImpl;
import drones.order.client.OrderServiceImpl;
import drones.order.master.OrderLogic;
import drones.order.master.OrderMQTTThread;
import drones.order.master.OrderQueue;
import drones.recharge.RechargeLogic;
import drones.recharge.RechargeServiceImpl;
import drones.register.RegistrationLogic;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DroneSingleton {

    private static DroneSingleton instance;

    // TODO: MUOVI droneModel.electionParticipant nel modulo election e droneModel.delivering in order VEDI RECHARGEQUEUE
    private DroneModel droneModel;

    private DroneSingleton() {
    }

    public synchronized static DroneSingleton getInstance() {
        if (instance == null)
            instance = new DroneSingleton();
        return instance;
    }


    public void startRegisterService(int id, String address, int port) {
        try {
            RegistrationLogic registrationLogic = new RegistrationLogic(id, address, port);
            registrationLogic.start();
            registrationLogic.join();
            droneModel = registrationLogic.getDroneModel();
        } catch (Exception e) {
            System.out.println("DroneSingleton startRegisterService esecuzione fallita");
            EventBus.getInstance().put(new ErrorMessage());
        }
    }

    public void startGreetingsService() {
        try {
            GreetingsLogic greetingsLogic = new GreetingsLogic();
            greetingsLogic.start();
        } catch (Exception e) {
            System.out.println("DroneSingleton startGreetingsService esecuzione fallita");
            EventBus.getInstance().put(new ErrorMessage());
        }
    }

    public void startCheckAliveService() {
        try {
            CheckAliveLogic checkAliveLogic = new CheckAliveLogic();
            checkAliveLogic.start();
        } catch (Exception e) {
            System.out.println("DroneSingleton startCheckAliveService esecuzione fallita");
        }
    }

    public void startElectionService() {
        try {
            ElectionLogic electionLogic = new ElectionLogic();
            electionLogic.start();
        } catch (Exception e) {
            System.out.println("DroneSingleton startElectionService esecuzione fallita");
        }
    }

    public void startClosingService() {
        try {
            ClosingLogic closingLogic = new ClosingLogic(droneModel.id, droneModel.serverAddress, droneModel.port);
            closingLogic.start();
            closingLogic.join();
        } catch (Exception e) {
            System.out.println("DroneSingleton startClosingService esecuzione fallita");
        }
    }

    public void startOrderService() {
        try {
            OrderLogic orderLogic = new OrderLogic();
            orderLogic.start();
        } catch (Exception e) {
            System.out.println("DroneSingleton startOrderService esecuzione fallita");
        }
    }

    public void startOrderMQTTThread() {
        try {
            OrderMQTTThread orderMQTTThread = new OrderMQTTThread();
            orderMQTTThread.start();
        } catch (Exception e) {
            System.out.println("DroneSingleton startOrderMQTTThread esecuzione fallita");
        }
    }

    public void startRechargeService() {
        try {
            RechargeLogic rechargeLogic = new RechargeLogic();
            rechargeLogic.start();
        } catch (Exception e) {
            System.out.println("DroneSingleton startRechargeService esecuzione fallita");
        }
    }

    public synchronized void startGRPCServers() {
        try {
            System.out.println("GRPC servers starting");
            Server server = ServerBuilder.forPort(DroneSingleton.getInstance().getPort())
                    .addService(new GreetingsServiceImpl())
                    .addService(new CheckAliveServiceImpl())
                    .addService(new ElectionServiceImpl())
                    .addService(new OrderServiceImpl())
                    .addService(new RechargeServiceImpl())
                    .build();
            server.start();
            System.out.println("GRPC servers started");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("GRPC server ERROR:" + e.getLocalizedMessage());
        }
    }

    public synchronized void interruptAll() {
        OrderQueue.getInstance().clear();
        startClosingService();
        System.exit(0);
    }

    public synchronized boolean initiated() {
        return droneModel != null;
    }

    public synchronized List<DroneEntity> getDroneList() {
        return droneModel.droneList;
    }

    public synchronized void setDroneList(List<DroneEntity> droneEntityList) {
        droneModel.droneList = new ArrayList<>(droneEntityList);
    }

    public synchronized DroneEntity getDroneEntity() {
        return DroneModel.getEntity(droneModel);
    }

    public synchronized int getPort() {
        return droneModel.port;
    }

    public synchronized int getId() {
        return droneModel.id;
    }

    public synchronized DroneEntity getMaster() {
        return droneModel.master;
    }

    public synchronized void setMaster(DroneEntity master) {
        droneModel.master = master;
    }

    public synchronized void addToRing(DroneEntity droneEntity) {
        droneModel.droneList.add(droneEntity);
        droneModel.droneList.sort(Comparator.comparingInt(DroneEntity::getId));
    }

    public synchronized void removeFromRing(int id) {
        droneModel.droneList.removeIf(d -> d.getId() == id);
    }

    public synchronized int getBattery() {
        return droneModel.battery;
    }

    public synchronized void makeDelivery(int x, int y) {
        droneModel.x = x;
        droneModel.y = y;
        droneModel.battery -= 10;
    }

    public synchronized int getX() {
        return droneModel.x;
    }

    public synchronized int getY() {
        return droneModel.y;
    }

    public synchronized String getServerAddress() {
        return droneModel.serverAddress;
    }

    public synchronized void setDelivering(int id, boolean b) {
        if (droneModel.droneList.stream().anyMatch(d -> d.getId() == id)) {
            droneModel.droneList.stream().filter(d -> d.getId() == id).findFirst().get().setDelivering(b);
        } else if (droneModel.id == id) {
            droneModel.delivering = b;
        }
    }

    public synchronized boolean isRecharging() {
        return droneModel.recharging;
    }

    public synchronized void setRecharging(boolean b) {
        droneModel.recharging = b;
    }

    public synchronized void updateDrone(int id, int x, int y, int battery) {
        if (droneModel.droneList.stream().anyMatch(d -> d.getId() == id)) {
            droneModel.droneList.stream().filter(d -> d.getId() == id).findFirst().get().setCoordinates(x, y);
            droneModel.droneList.stream().filter(d -> d.getId() == id).findFirst().get().setBattery(battery);
        } else if (droneModel.id == id) {
            droneModel.x = x;
            droneModel.y = y;
            droneModel.battery = battery;
        }
    }
}
