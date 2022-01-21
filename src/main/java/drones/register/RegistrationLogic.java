package drones.register;

import admin.entities.DroneEntity;
import com.google.gson.Gson;
import com.sun.jersey.api.client.*;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import drones.DroneModel;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;

import java.util.ArrayList;
import java.util.List;

public class RegistrationLogic extends Thread {
    private final int id;
    private final String address;
    private final int port;
    private volatile DroneModel droneModel; // volatile perche' main ritira il droneModel nel suo thread

    public RegistrationLogic(int id, String address, int port) {
        this.id = id;
        this.address = address;
        this.port = port;
    }

    private ClientResponse postRequest(Client client, String url, DroneEntity d) {
        WebResource webResource = client.resource(url);
        String input = new Gson().toJson(d);
        try {
            return webResource.type("application/json").post(ClientResponse.class, input);
        } catch (ClientHandlerException e) {
            System.out.println("Server non disponibile");
            return null;
        }
    }

    public void run() {
        System.out.println("RegisterLogic started");

        ClientConfig config = new DefaultClientConfig();
        config.getClasses().add(JacksonJaxbJsonProvider.class);
        config.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);

        Client client = Client.create(config);
        ClientResponse clientResponse = null;

        String addPath = "/drones/add";
        DroneEntity droneEntity = new DroneEntity(id, port);
        clientResponse = postRequest(client, "http://" + address + addPath, droneEntity);
        System.out.println(clientResponse.toString());
        List<DroneEntity> droneEntityList = clientResponse.getEntity(new GenericType<List<DroneEntity>>() {
        });
        int x = droneEntityList.stream().filter(d -> d.getId() == id).findFirst().get().getX();
        int y = droneEntityList.stream().filter(d -> d.getId() == id).findFirst().get().getY();
        droneEntityList.removeIf(d -> d.getId() == id);
        if (droneEntityList.size() == 0) {
            droneEntityList = new ArrayList<>();
        }
        this.droneModel = new DroneModel(id, x, y, address, port, droneEntityList);

        System.out.println("RegisterLogic ended");
    }

    public DroneModel getDroneModel() {
        return droneModel;
    }
}
