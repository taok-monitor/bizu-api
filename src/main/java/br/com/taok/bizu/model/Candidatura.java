package br.com.taok.bizu.model;

import br.com.taok.bizu.tse.model.Candidato;
import br.com.taok.bizu.tse.model.Cargo;
import br.com.taok.bizu.tse.model.Localidade;

import java.math.BigDecimal;

public class Candidatura {

    private String codigoCandidator;
    private String nomeCandidato;
    private Integer numeroEleicao;
    private String partido;
    private Boolean eleito;
    private BigDecimal patrimonioDeclarado;
    private Integer anoEleicao;
    private String estadoEleicao;
    private String municipioEleicao;
    private String cargoEleicao;
    private String statusCandidatura;
    private String coligacao;

    public Candidatura(
            Candidato candidato,
            Integer anoEleicao,
            Localidade localidade,
            Cargo cargo) {

        this.codigoCandidator = candidato.getId();
        this.nomeCandidato = candidato.getNomeCompleto();
        this.numeroEleicao = candidato.getNumero();
        this.partido = candidato.getPartido().getSigla();
        this.eleito = candidato.getDescricaoTotalizacao().equals("Eleito") ? true:false;
        this.patrimonioDeclarado = BigDecimal.ZERO;
        this.anoEleicao = anoEleicao;
        this.estadoEleicao = localidade.getSigla();
        this.municipioEleicao =  "";
        this.cargoEleicao = cargo.getNome();
        this.statusCandidatura = candidato.getDescricaoSituacao();
        this.coligacao = candidato.getNomeColigacao();
    }

    public String getCodigoCandidator() {
        return codigoCandidator;
    }

    public void setCodigoCandidator(String codigoCandidator) {
        this.codigoCandidator = codigoCandidator;
    }

    public String getNomeCandidato() {
        return nomeCandidato;
    }

    public void setNomeCandidato(String nomeCandidato) {
        this.nomeCandidato = nomeCandidato;
    }

    public Integer getNumeroEleicao() {
        return numeroEleicao;
    }

    public void setNumeroEleicao(Integer numeroEleicao) {
        this.numeroEleicao = numeroEleicao;
    }

    public String getPartido() {
        return partido;
    }

    public void setPartido(String partido) {
        this.partido = partido;
    }

    public Boolean getEleito() {
        return eleito;
    }

    public void setEleito(Boolean eleito) {
        this.eleito = eleito;
    }

    public BigDecimal getPatrimonioDeclarado() {
        return patrimonioDeclarado;
    }

    public void setPatrimonioDeclarado(BigDecimal patrimonioDeclarado) {
        this.patrimonioDeclarado = patrimonioDeclarado;
    }

    public Integer getAnoEleicao() {
        return anoEleicao;
    }

    public void setAnoEleicao(Integer anoEleicao) {
        this.anoEleicao = anoEleicao;
    }

    public String getEstadoEleicao() {
        return estadoEleicao;
    }

    public void setEstadoEleicao(String estadoEleicao) {
        this.estadoEleicao = estadoEleicao;
    }

    public String getMunicipioEleicao() {
        return municipioEleicao;
    }

    public void setMunicipioEleicao(String municipioEleicao) {
        this.municipioEleicao = municipioEleicao;
    }

    public String getCargoEleicao() {
        return cargoEleicao;
    }

    public void setCargoEleicao(String cargoEleicao) {
        this.cargoEleicao = cargoEleicao;
    }

    public String getStatusCandidatura() {
        return statusCandidatura;
    }

    public void setStatusCandidatura(String statusCandidatura) {
        this.statusCandidatura = statusCandidatura;
    }

    public String getColigacao() {
        return coligacao;
    }

    public void setColigacao(String coligacao) {
        this.coligacao = coligacao;
    }
}
