package br.com.taok.bizu.model;

import br.com.taok.bizu.tse.model.*;
import io.quarkus.mongodb.panache.PanacheMongoEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Candidatura extends PanacheMongoEntity {

    private String codigoCandidato;
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
    private String urlFoto;
    private String nomeCandidatoNaUrna;
    private List<Bem> bens = new ArrayList<>();
    private List<Cassacao> cassacoes = new ArrayList<>();

    public Candidatura() {
    }

    public Candidatura(
            Candidato candidato,
            Integer anoEleicao,
            Localidade localidade,
            Cargo cargo,
            Localidade municipio) {

        this.codigoCandidato = candidato.getId();
        this.nomeCandidato = candidato.getNomeCompleto();
        this.numeroEleicao = candidato.getNumero();
        this.partido = candidato.getPartido().getSigla();
        this.eleito = ConfirmacaoEleicao.foiEleito(candidato.getDescricaoTotalizacao());
        this.patrimonioDeclarado = BigDecimal.ZERO;
        this.anoEleicao = anoEleicao;
        this.estadoEleicao = localidade.getSigla();
        this.municipioEleicao =  municipio.getNome() == null? "": municipio.getNome();
        this.cargoEleicao = cargo.getNome();
        this.statusCandidatura = candidato.getDescricaoSituacao();
        this.coligacao = candidato.getNomeColigacao();
    }

    public void adicionaBens(List<Bem> bens){
        this.bens.addAll(bens);
    }

    public void adicionarCassacao(List<Cassacao> cassacoes){
        this.cassacoes.addAll(cassacoes);
    }

    public String getCodigoCandidato() {
        return codigoCandidato;
    }

    public void setCodigoCandidato(String codigoCandidato) {
        this.codigoCandidato = codigoCandidato;
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

    public String getNomeCandidatoNaUrna() {
        return nomeCandidatoNaUrna;
    }

    public void setNomeCandidatoNaUrna(String nomeCandidatoNaUrna) {
        this.nomeCandidatoNaUrna = nomeCandidatoNaUrna;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public List<Bem> getBens() {
        return bens;
    }

    public void setBens(List<Bem> bens) {
        this.bens = bens;
    }

    public List<Cassacao> getCassacoes() {
        return cassacoes;
    }

    public void setCassacoes(List<Cassacao> cassacoes) {
        this.cassacoes = cassacoes;
    }

    public BigDecimal valorTotalDeBens(){
        return this.bens.stream()
                .map(b -> b.getValor())
                .reduce(BigDecimal.ZERO,BigDecimal::add);
    }

    @Override
    public String toString() {
        return "Candidatura{" +
                "codigoCandidato='" + codigoCandidato + '\'' +
                ", nomeCandidato='" + nomeCandidato + '\'' +
                ", numeroEleicao=" + numeroEleicao +
                ", partido='" + partido + '\'' +
                ", eleito=" + eleito +
                ", patrimonioDeclarado=" + patrimonioDeclarado +
                ", anoEleicao=" + anoEleicao +
                ", estadoEleicao='" + estadoEleicao + '\'' +
                ", municipioEleicao='" + municipioEleicao + '\'' +
                ", cargoEleicao='" + cargoEleicao + '\'' +
                ", statusCandidatura='" + statusCandidatura + '\'' +
                ", coligacao='" + coligacao + '\'' +
                ", bens=" + bens.size() +
                '}';
    }
}
