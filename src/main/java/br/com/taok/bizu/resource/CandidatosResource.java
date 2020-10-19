package br.com.taok.bizu.resource;

import br.com.taok.bizu.model.Estado;
import br.com.taok.bizu.service.CandidaturaFilter;
import br.com.taok.bizu.service.CandidaturaService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/candidaturas")
public class CandidatosResource {

    @Inject
    CandidaturaService candidaturaService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtemCandidaturasGerais(
            @QueryParam("nomeCandidato") String nomeCandidato,
            @QueryParam("cargo") String cargo,
            @QueryParam("cassacao") boolean cassacao,
            @QueryParam("page") int page) {

        CandidaturaFilter candidaturaFilter = new CandidaturaFilter();
        candidaturaFilter.setCargo(cargo);
        candidaturaFilter.setNomeCandidato(nomeCandidato);
        candidaturaFilter.setComCassacao(cassacao);

        return Response.status(200).entity(candidaturaService.candidaturas(candidaturaFilter, page)).build();
    }

    @GET
    @Path("/{ano}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtemCandidaturasPorAno(
            @PathParam("ano") Integer anoEleicao,
            @QueryParam("nomeCandidato") String nomeCandidato,
            @QueryParam("cargo") String cargo,
            @QueryParam("cassacao") boolean cassacao,
            @QueryParam("page") int page) {

        CandidaturaFilter candidaturaFilter = new CandidaturaFilter();
        candidaturaFilter.setAnoEleicao(anoEleicao);
        candidaturaFilter.setCargo(cargo);
        candidaturaFilter.setNomeCandidato(nomeCandidato);
        candidaturaFilter.setComCassacao(cassacao);

        return Response.status(200).entity(candidaturaService.candidaturas(candidaturaFilter, page)).build();
    }

    @GET
    @Path("/{ano}/{estado}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtemCandidaturasPorAnoEEstado(
            @PathParam("estado") Estado estado,
            @PathParam("ano") Integer anoEleicao,
            @QueryParam("nomeCandidato") String nomeCandidato,
            @QueryParam("cargo") String cargo,
            @QueryParam("cassacao") boolean cassacao,
            @QueryParam("page") int page) {

        System.out.println(estado);

        CandidaturaFilter candidaturaFilter = new CandidaturaFilter();
        candidaturaFilter.setAnoEleicao(anoEleicao);
        candidaturaFilter.setCargo(cargo);
        candidaturaFilter.setNomeCandidato(nomeCandidato);
        candidaturaFilter.setEstado(estado);
        candidaturaFilter.setComCassacao(cassacao);

        return Response.status(200).entity(candidaturaService.candidaturas(candidaturaFilter, page)).build();
    }

    @GET
    @Path("/{ano}/{estado}/{municipio}/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtemCandidaturasPorAnoEstadoEMunicipio(
            @PathParam("estado") Estado estado,
            @PathParam("municipio") String nomeMunicipio,
            @PathParam("ano") Integer anoEleicao,
            @QueryParam("nomeCandidato") String nomeCandidato,
            @QueryParam("cargo") String cargo,
            @QueryParam("cassacao") boolean cassacao,
            @QueryParam("page") int page) {

        CandidaturaFilter candidaturaFilter = new CandidaturaFilter();
        candidaturaFilter.setAnoEleicao(anoEleicao);
        candidaturaFilter.setCargo(cargo);
        candidaturaFilter.setNomeCandidato(nomeCandidato);
        candidaturaFilter.setNomeMunicipio(nomeMunicipio);
        candidaturaFilter.setEstado(estado);
        candidaturaFilter.setComCassacao(cassacao);

        return Response.status(200).entity(candidaturaService.candidaturas(candidaturaFilter, page)).build();
    }
}