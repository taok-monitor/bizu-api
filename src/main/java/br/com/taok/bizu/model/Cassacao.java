package br.com.taok.bizu.model;

public class Cassacao {

    private String codigoCandidato;
    private String motivoCanssacao;

    public String getCodigoCandidato() {
        return codigoCandidato;
    }

    public void setCodigoCandidato(String codigoCandidato) {
        this.codigoCandidato = codigoCandidato;
    }

    public String getMotivoCanssacao() {
        return motivoCanssacao;
    }

    public void setMotivoCanssacao(String motivoCanssacao) {
        this.motivoCanssacao = motivoCanssacao;
    }

    @Override
    public String toString() {
        return "Cassacao{" +
                "codigoCandidato='" + codigoCandidato + '\'' +
                ", motivoCanssacao='" + motivoCanssacao + '\'' +
                '}';
    }
}
