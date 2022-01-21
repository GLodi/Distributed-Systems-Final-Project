package drones.closing;

import admin.entities.DroneEntity;
import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;

public class ClosingLogic extends Thread {
    private final int id;
    private final String address;
    private final int port;

    public ClosingLogic(int id, String address, int port) {
        this.id = id;
        this.address = address;
        this.port = port;
    }

    private static ClientResponse deleteRequest(Client client, String url, DroneEntity d) {
        WebResource webResource = client.resource(url);
        String input = new Gson().toJson(d);
        try {
            return webResource.type("application/json").delete(ClientResponse.class, input);
        } catch (ClientHandlerException e) {
            System.out.println("Server non disponibile");
            return null;
        }
    }

    public void run() {
        System.out.println("ClosingLogic started");

        ClientConfig config = new DefaultClientConfig();
        config.getClasses().add(JacksonJaxbJsonProvider.class);
        config.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);

        Client client = Client.create(config);
        ClientResponse clientResponse = null;

        String addPath = "/drones/delete";
        DroneEntity droneEntity = new DroneEntity(id, port);
        clientResponse = deleteRequest(client, "http://" + address + addPath, droneEntity);
        System.out.println(clientResponse.toString());

        System.out.println("ClosingLogic ended");
    }

}

