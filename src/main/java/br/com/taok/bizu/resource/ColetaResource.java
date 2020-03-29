package br.com.taok.bizu.resource;

import br.com.taok.bizu.service.CandidaturaService;
import br.com.taok.bizu.tse.model.Eleicoes;
import br.com.taok.bizu.tse.service.coleta.Coletor;

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

    @Inject
    CandidaturaService candidaturaService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello() {
        return Response.status(200).entity(coletor.colete(Eleicoes.ELEICAO_2018)).build();
    }

    @GET
    @Path("/candidaturas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response candidaturas() {
        return Response.status(200).entity(candidaturaService.coletaEleicao()).build();
    }
}