package admin.server.services;

import admin.entities.DroneEntity;
import admin.server.beans.Drones;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("drones")
public class DroneService {

    @Path("add")
    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response addDrone(DroneEntity d) {
        try {
            System.out.println("POST /drones/add called");
            GenericEntity<List<DroneEntity>> droneEntities = new GenericEntity<List<DroneEntity>>(Drones.getInstance().add(d)) {
            };
            System.out.println("POST /drones/add list");
            Drones.getInstance().getDroneList().forEach(drone -> System.out.println(drone.getId()));
            System.out.println("POST /drones/add ended");
            System.out.println();
            return Response.ok(droneEntities).build();
        } catch (Exception e) {
            System.out.println("POST /drones/add ended with error: " + e.getLocalizedMessage());
            System.out.println();
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @Path("get/{id}")
    @GET
    @Produces({"application/json"})
    public Response getByid(@PathParam("id") int id) {
        System.out.println("GET /drones/get/{id} called");
        DroneEntity d = Drones.getInstance().getById(id);
        System.out.println("GET /drones/get/{id} list:");
        Drones.getInstance().getDroneList().forEach(drone -> System.out.println(drone.getId()));
        if (d != null) {
            System.out.println("GET /drones/get/{id} ended with: " + d);
            System.out.println();
            return Response.ok(d).build();
        } else {
            System.out.println("GET /drones/get/{id} ended with status NOT_FOUND");
            System.out.println();
            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }

    @Path("delete")
    @DELETE
    @Consumes({"application/json"})
    public Response removeDroneById(DroneEntity d) {
        System.out.println("DELETE /drones/delete called");
        boolean b = Drones.getInstance().remove(d);
        System.out.println("DELETE /drones/delete list:");
        Drones.getInstance().getDroneList().forEach(drone -> System.out.println(drone.getId()));
        if (b) {
            System.out.println("DELETE /drones/delete ended with: " + b);
            System.out.println();
            return Response.ok().build();
        } else {
            System.out.println("DELETE /drones/delete ended with status NOT_MODIFIED");
            System.out.println();
            return Response.status(Response.Status.NOT_MODIFIED).build();
        }
    }

}
