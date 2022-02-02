package drones.stats;

import admin.entities.StatisticEntity;
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

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

public class StatsMasterLoop extends Thread {

    private ClientResponse postRequest(Client client, String url, StatisticEntity d) {
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
        System.out.println("StatsMasterLogic started");
        List<Stats> statList = StatsSingleton.getInstance().getList();
        double numDroni = DroneSingleton.getInstance().getDroneList().size();

        double averageDeliveryDone = statList.size() / numDroni;
        double averageKmTraveled = statList.stream().map(s -> s.kmRun).reduce(0.0, Double::sum) / numDroni;
        double averagePollutionLevel = 0;
        double averageBatteryLevel = statList.stream().map(s -> s.residualBattery).reduce(0, Integer::sum) / numDroni;

        ClientConfig config = new DefaultClientConfig();
        config.getClasses().add(JacksonJaxbJsonProvider.class);
        config.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);

        Client client = Client.create(config);
        ClientResponse clientResponse = null;

        String addPath = "/stats/add";
        StatisticEntity statisticEntity = new StatisticEntity(averageDeliveryDone, averageKmTraveled, averagePollutionLevel, averageBatteryLevel, Timestamp.from(Instant.now()));
        clientResponse = postRequest(client, "http://" + DroneSingleton.getInstance().getServerAddress() + addPath, statisticEntity);
        System.out.println(clientResponse.toString());

        System.out.println("StatsMasterLogic ended");
    }
}
