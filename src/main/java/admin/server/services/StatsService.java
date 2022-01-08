package admin.server.services;

import admin.entities.StatisticEntity;
import admin.server.beans.Drones;
import admin.server.beans.Statistics;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("stats")
public class StatsService {

    @POST
    @Consumes({"application/json"})
    public Response addStatistic(StatisticEntity s) {
        System.out.println("POST /stats called");
        Statistics.getInstance().add(s);
        System.out.println("POST /stats ended");
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
        System.out.println("POST /stats/last called");
        System.out.println("POST /stats/last ended");
        System.out.println();
        return Response.ok().build();
    }

    @Path("deliveries/{ts1}&{ts2}")
    @GET
    @Consumes({"application/json"})
    public Response averageDeliveries(@PathParam("ts1") String ts1, @PathParam("ts2") String ts2) {
        System.out.println("POST /stats/deliveries called");
        System.out.println("POST /stats/deliveries ended");
        System.out.println();
        return Response.ok().build();
    }

    @Path("km/{ts1}&{ts2}")
    @GET
    @Consumes({"application/json"})
    public Response averageKm(@PathParam("ts1") String ts1, @PathParam("ts2") String ts2) {
        System.out.println("POST /stats/km called");
        System.out.println("POST /stats/km ended");
        System.out.println();
        return Response.ok().build();
    }


}
