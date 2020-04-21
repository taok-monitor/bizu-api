package br.com.taok.bizu.resource;

import br.com.taok.bizu.service.CandidaturaService;

import javax.inject.Inject;
import javax.ws.rs.*;
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