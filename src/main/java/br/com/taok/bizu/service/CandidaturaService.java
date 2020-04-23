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
        System.out.println(candidaturas.size());
    }

    public List<Candidatura> candidaturas(String nomeCandidato, String nomeMunicipio, int anoEleicao){
        List<Candidatura> todasCandidaturas = Candidatura.findAll().list();
        return todasCandidaturas.stream()
                .filter(c -> filtroCandidato(c, nomeCandidato, nomeMunicipio, anoEleicao))
                .filter(c -> c.getCassacoes().size() > 0)
                .sorted((c1, c2) -> c2.valorTotalDeBens().compareTo(c1.valorTotalDeBens()))
                .limit(15)
                .collect(Collectors.toList());
    }

    public boolean filtroCandidato(Candidatura candidatura, String nomeCandidato, String nomeMunicipio, int anoEleicao){
        boolean filtraCandidato = nomeCandidato != null && nomeCandidato.trim().length() > 0;
        boolean filtraMunicipio = nomeMunicipio != null && nomeMunicipio.trim().length() > 0;
        boolean filtraAnoEleicao = anoEleicao > 0;
        boolean candidatoFiltrado = true;
        boolean municipioFiltrado = true;
        boolean anoEleicaoFiltrado = true;

        if(filtraCandidato ){
            candidatoFiltrado = candidatura.getNomeCandidato() != null && candidatura.getNomeCandidato().contains(nomeCandidato.toUpperCase());
        }

        if(filtraMunicipio){
            municipioFiltrado = candidatura.getMunicipioEleicao() != null && candidatura.getMunicipioEleicao().contains(nomeMunicipio.toUpperCase());
        }

        if(filtraAnoEleicao){
            anoEleicaoFiltrado = candidatura.getAnoEleicao() == anoEleicao;
        }

        return candidatoFiltrado && municipioFiltrado && anoEleicaoFiltrado;
    }
}
