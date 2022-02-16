package admin.client;

import admin.entities.AverageDeliveriesEntity;
import admin.entities.AverageKmTraveledEntity;
import admin.entities.DroneEntity;
import admin.entities.StatisticEntity;
import admin.server.beans.Drones;
import com.google.gson.Gson;
import com.sun.jersey.api.client.*;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;

import java.util.List;
import java.util.Scanner;

public class ClientAdmin {

    public static void main(String[] args) {
        while (true) {
            int choice = showMenu();
            switch (choice) {
                case 1: {
                    getDroneList();
                    break;
                }
                case 2: {
                    getLastStats();
                    break;
                }
                case 3: {
                    getAverageDelivery();
                    break;
                }
                case 4: {
                    getAverageKm();
                    break;
                }
            }
            if (choice == 5) {
                break;
            }
        }
    }

    private static int showMenu() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Main Menu:");
        System.out.println("--------------");
        System.out.println("1.Elenco droni presenti");
        System.out.println("2.Ultime n statistiche");
        System.out.println("3.Media numero di consegne effettuate");
        System.out.println("4.Media dei chilometri percorsi dai droni");
        System.out.println("5.Quit");
        System.out.println("--------------");
        System.out.println("Enter your choice:");
        int choice = keyboard.nextInt();

        return choice;
    }

    private static void getDroneList() {
        Client client = Client.create();
        ClientResponse clientResponse = null;

        String getPath = "/stats/drones";
        clientResponse = getRequest(client, "http://localhost:1337" + getPath);
        System.out.println(clientResponse.toString());
        Drones drones = clientResponse.getEntity(Drones.class);
        System.out.println("Users List");
        for (DroneEntity d : drones.getDroneList()) {
            System.out.println("id: " + d.getId());
        }
    }

    private static void getLastStats() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("--------------");
        System.out.println("Enter number of last stats:");
        String n = keyboard.next();

        ClientConfig config = new DefaultClientConfig();
        config.getClasses().add(JacksonJaxbJsonProvider.class);
        config.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);

        Client client = Client.create(config);
        ClientResponse clientResponse = null;

        String getPath = "/stats/last/" + n;
        clientResponse = getRequest(client, "http://localhost:1337" + getPath);
        System.out.println(clientResponse.toString());
        List<StatisticEntity> statisticEntityList = clientResponse.getEntity(new GenericType<List<StatisticEntity>>() {
        });
        for (StatisticEntity statisticEntity : statisticEntityList) {
            System.out.println("ts: " + statisticEntity.getTimestamp());
            System.out.println("average battery level: " + statisticEntity.getAverageBatteryLevel());
            System.out.println("average deliveries done: " + statisticEntity.getAverageDeliveryDone());
            System.out.println("average pollution level: " + statisticEntity.getAveragePollutionLevel());
        }
    }

    private static void getAverageDelivery() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("--------------");
        System.out.println("Enter first timestamp:");
        String ts1 = keyboard.next();

        System.out.println("--------------");
        System.out.println("Enter second timestamp:");
        String ts2 = keyboard.next();

        Client client = Client.create();
        ClientResponse clientResponse = null;

        String getPath = "/stats/deliveries/" + ts1 + "&" + ts2;
        clientResponse = getRequest(client, "http://localhost:1337" + getPath);
        AverageDeliveriesEntity averageDelivery = clientResponse.getEntity(AverageDeliveriesEntity.class);
        System.out.println(clientResponse);
        System.out.println("average deliveries between " + ts1 + " and " + ts2 + ": " + averageDelivery.getAverageDeliveries());
    }

    private static void getAverageKm() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("--------------");
        System.out.println("Enter first timestamp:");
        String ts1 = keyboard.next();

        System.out.println("--------------");
        System.out.println("Enter second timestamp:");
        String ts2 = keyboard.next();

        Client client = Client.create();
        ClientResponse clientResponse = null;

        String getPath = "/stats/km/" + ts1 + "&" + ts2;
        clientResponse = getRequest(client, "http://localhost:1337" + getPath);
        AverageKmTraveledEntity averageKmTraveledEntity = clientResponse.getEntity(AverageKmTraveledEntity.class);
        System.out.println(clientResponse);
        System.out.println("average km run between " + ts1 + " and " + ts2 + ": " + averageKmTraveledEntity.getKmTraveled());
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
}
