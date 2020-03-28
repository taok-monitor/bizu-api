package br.com.taok.bizu.tse.model;

import java.util.Objects;

public class Cargo {

    private Integer codigo;
    private String sigla;
    private String nome;
    private Integer codSuperior;
    private boolean titular;
    private Integer contagem;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getCodSuperior() {
        return codSuperior;
    }

    public void setCodSuperior(Integer codSuperior) {
        this.codSuperior = codSuperior;
    }

    public boolean isTitular() {
        return titular;
    }

    public void setTitular(boolean titular) {
        this.titular = titular;
    }

    public Integer getContagem() {
        return contagem;
    }

    public void setContagem(Integer contagem) {
        this.contagem = contagem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cargo cargo = (Cargo) o;
        return codigo.equals(cargo.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}
