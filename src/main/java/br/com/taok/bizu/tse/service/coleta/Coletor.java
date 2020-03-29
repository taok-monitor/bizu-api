package br.com.taok.bizu.tse.service.coleta;

import br.com.taok.bizu.tse.model.Eleicao;
import br.com.taok.bizu.tse.model.Eleicoes;
import br.com.taok.bizu.tse.model.Localidade;

import javax.enterprise.context.Dependent;
import javax.ws.rs.client.ClientBuilder;
import java.util.ArrayList;
import java.util.List;

@Dependent
public class Coletor {

    public List<Eleicao> colete(Eleicoes eleicoes){

        List<Eleicao> eleicoesColetadas = new ArrayList<>();
        Localidade localidade = new Localidade();
        localidade.setSigla("CE");

        eleicoes.getURLs(localidade).stream().forEach(url -> {
            eleicoesColetadas.add(colete(url));
        });

        return  eleicoesColetadas;
    }

    private Eleicao colete(String url){

        System.out.println("COLETANDO ELEICAO: URL: "+url);

        return ClientBuilder.newClient()
                .target(url)
                .request()
                .get()
                .readEntity(Eleicao.class);
    }

    private Eleicao colete(){

       return ClientBuilder.newClient()
                .target("http://divulgacandcontas.tse.jus.br/divulga/rest/v1/candidatura/listar/2018/CE/2022802018/3/candidatos")
                .request()
                .get()
                .readEntity(Eleicao.class);
    }
}