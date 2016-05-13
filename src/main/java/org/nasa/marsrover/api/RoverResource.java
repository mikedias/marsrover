package org.nasa.marsrover.api;

import org.nasa.marsrover.Field;
import org.nasa.marsrover.Rover;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Mike Dias
 */
public class RoverResource {

    private static final AtomicInteger ID_GEN = new AtomicInteger();

    private Field field;

    public RoverResource(@NotNull Field field) {
        this.field = field;
    }

    public Field getField() {
        return field;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Rover> list() {
        return field.getRovers();
    }

    @GET
    @Path("{roverId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Rover get(@PathParam("roverId") Integer roverId) {
        return field.getRover(roverId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Rover create(@Valid Rover r) {
        r.setId(ID_GEN.incrementAndGet());
        r.setField(field);
        field.addRover(r);
        return r;
    }

    @PUT
    @Path("{roverId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Rover update(@PathParam("roverId") Integer roverId, @Valid Rover r) {
        r.setField(field);
        field.addRover(r);
        return r;
    }

    @DELETE
    @Path("{roverId}")
    public Response delete(@PathParam("roverId") Integer roverId) {
        field.removeRover(roverId);
        return Response.noContent().build();
    }

    @POST
    @Path("{roverId}/{command}")
    public void command(@PathParam("roverId") Integer roverId,
                        @PathParam("command") Character command) {

        Rover r = field.getRover(roverId);
        r.performCmd(command);

    }

}
