package drones.register;

import admin.entities.DroneAcceptedEntity;
import admin.entities.DroneEntity;
import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import drones.DroneModel;

public class DroneRegisterThread extends Thread {
    private final int id;
    private final String address;
    private final int port;
    private volatile DroneModel droneModel; // volatile perche' main ritira il droneModel nel suo thread

    public DroneRegisterThread(int id, String address, int port) {
        this.id = id;
        this.address = address;
        this.port = port;
    }

    private static ClientResponse postRequest(Client client, String url, DroneEntity d) {
        WebResource webResource = client.resource(url);
        String input = new Gson().toJson(d);
        try {
            return webResource.type("application/json").post(ClientResponse.class, input);
        } catch (ClientHandlerException e) {
            System.out.println("Server non disponibile");
            return null;
        }
    }

    private static ClientResponse getRequest(Client client, String url) {
        WebResource webResource = client.resource(url);
        try {
            return webResource.type("application/json").get(ClientResponse.class);
        } catch (ClientHandlerException e) {
            System.out.println("Server non disponibile");
            return null;
        }
    }

    public static DroneModel connectToServer(String id, String address, String port) {
        try {
            DroneRegisterThread droneRegisterThread = new DroneRegisterThread(Integer.valueOf(id), address, Integer.valueOf(port));
            droneRegisterThread.run();
            droneRegisterThread.join();
            return droneRegisterThread.getDroneModel();
        } catch (Exception e) {
            System.out.println("esecuzione fallita");
        }
        return null;
    }

    public void run() {
        System.out.println("DroneRegisterThread started");
        Client client = Client.create();
        ClientResponse clientResponse = null;


        String addPath = "/drones/add";
        DroneEntity droneEntity = new DroneEntity(id, port);
        clientResponse = postRequest(client, "http://" + address + addPath, droneEntity);
        System.out.println(clientResponse.toString());
        DroneAcceptedEntity droneAcceptedEntity = clientResponse.getEntity(DroneAcceptedEntity.class);
        this.droneModel = new DroneModel(id, port, droneAcceptedEntity);

        System.out.println("DroneRegisterThread ended");
    }

    public DroneModel getDroneModel() {
        return droneModel;
    }
}
