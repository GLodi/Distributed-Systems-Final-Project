package admin.client;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import drones.Drone;
import drones.Drones;

public class ClientAdmin {

    public static void main(String[] args) {
        Client client = Client.create();
        String serverAddress = "http://localhost:1337";
        ClientResponse clientResponse = null;

        // POST EXAMPLE
//        String postPath = "/drones/add";
//        Drone drone = new Drone(2);
//        clientResponse = postRequest(client, serverAddress + postPath, drone);
//        System.out.println(clientResponse.toString());

        //GET REQUEST #1
        String getPath = "/drones";
        clientResponse = getRequest(client, serverAddress + getPath);
        System.out.println(clientResponse.toString());
        Drones drones = clientResponse.getEntity(Drones.class);
        System.out.println("Users List");
        for (Drone d : drones.getDroneListCopy()) {
            System.out.println("id: " + d.getId());
        }

        //GET REQUEST #2
//        String getUserPath = "/drones/get/2";
//        clientResponse = getRequest(client, serverAddress + getUserPath);
//        System.out.println(clientResponse.toString());
//        Drone userResponse = clientResponse.getEntity(Drone.class);
//        System.out.println("Name: " + userResponse.getId());

    }

    public static ClientResponse postRequest(Client client, String url, Drone d) {
        WebResource webResource = client.resource(url);
        String input = new Gson().toJson(d);
        try {
            return webResource.type("application/json").post(ClientResponse.class, input);
        } catch (ClientHandlerException e) {
            System.out.println("Server non disponibile");
            return null;
        }
    }

    public static ClientResponse getRequest(Client client, String url) {
        WebResource webResource = client.resource(url);
        try {
            return webResource.type("application/json").get(ClientResponse.class);
        } catch (ClientHandlerException e) {
            System.out.println("Server non disponibile");
            return null;
        }
    }
}
