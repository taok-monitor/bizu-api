package br.com.taok.bizu.resource;

import br.com.taok.bizu.service.CandidaturaFilter;
import br.com.taok.bizu.service.CandidaturaService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/candidaturas")
public class CandidatosResource {

    @Inject
    CandidaturaService candidaturaService;

    @GET
    @Path("/candidaturas/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtemCandidaturas(
            @QueryParam("nomeCandidato") String nomeCandidato,
            @QueryParam("nomeMunicipio") String nomeMunicipio,
            @QueryParam("anoEleicao") Integer anoEleicao,
            @QueryParam("cargo") String cargo) {

        CandidaturaFilter candidaturaFilter = new CandidaturaFilter();
        candidaturaFilter.setAnoEleicao(anoEleicao);
        candidaturaFilter.setCargo(cargo);
        candidaturaFilter.setNomeCandidato(nomeCandidato);
        candidaturaFilter.setNomeMunicipio(nomeMunicipio);

        return Response.status(200).entity(candidaturaService.candidaturas(candidaturaFilter)).build();
    }
}