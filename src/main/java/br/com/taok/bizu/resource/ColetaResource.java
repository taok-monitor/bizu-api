package br.com.taok.bizu.resource;

import br.com.taok.bizu.service.CandidaturaService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/coleta")
public class ColetaResource {

    @Inject
    CandidaturaService candidaturaService;

    @GET
    @Path("/candidaturas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response candidaturas() {
        return Response.status(200).entity(candidaturaService.coletaEleicao1()).build();
    }

    @GET
    @Path("/candidaturas/municipais")
    @Produces(MediaType.APPLICATION_JSON)
    public Response municipais() {
        candidaturaService.coletaEleicao2();
        return Response.status(200).build();
    }
}