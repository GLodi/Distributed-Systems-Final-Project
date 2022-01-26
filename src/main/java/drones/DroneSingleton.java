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
import drones.register.RegistrationLogic;
import drones.sensors.DroneSensorsThread;
import drones.stats.DroneStatsThread;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DroneSingleton {

    private static DroneSingleton instance;

    // TODO: split this into multiple models, (handled by singletons) so that accessing one piece of info
    //      doesn't require the sync of the entire DroneSingleton
    // TODO: use manual locks to lower granularity of synchronized access
    private DroneModel droneModel;

    private RegistrationLogic registrationLogic;
    private GreetingsLogic greetingsLogic;
    private CheckAliveLogic checkAliveLogic;
    private ElectionLogic electionLogic;
    private ClosingLogic closingLogic;
    private OrderLogic orderLogic;
    private OrderMQTTThread orderMQTTThread;
    private DroneSensorsThread droneSensorsThread;
    private DroneStatsThread droneStatsThread;

    private DroneSingleton() {
    }

    public synchronized static DroneSingleton getInstance() {
        if (instance == null)
            instance = new DroneSingleton();
        return instance;
    }


    public void startRegisterService(int id, String address, int port) {
        try {
            registrationLogic = new RegistrationLogic(id, address, port);
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
            greetingsLogic = new GreetingsLogic();
            greetingsLogic.start();
        } catch (Exception e) {
            System.out.println("DroneSingleton startGreetingsService esecuzione fallita");
            EventBus.getInstance().put(new ErrorMessage());
        }
    }

    public void startCheckAliveService() {
        try {
            checkAliveLogic = new CheckAliveLogic();
            checkAliveLogic.start();
        } catch (Exception e) {
            System.out.println("DroneSingleton startCheckAliveService esecuzione fallita");
        }
    }

    public void startElectionService() {
        try {
            electionLogic = new ElectionLogic();
            electionLogic.start();
        } catch (Exception e) {
            System.out.println("DroneSingleton startElectionService esecuzione fallita");
        }
    }

    public void startClosingService() {
        try {
            closingLogic = new ClosingLogic(droneModel.id, droneModel.serverAddress, droneModel.port);
            closingLogic.start();
            closingLogic.join();
        } catch (Exception e) {
            System.out.println("DroneSingleton startClosingService esecuzione fallita");
        }
    }

    public void startOrderService() {
        try {
            orderLogic = new OrderLogic();
            orderLogic.start();
        } catch (Exception e) {
            System.out.println("DroneSingleton startOrderService esecuzione fallita");
        }
    }

    public void startOrderMQTTThread() {
        try {
            orderMQTTThread = new OrderMQTTThread();
            orderMQTTThread.start();
        } catch (Exception e) {
            System.out.println("DroneSingleton startOrderMQTTThread esecuzione fallita");
        }
    }

    public void stopOrderMQTTThread() {
        if (orderMQTTThread != null) {
            orderMQTTThread.interrupt();
        }
    }

    public void listenForErrors() {
        while (true) {
            if (EventBus.getInstance().take("ERROR") != null) {

            }
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
                    .build();
            server.start();
            System.out.println("GRPC servers started");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("GRPC server ERROR:" + e.getLocalizedMessage());
        }
    }

    // TODO: subscribe to eventbus for error handling. If error message shows up, kill all threads.
    public synchronized void interruptAll() {
        startClosingService();
        if (greetingsLogic != null) {
            greetingsLogic.interrupt();
        }
        if (registrationLogic != null) {
            registrationLogic.interrupt();
        }
        if (checkAliveLogic != null) {
            checkAliveLogic.interrupt();
        }
        if (closingLogic != null) {
            closingLogic.interrupt();
        }
        if (electionLogic != null) {
            electionLogic.interrupt();
        }
        if (orderLogic != null) {
            orderLogic.interrupt();
        }
        if (orderMQTTThread != null) {
            orderMQTTThread.interrupt();
        }
        if (droneSensorsThread != null) {
            droneSensorsThread.interrupt();
        }
        if (droneStatsThread != null) {
            droneStatsThread.interrupt();
        }
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

    public synchronized void setParticipant() {
        droneModel.electionParticipant = true;
    }

    public synchronized void setNonParticipant() {
        droneModel.electionParticipant = false;
    }

    public synchronized boolean isParticipant() {
        return droneModel.electionParticipant;
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
}
