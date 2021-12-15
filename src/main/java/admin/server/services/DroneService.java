package admin.server.services;

import drones.Drone;
import drones.Drones;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("drones")
public class DroneService {

    @GET
    @Produces({"application/json"})
    public Response getDroneList() {
        return Response.ok(Drones.getInstance()).build();

    }

    @Path("add")
    @POST
    @Consumes({"application/json"})
    public Response addDrone(Drone d) {
        Drones.getInstance().add(d);
        return Response.ok().build();
    }

    @Path("get/{id}")
    @GET
    @Produces({"application/json"})
    public Response getByid(@PathParam("id") int id) {
        Drone d = Drones.getInstance().getById(id);
        if (d != null)
            return Response.ok(d).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

    @Path("delete")
    @DELETE
    @Consumes({"application/json"})
    public Response removeDroneById(Drone d) {
        boolean b = Drones.getInstance().remove(d);
        if (b)
            return Response.ok().build();
        else
            return Response.status(Response.Status.NOT_MODIFIED).build();
    }


}
