package br.com.taok.bizu.service;

import br.com.taok.bizu.model.Candidatura;
import br.com.taok.bizu.model.Estado;
import io.quarkus.panache.common.Page;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;

import java.util.List;
import java.util.regex.Pattern;

public class CandidaturaFilter {

    private String nomeCandidato;
    private String nomeMunicipio;
    private Integer anoEleicao;
    private String cargo;
    private Estado estado;
    private boolean apenasComCassacao = false;

    public void setNomeCandidato(String nomeCandidato) {
        this.nomeCandidato = nomeCandidato;
    }

    public void setNomeMunicipio(String nomeMunicipio) {
        this.nomeMunicipio = nomeMunicipio;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setAnoEleicao(Integer anoEleicao) {
        this.anoEleicao = anoEleicao;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void setComCassacao(boolean cassacao) {
        this.apenasComCassacao = cassacao;
    }

    public CandidaturaFilter apenasComCassacao(){
        this.apenasComCassacao = true;
        return this;
    }

    private Document createDocumentByFilters(){

        final Document document = new Document();

        if(anoEleicao != null){
            document.put("anoEleicao", anoEleicao);
        }

        if(StringUtils.isNotBlank(nomeCandidato)){
            document.put("nomeCandidato", Pattern.compile(nomeCandidato));
        }

        if(StringUtils.isNotBlank(nomeMunicipio)){
            document.put("municipioEleicao", nomeMunicipio.toUpperCase());
        }

        if(StringUtils.isNotBlank(cargo)){
            document.put("cargoEleicao", cargo);
        }

        if(estado != null){
            document.put("estadoEleicao", estado.name());
        }

        if(apenasComCassacao){
            document.put("cassacoes", new Document("$not", new Document("$size",0)));
        }

        System.out.println(document.toJson());
        return  document;
    }

    public List<Candidatura> getListagemFiltrada(int page){
        return Candidatura.find(createDocumentByFilters())
                .page(Page.of(page, 15))
                .list();
    }
}
