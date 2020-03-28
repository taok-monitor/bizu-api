package br.com.taok.bizu.resource;

import br.com.taok.bizu.tse.coleta.Coletor;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/coleta")
public class ColetaResource {

    @Inject
    Coletor coletor;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello() {
        return Response.status(200).entity(coletor.colete()).build();
    }
}