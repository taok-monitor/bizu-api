package br.com.taok.bizu.service;

import br.com.taok.bizu.tse.model.Localidade;

import javax.enterprise.context.Dependent;
import javax.ws.rs.client.ClientBuilder;

@Dependent
public class LocalidadeService {

    public Localidade carregaLocalidadeDoEstado(String siglaEstado){

        return ClientBuilder.newClient()
                .target("http://divulgacandcontas.tse.jus.br/divulga/rest/v1/eleicao/buscar/"+siglaEstado+"/2/municipios")
                .request()
                .get()
                .readEntity(Localidade.class);
    }
}
