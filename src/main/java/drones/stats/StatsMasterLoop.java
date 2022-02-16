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
import drones.sensors.AverageMeasurement;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StatsMasterLoop extends Thread {

    public static <E, K> Map<K, List<E>> groupBy(List<E> list, Function<E, K> keyFunction) {
        return Optional.ofNullable(list)
                .orElseGet(ArrayList::new)
                .stream()
                .collect(Collectors.groupingBy(keyFunction));
    }

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
        while (true) {
            try {
                System.out.println("StatsMasterLogic started");
                List<Stats> statList = StatsSingleton.getInstance().getList();
                double numDroni = DroneSingleton.getInstance().getDroneList().size() + 1;

                Collection<List<Stats>> groupedStats = groupBy(statList, Stats::getDroneId).values();

                double averageDeliveryDone = groupedStats.stream().map(List::size).reduce(Integer::sum).orElse(0) / numDroni;
                double averageKmTraveled = groupedStats.stream().map((list) ->
                                list.stream().map(s -> s.kmRun).reduce(Double::sum).orElse(0.0) / list.size())
                        .reduce(Double::sum).orElse(0.0) / numDroni;
                double averagePollutionLevel = groupedStats.stream().map((drone) ->
                                drone.stream().filter(stat -> stat.averageMeasurementList.size() > 0).map(stat ->
                                                stat.averageMeasurementList.stream().map(AverageMeasurement::getValue).reduce(Double::sum).orElse(0.0) /
                                                        (stat.averageMeasurementList.size() == 0 ? 1 : stat.averageMeasurementList.size()))
                                        .reduce(Double::sum).orElse(0.0) / drone.stream().filter(stat -> stat.averageMeasurementList.size() > 0).collect(Collectors.toList()).size() == 0 ? 1 : drone.stream().filter(stat -> stat.averageMeasurementList.size() > 0).collect(Collectors.toList()).size())
                        .reduce(Integer::sum).orElse(0) / numDroni;
                double averageBatteryLevel = groupedStats.stream().map((list) ->
                                list.stream().map(s -> s.residualBattery).reduce(Integer::sum).orElse(0) / list.size())
                        .reduce(Integer::sum).orElse(0) / numDroni;

                if (statList.size() > 0) {
                    ClientConfig config = new DefaultClientConfig();
                    config.getClasses().add(JacksonJaxbJsonProvider.class);
                    config.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);

                    Client client = Client.create();
                    ClientResponse clientResponse = null;

                    String addPath = "/stats/add";
                    StatisticEntity statisticEntity = new StatisticEntity(averageDeliveryDone, averageKmTraveled, averagePollutionLevel, averageBatteryLevel, System.currentTimeMillis());
                    clientResponse = postRequest(client, "http://" + DroneSingleton.getInstance().getServerAddress() + addPath, statisticEntity);
                    System.out.println(clientResponse.toString());
                    System.out.println("StatsMasterLogic done sending stats");
                } else {
                    System.out.println("StatsMasterLogic no stats to send");
                }
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
