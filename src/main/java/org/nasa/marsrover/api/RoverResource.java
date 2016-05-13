package org.nasa.marsrover.api;

import com.google.common.base.Preconditions;
import org.nasa.marsrover.Field;
import org.nasa.marsrover.Rover;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

import static org.nasa.marsrover.storage.Storage.getFieldStorage;

/**
 * @author Mike Dias
 */
public class RoverResource {

    private static final AtomicInteger ID_GEN = new AtomicInteger();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Rover> list(@PathParam("fieldId") Integer fieldId) {
        Field f = getField(fieldId);
        return f.getRovers();
    }

    @GET
    @Path("{roverId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Rover get(@PathParam("fieldId") Integer fieldId, @PathParam("roverId") Integer roverId) {
        Field f = getField(fieldId);
        return f.getRover(roverId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Rover create(@PathParam("fieldId") Integer fieldId, Rover r) {
        Field f = getField(fieldId);
        r.setId(ID_GEN.incrementAndGet());
        r.setField(f);
        f.addRover(r);
        return r;
    }

    @PUT
    @Path("{roverId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Rover update(@PathParam("fieldId") Integer fieldId, @PathParam("roverId") Integer roverId, Rover r) {
        Field f = getField(fieldId);
        f.addRover(r);
        return r;
    }

    @DELETE
    @Path("{roverId}")
    public Response delete(@PathParam("fieldId") Integer fieldId, @PathParam("roverId") Integer roverId) {
        Field f = getField(fieldId);
        f.removeRover(roverId);
        return Response.noContent().build();
    }

    @POST
    @Path("{roverId}/{command}")
    public void command(@PathParam("fieldId") Integer fieldId,
                        @PathParam("roverId") Integer roverId,
                        @PathParam("command") Character command) {

        Field f = getField(fieldId);
        Rover r = f.getRover(roverId);
        r.performCmd(command);

    }

    private Field getField(int fieldId) {
        Field f = getFieldStorage().get(fieldId);
        Preconditions.checkArgument(f != null, "There is no field with id '%s'", fieldId);
        return f;
    }

}
