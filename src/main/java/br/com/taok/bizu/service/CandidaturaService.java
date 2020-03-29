package br.com.taok.bizu.service;

import br.com.taok.bizu.model.Candidatura;
import br.com.taok.bizu.tse.model.Eleicao;
import br.com.taok.bizu.tse.model.Eleicoes;
import br.com.taok.bizu.tse.service.coleta.Coletor;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Dependent
public class CandidaturaService {

    @Inject
    Coletor coletor;

    public List<Candidatura> coletaEleicao(){

        List<Eleicao> eleicoesEncontradas = coletor.colete(Eleicoes.ELEICAO_2018);
        List<Candidatura> candidaturas = new ArrayList<>();

        eleicoesEncontradas.stream().forEach(eleicao ->{
            eleicao.getCandidatos().stream().forEach( c -> {
                candidaturas.add(new Candidatura(c, 2018,eleicao.getUnidadeEleitoral(), eleicao.getCargo()));
            });
        });
        return candidaturas;
    }
}
