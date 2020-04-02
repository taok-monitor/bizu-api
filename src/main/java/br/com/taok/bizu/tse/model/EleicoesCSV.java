package br.com.taok.bizu.tse.model;

public enum EleicoesCSV {

    ELEICAO_2018(2018, "/home/cassunde/Documents/bizu/arquivos/2018"),
    ELEICAO_2016(2016,"/home/cassunde/Documents/bizu/arquivos/2016"),
    ELEICAO_2014(2014,"/home/cassunde/Documents/bizu/arquivos/2014");

    private final int anoEleicao;
    private final String pathArquivo;

    EleicoesCSV(int anoEleicao, String pathArquivo) {
        this.anoEleicao = anoEleicao;
        this.pathArquivo = pathArquivo;
    }

    public int getAnoEleicao() {
        return anoEleicao;
    }

    public String pathCandidatos(String estado){
        return this.pathArquivo+"/consulta_cand_"+getAnoEleicao()+"/consulta_cand_"+getAnoEleicao()+"_"+estado+".csv";
    }

    public String pathCandidatosBens(String estado){
        return this.pathArquivo+"/bem_candidato_"+getAnoEleicao()+"/bem_candidato_"+getAnoEleicao()+"_"+estado+".csv";
    }

    public String pathCandidatosCassacao(String estado){
        return this.pathArquivo+"/motivo_cassacao_"+getAnoEleicao()+"/motivo_cassacao_"+getAnoEleicao()+"_"+estado+".csv";
    }

    public static EleicoesCSV encontraPorAnoEleitora(int anoEleicao){
        for (EleicoesCSV eleicoes : EleicoesCSV.values()) {
            if( eleicoes.anoEleicao == anoEleicao ){
                return eleicoes;
            }
        }
        return null;
    }
}
