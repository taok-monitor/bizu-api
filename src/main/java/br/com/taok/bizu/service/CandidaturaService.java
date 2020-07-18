package br.com.taok.bizu.service;

import br.com.taok.bizu.model.Bem;
import br.com.taok.bizu.model.Candidatura;
import br.com.taok.bizu.model.Cassacao;
import br.com.taok.bizu.tse.model.EleicoesCSV;
import br.com.taok.bizu.tse.service.coleta.LeitorCSV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Dependent
public class CandidaturaService {

    Logger log = LoggerFactory.getLogger(CandidaturaService.class);

    @Inject
    LeitorCSV leitorCSV;

    public void coletaEleicaoGeralViaCSV(Integer anoEleicao){
        EleicoesCSV eleicoesCSV = EleicoesCSV.encontraPorAnoEleitora(anoEleicao);
        List<Candidatura> candidaturas = leitorCSV.lerCSV(eleicoesCSV.pathCandidatos("CE"),eleicoesCSV.getAnoEleicao());
        List<Bem> bens = leitorCSV.lerCSVBem(eleicoesCSV.pathCandidatosBens("CE"));
        List<Cassacao> cassacoes = leitorCSV.lerCSVCassacao(eleicoesCSV.pathCandidatosCassacao("CE"));

        candidaturas.stream().forEach(c -> {
            c.setUrlFoto(eleicoesCSV.getUrlFotoPorCandidato(c));
            List<Bem> bensDoCandidato = bens.stream()
                    .filter(b -> b.getCodigoCandidato().equals(c.getCodigoCandidato()))
                    .collect(Collectors.toList());
            c.adicionaBens(bensDoCandidato);

            List<Cassacao> cassacoesDoCandidato = cassacoes.stream()
                    .filter(cassacao -> cassacao.getCodigoCandidato().equals(c.getCodigoCandidato()))
                    .collect(Collectors.toList());
            c.adicionarCassacao(cassacoesDoCandidato);
        });

        Candidatura.persist(candidaturas);
        log.info("Finalizando importação, total importado={} ", candidaturas.size());
    }

    public List<Candidatura> candidaturas(CandidaturaFilter candidaturaFilter){

        return candidaturaFilter
                .getListagemFiltrada().stream()
                    .filter(c -> c.getCassacoes().size() > 0)
                    .sorted(Comparator.comparing(Candidatura::valorTotalDeBens).reversed())
                    .limit(15)
                    .collect(Collectors.toList());
    }

    public List<String> municipios(){
        List<Candidatura> todasCandidaturas = Candidatura.findAll().list();
        return todasCandidaturas.stream()
                .filter(c -> c.getMunicipioEleicao() != null)
                .map(c -> c.getMunicipioEleicao())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
}
