package br.com.taok.bizu.tse.model;

import br.com.taok.bizu.model.Candidatura;
import br.com.taok.bizu.util.StringUtil;

public enum EleicoesCSV {

    ELEICAO_2018(2018, "/home/cassunde/Documents/bizu/arquivos/2018","https://cdn-eleicoes.gazetadopovo.com.br/fotos/ceara/::cargo/::nomeurna.jpg"),
    ELEICAO_2016(2016,"/home/cassunde/Documents/bizu/arquivos/2016","https://gazetadopovo-candidatos-2016.s3.amazonaws.com/fotos/::estado/::municipio/::nomeurna.jpg"),
    ELEICAO_2014(2014,"/home/cassunde/Documents/bizu/arquivos/2014","");

    private final int anoEleicao;
    private final String pathArquivo;
    private final String urlFoto;

    EleicoesCSV(int anoEleicao, String pathArquivo, String urlFoto) {
        this.anoEleicao = anoEleicao;
        this.pathArquivo = pathArquivo;
        this.urlFoto = urlFoto;
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

    public String getUrlFotoPorCandidato(Candidatura candidatura) {


        String cargo = candidatura.getCargoEleicao()
                .toLowerCase()
                .replace(" ","-");
        String nomeCandidato = candidatura.getNomeCandidatoNaUrna()
                .toLowerCase()
                .replace(" ","-");

        if(candidatura.getCargoEleicao().equals("DEPUTADO ESTADUAL")
                || candidatura.getCargoEleicao().equals("DEPUTADO FEDERAL")
                || candidatura.getCargoEleicao().equals("PREFEITO")
                || candidatura.getCargoEleicao().equals("VICE-PREFEITO")
                || candidatura.getCargoEleicao().equals("VEREADOR")){
            nomeCandidato = nomeCandidato + "-"+candidatura.getNumeroEleicao().toString();
        }

        return StringUtil.removeAccents(urlFoto
                .replace("::cargo",cargo)
                .replace("::estado", candidatura.getEstadoEleicao().toLowerCase())
                .replace("::municipio", candidatura.getMunicipioEleicao()
                        .replace(" ","-").toLowerCase())
                .replace("::nomeurna", nomeCandidato
                        .replace(",","")
                        .replace(".","")));
    }

    public static EleicoesCSV encontraPorAnoEleitoral(int anoEleicao){
        for (EleicoesCSV eleicoes : EleicoesCSV.values()) {
            if( eleicoes.anoEleicao == anoEleicao ){
                return eleicoes;
            }
        }
        return null;
    }
}
