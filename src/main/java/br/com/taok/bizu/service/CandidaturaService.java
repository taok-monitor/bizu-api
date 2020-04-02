package br.com.taok.bizu.service;

import br.com.taok.bizu.model.Bem;
import br.com.taok.bizu.model.Candidatura;
import br.com.taok.bizu.model.Cassacao;
import br.com.taok.bizu.tse.model.Eleicao;
import br.com.taok.bizu.tse.model.Eleicoes;
import br.com.taok.bizu.tse.model.EleicoesCSV;
import br.com.taok.bizu.tse.model.Localidade;
import br.com.taok.bizu.tse.service.coleta.Coletor;
import br.com.taok.bizu.tse.service.coleta.LeitorCSV;
import io.quarkus.mongodb.panache.PanacheMongoEntityBase;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Dependent
public class CandidaturaService {

    @Inject
    Coletor coletor;

    @Inject
    LeitorCSV leitorCSV;

    @Inject
    LocalidadeService localidadeService;

    public void coletaEleicaoGeral(Integer anoEleicao) throws Exception {

        if(anoEleicao == null){
            throw new Exception("Ano Eleição está nulo");
        }

        Eleicoes eleicaoEncontrada = Eleicoes.encontraPorAnoEleitora(anoEleicao);

        if(eleicaoEncontrada == null){
            throw new Exception("Implementação do ano "+anoEleicao+" não encontrada");
        }

        Localidade localidade = new Localidade();
        localidade.setSigla("CE");
        List<Eleicao> eleicoesEncontradas = coletor.colete(eleicaoEncontrada, localidade);
        eleicoesEncontradas.stream().forEach(eleicao ->{
            eleicao.getCandidatos().stream().forEach( c -> {
                new Candidatura(c, eleicaoEncontrada.getAnoEleicao(), eleicao.getUnidadeEleitoral(), eleicao.getCargo(), eleicao.getUnidadeEleitoral()).persist();
            });
        });
    }

    public void coletaEleicaoMunicipal(){

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

    public void coletaEleicaoGeralViaCSV(Integer anoEleicao){
        EleicoesCSV eleicoesCSV = EleicoesCSV.encontraPorAnoEleitora(anoEleicao);
        List<Candidatura> candidaturas = leitorCSV.lerCSV(eleicoesCSV.pathCandidatos("CE"),eleicoesCSV.getAnoEleicao());
        List<Bem> bens = leitorCSV.lerCSVBem(eleicoesCSV.pathCandidatosBens("CE"));
        List<Cassacao> cassacoes = leitorCSV.lerCSVCassacao(eleicoesCSV.pathCandidatosCassacao("CE"));

        candidaturas.stream().forEach(c -> {
            List<Bem> bensDoCandidato = bens.stream()
                    .filter(b -> b.getCodigoCandidato().equals(c.getCodigoCandidato()))
                    .collect(Collectors.toList());
            c.adicionaBens(bensDoCandidato);

            List<Cassacao> cassacoesDoCandidato = cassacoes.stream()
                    .filter(cassacao -> cassacao.getCodigoCandidato().equals(c.getCodigoCandidato()))
                    .collect(Collectors.toList());
            c.adicionarCassacao(cassacoesDoCandidato);
        });

        candidaturas.stream().forEach(c -> {
            c.persist();
        });
        System.out.println(candidaturas.size());
    }

    public List<Candidatura> candidaturas(){

        List<Candidatura> todasCandidaturas = Candidatura.findAll().list();

        return todasCandidaturas.stream()
                .filter(c -> c.getCassacoes().size() > 0)
                .sorted((c1, c2) -> c2.valorTotalDeBens().compareTo(c1.valorTotalDeBens()))
                .limit(15)
                .collect(Collectors.toList());
    }
}
