package admin.server.services;

import admin.entities.AverageDeliveriesEntity;
import admin.entities.AverageKmTraveledEntity;
import admin.entities.StatisticEntity;
import admin.server.beans.Drones;
import admin.server.beans.Statistics;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("stats")
public class StatsService {

    @Path("add")
    @POST
    @Consumes({"application/json", "application/xml"})
    public Response addStatistic(StatisticEntity s) {
        System.out.println("POST /stats/add called");
        Statistics.getInstance().add(s);
        System.out.println("POST /stats/add ended");
        System.out.println();
        return Response.ok().build();
    }

    @Path("drones")
    @GET
    @Produces({"application/json"})
    public Response getDroneList() {
        System.out.println("GET /stats/drones called");
        Drones res = Drones.getInstance();
        System.out.println("GET /stats/drones list:");
        Drones.getInstance().getDroneList().stream().forEach(drone -> System.out.println(drone.getId()));
        System.out.println("GET /stats/drones ended with: " + res);
        System.out.println();
        return Response.ok(res).build();
    }

    @Path("last/{n}")
    @GET
    @Consumes({"application/json"})
    public Response lastStatistics(@PathParam("n") int n) {
        System.out.println("GET /stats/last called");
        if (Statistics.getInstance().getStatisticList().size() < n) {
            System.out.println("GET /stats/last ended with error length");
            System.out.println();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        try {
            GenericEntity<List<StatisticEntity>> res = new GenericEntity<List<StatisticEntity>>(Statistics.getInstance().getStatisticList().subList(Statistics.getInstance().getStatisticList().size() - n, Statistics.getInstance().getStatisticList().size())) {
            };
            System.out.println("GET /stats/last ended");
            System.out.println();
            return Response.ok(res).build();
        } catch (Exception e) {
            System.out.println("GET /stats/last ended with error: " + e.getLocalizedMessage());
            System.out.println();
            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }

    @Path("deliveries/{ts1}&{ts2}")
    @GET
    @Consumes({"application/json"})
    public Response averageDeliveries(@PathParam("ts1") long ts1, @PathParam("ts2") long ts2) {
        System.out.println("GET /stats/deliveries called");
        try {
            AverageDeliveriesEntity ad = new AverageDeliveriesEntity(Statistics.getInstance().getStatisticList().stream().filter(s -> s.getTimestamp() > ts1 && s.getTimestamp() < ts2)
                    .map(StatisticEntity::getAverageDeliveryDone).reduce(Double::sum).orElse(0.0) / Statistics.getInstance().getStatisticList().stream().filter(s -> s.getTimestamp() > ts1 && s.getTimestamp() < ts2).collect(Collectors.toList()).size() == 0 ? 1 : Statistics.getInstance().getStatisticList().stream().filter(s -> s.getTimestamp() > ts1 && s.getTimestamp() < ts2).collect(Collectors.toList()).size());
            System.out.println("GET /stats/deliveries ended");
            System.out.println();
            return Response.ok(ad).build();
        } catch (Exception e) {
            System.out.println("GET /stats/deliveries ended");
            System.out.println();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Path("km/{ts1}&{ts2}")
    @GET
    @Consumes({"application/json"})
    public Response averageKm(@PathParam("ts1") long ts1, @PathParam("ts2") long ts2) {
        System.out.println("GET /stats/km called");
        try {
            List<StatisticEntity> filteredStats = Statistics.getInstance().getStatisticList().stream().filter(s -> s.getTimestamp() > ts1 && s.getTimestamp() < ts2).collect(Collectors.toList());
            AverageKmTraveledEntity ad = new AverageKmTraveledEntity(filteredStats.stream()
                    .map(StatisticEntity::getAverageKmTraveled).reduce(Double::sum).orElse(0.0) /
                    (filteredStats.size() == 0 ? 1 : filteredStats.size()));
            System.out.println("GET /stats/km ended");
            System.out.println();
            return Response.ok(ad).build();
        } catch (Exception e) {
            System.out.println("GET /stats/km ended");
            System.out.println();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


}
