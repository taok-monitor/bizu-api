package br.com.taok.bizu.tse.model;

public enum EleicoesURL {

    ELEICAO_2018("http://divulgacandcontas.tse.jus.br/divulga/rest/v1/candidatura/listar/2018/::localidade/2022802018/::cargo/candidatos"),
    ELEICAO_2016("http://divulgacandcontas.tse.jus.br/divulga/rest/v1/candidatura/listar/2016/::localidade/2/::cargo/candidatos"),
    ELEICAO_2014("http://divulgacandcontas.tse.jus.br/divulga/rest/v1/candidatura/listar/2014/::localidade/680/::cargo/candidatos");

    private String url;

    EleicoesURL(String url) {
        this.url = url;
    }

    public String getUrl(Localidade localidade, Integer cargo) {
        return url
                .replace("::localidade", localidade.getSigla() == null? localidade.getCodigo(): localidade.getSigla())
                .replace("::cargo", cargo.toString());
    }
}
