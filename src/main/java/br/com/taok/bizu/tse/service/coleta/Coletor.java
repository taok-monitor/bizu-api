package br.com.taok.bizu.tse.service.coleta;

import br.com.taok.bizu.tse.model.Eleicao;
import br.com.taok.bizu.tse.model.Eleicoes;
import br.com.taok.bizu.tse.model.Localidade;

import javax.enterprise.context.Dependent;
import javax.validation.constraints.NotNull;
import javax.ws.rs.client.ClientBuilder;
import java.util.ArrayList;
import java.util.List;

@Dependent
public class Coletor {

    public List<Eleicao> colete(@NotNull Eleicoes eleicoes, Localidade localidade){

        List<Eleicao> eleicoesColetadas = new ArrayList<>();
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
}