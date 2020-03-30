package br.com.taok.bizu.service;

import br.com.taok.bizu.model.Candidatura;
import br.com.taok.bizu.tse.model.Eleicao;
import br.com.taok.bizu.tse.model.Eleicoes;
import br.com.taok.bizu.tse.model.Localidade;
import br.com.taok.bizu.tse.service.coleta.Coletor;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Dependent
public class CandidaturaService {

    @Inject
    Coletor coletor;

    @Inject
    LocalidadeService localidadeService;

    public List<Candidatura> coletaEleicao1(){

        Localidade localidade = new Localidade();
        localidade.setSigla("CE");
        List<Eleicao> eleicoesEncontradas = coletor.colete(Eleicoes.ELEICAO_2018, localidade);
        List<Candidatura> candidaturas = new ArrayList<>();

        eleicoesEncontradas.stream().forEach(eleicao ->{
            eleicao.getCandidatos().stream().forEach( c -> {
                candidaturas.add(new Candidatura(c, Eleicoes.ELEICAO_2018.getAnoEleicao(), eleicao.getUnidadeEleitoral(), eleicao.getCargo(), eleicao.getUnidadeEleitoral()));
            });
        });
        candidaturas.stream().forEach(c ->{
            c.persist();
        });
        return candidaturas;
    }

    public void coletaEleicao2(){

        Localidade localidade = localidadeService.carregaLocalidadeDoEstado("CE");
        localidade.getMunicipios().stream().forEach(municipio -> {
            List<Eleicao> eleicoesEncontradas = coletor.colete(Eleicoes.ELEICAO_2016, municipio);
            eleicoesEncontradas.stream().forEach(eleicao ->{
                eleicao.getCandidatos().stream().forEach( c -> {
                    new Candidatura(c, Eleicoes.ELEICAO_2016.getAnoEleicao(),eleicao.getUnidadeEleitoral(), eleicao.getCargo(), municipio).persist();
                });
            });
        });
    }
}
