package br.com.taok.bizu.tse.model;

public enum ConfirmacaoEleicao {

    ELEITO("Eleito"),
    TURNO_2("2º turno"),
    ELEITO_POR_QP("Eleito por QP"),
    ELEITO_POR_MEDIA("Eleito por média"),
    ELEITO_CSV("ELEITO");


    private final String descricaoConfirmacao;

    ConfirmacaoEleicao(String descricaoConfirmacao) {
        this.descricaoConfirmacao = descricaoConfirmacao;
    }

    public static boolean foiEleito(String descricaoConfirmacao){
        for (ConfirmacaoEleicao confirmacao : ConfirmacaoEleicao.values()) {
            return confirmacao.getDescricaoConfirmacao().equals(descricaoConfirmacao);
        }
        return false;
    }
    
    public String getDescricaoConfirmacao() {
        return descricaoConfirmacao;
    }
}
