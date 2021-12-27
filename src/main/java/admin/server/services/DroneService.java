package admin.server.services;

import admin.models.Drone;
import admin.server.beans.Drones;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("drones")
public class DroneService {

    @Path("add")
    @POST
    @Consumes({"application/json"})
    public Response addDrone(Drone d) {
        try {
            System.out.println("POST /drones called");
            Drones.getInstance().add(d);
            System.out.println("POST /drones list");
            Drones.getInstance().getDroneList().stream().forEach(drone -> System.out.println(drone.getId()));
            System.out.println("POST /drones ended");
            System.out.println();
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @Path("get/{id}")
    @GET
    @Produces({"application/json"})
    public Response getByid(@PathParam("id") int id) {
        System.out.println("GET /drones/get/{id} called");
        Drone d = Drones.getInstance().getById(id);
        System.out.println("GET /drones/get/{id} list:");
        Drones.getInstance().getDroneList().stream().forEach(drone -> System.out.println(drone.getId()));
        System.out.println("GET /drones/get/{id} ended with: " + d);
        System.out.println();
        if (d != null)
            return Response.ok(d).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

    @Path("delete")
    @DELETE
    @Consumes({"application/json"})
    public Response removeDroneById(Drone d) {
        System.out.println("DELETE /drones/delete called");
        boolean b = Drones.getInstance().remove(d);
        System.out.println("DELETE /drones/delete list:");
        Drones.getInstance().getDroneList().stream().forEach(drone -> System.out.println(drone.getId()));
        System.out.println("DELETE /drones/delete ended with: " + b);
        System.out.println();
        if (b)
            return Response.ok().build();
        else
            return Response.status(Response.Status.NOT_MODIFIED).build();
    }

}
