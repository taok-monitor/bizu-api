package br.com.taok.bizu.service;

import br.com.taok.bizu.model.Candidatura;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.Dependent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Dependent
public class CandidaturaFilter {

    private String nomeCandidato;
    private String nomeMunicipio;
    private Integer anoEleicao;
    private String cargo;
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

    public CandidaturaFilter apenasComCassacao(){
        this.apenasComCassacao = true;
        return this;
    }

    private String getQuery(){

        String query =  " anoEleicao = :anoEleicao ";
        if(StringUtils.isNotBlank(nomeCandidato)){
            query = query + " and nomeCandidato like :nomeCandidato";
        }

        if(StringUtils.isNotBlank(nomeMunicipio)){
            query = query + " and municipioEleicao = :municipioEleicao";
        }

        if(StringUtils.isNotBlank(cargo)){
            query = query + " and cargoEleicao = :cargoEleicao";
        }

        if(apenasComCassacao){
            query = query + " and cassacoes is not null";
        }

        return query;
    }

    private Map<String,Object> getParams(){

        Map<String,Object> params = new HashMap<>();
        params.put("anoEleicao", anoEleicao);
        if(StringUtils.isNotBlank(nomeCandidato)){
            params.put("nomeCandidato", nomeCandidato.toUpperCase());
        }

        if(StringUtils.isNotBlank(nomeMunicipio)){
            params.put("municipioEleicao", nomeMunicipio.toUpperCase());
        }

        if(StringUtils.isNotBlank(cargo)){
            params.put("cargoEleicao", cargo.toUpperCase());
        }

        return params;
    }

    public List<Candidatura> getListagemFiltrada(){
        return Candidatura.find(getQuery(), getParams()).list();
    }
}
