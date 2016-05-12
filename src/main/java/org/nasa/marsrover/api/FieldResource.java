package org.nasa.marsrover.api;

import org.nasa.marsrover.Field;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Mike Dias
 */
@Path("fields")
public class FieldResource {

    // ephemeral persistence
    private static final AtomicInteger ID_GEN = new AtomicInteger();
    private static final ConcurrentMap<Integer, Field> MEM_STORAGE = new ConcurrentHashMap<>();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Field> list() {
        return MEM_STORAGE.values();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Field get(@PathParam("id") Integer id) {
        return MEM_STORAGE.get(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Field create(Field f) {
        f.setId(ID_GEN.incrementAndGet());
        MEM_STORAGE.put(f.getId(), f);
        return f;
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Field update(@PathParam("id") Integer id, Field f) {
        MEM_STORAGE.put(id, f);
        return f;
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Integer id) {
        MEM_STORAGE.remove(id);
        return Response.noContent().build();
    }

}
