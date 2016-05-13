package org.nasa.marsrover.api;

import org.nasa.marsrover.Field;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

import static org.nasa.marsrover.storage.Storage.getFieldStorage;

/**
 * @author Mike Dias
 */
@Path("fields")
public class FieldResource {

    private static final AtomicInteger ID_GEN = new AtomicInteger();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Field> list() {
        return getFieldStorage().values();
    }

    @GET
    @Path("{fieldId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Field get(@PathParam("fieldId") Integer id) {
        return getFieldStorage().get(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Field create(@Valid Field f) {
        f.setId(ID_GEN.incrementAndGet());
        getFieldStorage().put(f.getId(), f);
        return f;
    }

    @PUT
    @Path("{fieldId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Field update(@PathParam("fieldId") Integer id, @Valid Field f) {
        getFieldStorage().put(id, f);
        return f;
    }

    @DELETE
    @Path("{fieldId}")
    public Response delete(@PathParam("fieldId") Integer id) {
        getFieldStorage().remove(id);
        return Response.noContent().build();
    }


    @Path("{fieldId}/rovers")
    public RoverResource getRoverResource() {
        return new RoverResource();
    }


}
