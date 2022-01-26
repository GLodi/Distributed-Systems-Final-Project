package drones.checkalive;

import admin.entities.DroneEntity;
import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import drones.DroneSingleton;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;

public class CheckAliveInformServer extends Thread {
    private final DroneEntity toRemove;

    CheckAliveInformServer(DroneEntity toRemove) {
        this.toRemove = toRemove;
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

    @Override
    public void run() {
        System.out.println("CheckAlive CheckAliveInformServer started");

        ClientConfig config = new DefaultClientConfig();
        config.getClasses().add(JacksonJaxbJsonProvider.class);
        config.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);

        Client client = Client.create(config);
        ClientResponse clientResponse = null;

        String removePath = "/drones/delete";
        DroneEntity droneEntity = new DroneEntity(toRemove.getId(), toRemove.getPort());
        clientResponse = deleteRequest(client, "http://" + DroneSingleton.getInstance().getServerAddress() + removePath, droneEntity);
        System.out.println(clientResponse.toString());

        System.out.println("CheckAlive CheckAliveInformServer ended");
    }
}
