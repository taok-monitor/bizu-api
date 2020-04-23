package br.com.taok.bizu.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class IndexResource {

    @GET
    @Path("/candidaturas/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response index() {
        return Response.status(200).entity("ok").build();
    }
}