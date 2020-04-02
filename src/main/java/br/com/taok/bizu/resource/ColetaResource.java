package br.com.taok.bizu.resource;

import br.com.taok.bizu.service.CandidaturaService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/coleta")
public class ColetaResource {

    @Inject
    CandidaturaService candidaturaService;

    @GET
    @Path("/candidaturas/gerais/{anoEleicao}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response candidaturas(@PathParam("anoEleicao") Integer anoEleicao) throws Exception {
        candidaturaService.coletaEleicaoGeral(anoEleicao);
        return Response.status(200).build();
    }

    @GET
    @Path("/candidaturas/municipais")
    @Produces(MediaType.APPLICATION_JSON)
    public Response municipais() {
        candidaturaService.coletaEleicaoMunicipal();
        return Response.status(200).build();
    }

    @GET
    @Path("/candidaturas/gerais/csv/{anoEleicao}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response candidaturasCSV(@PathParam("anoEleicao") Integer anoEleicao) throws Exception {
        candidaturaService.coletaEleicaoGeralViaCSV(anoEleicao);
        return Response.status(200).build();
    }

    @GET
    @Path("/candidaturas/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response candidaturas() throws Exception {
        return Response.status(200)
                .entity(candidaturaService.candidaturas())
                .build();
    }
}