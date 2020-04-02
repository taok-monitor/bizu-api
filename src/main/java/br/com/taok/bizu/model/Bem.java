package br.com.taok.bizu.model;

import java.math.BigDecimal;

public class Bem {

    private String codigoCandidato;
    private String tipo;
    private BigDecimal valor;

    public String getCodigoCandidato() {
        return codigoCandidato;
    }

    public void setCodigoCandidato(String codigoCandidato) {
        this.codigoCandidato = codigoCandidato;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Bem{" +
                "codigoCandidato='" + codigoCandidato + '\'' +
                ", tipo='" + tipo + '\'' +
                ", valor=" + valor +
                '}';
    }
}
