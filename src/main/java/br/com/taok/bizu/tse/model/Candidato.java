package br.com.taok.bizu.tse.model;

public class Candidato {

    private String id;
    private String nomeUrna;
    private Integer numero;
    private String nomeCompleto;
    private String descricaoSituacao;
    private String descricaoTotalizacao;
    private String nomeColigacao;
    private Partido partido;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomeUrna() {
        return nomeUrna;
    }

    public void setNomeUrna(String nomeUrna) {
        this.nomeUrna = nomeUrna;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getDescricaoSituacao() {
        return descricaoSituacao;
    }

    public void setDescricaoSituacao(String descricaoSituacao) {
        this.descricaoSituacao = descricaoSituacao;
    }

    public String getDescricaoTotalizacao() {
        return descricaoTotalizacao;
    }

    public void setDescricaoTotalizacao(String descricaoTotalizacao) {
        this.descricaoTotalizacao = descricaoTotalizacao;
    }

    public String getNomeColigacao() {
        return nomeColigacao;
    }

    public void setNomeColigacao(String nomeColigacao) {
        this.nomeColigacao = nomeColigacao;
    }

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }
}
