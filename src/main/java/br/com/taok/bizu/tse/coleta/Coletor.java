package br.com.taok.bizu.tse.coleta;

import br.com.taok.bizu.tse.model.Candidatura;

import javax.enterprise.context.Dependent;
import javax.ws.rs.client.ClientBuilder;

@Dependent
public class Coletor {

    public Candidatura colete(){

       return ClientBuilder.newClient()
                .target("http://divulgacandcontas.tse.jus.br/divulga/rest/v1/candidatura/listar/2018/CE/2022802018/3/candidatos")
                .request()
                .get()
                .readEntity(Candidatura.class);
    }
}