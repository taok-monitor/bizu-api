package br.com.taok.bizu.resource;

import br.com.taok.bizu.service.CandidaturaService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/coleta")
public class ColetaResource {

    @Inject
    CandidaturaService candidaturaService;

    @POST
    @Path("/candidaturas/{anoEleicao}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response coletaCandidatura(@PathParam("anoEleicao") Integer anoEleicao) {
        candidaturaService.coletaEleicaoGeralViaCSV(anoEleicao);
        return Response.status(200).build();
    }

    @GET
    @Path("/candidaturas/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtemCandidaturas(
            @QueryParam("nomeCandidato") String nomeCandidato,
            @QueryParam("nomeMunicipio") String nomeMunicipio,
            @QueryParam("anoEleicao") Integer anoEleicao,
            @QueryParam("cargo") String cargo) {
        System.out.println("versão:0.0.1");
        return Response.status(200).entity(candidaturaService.candidaturas(
                nomeCandidato,
                nomeMunicipio,
                anoEleicao,
                cargo
        )).build();
    }

    @GET
    @Path("/municipios/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtermMicipios() {
        return Response.status(200).entity(candidaturaService.municipios()).build();
    }

}