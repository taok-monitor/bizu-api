package br.com.taok.bizu.model;

import io.quarkus.mongodb.panache.ProjectionFor;

@ProjectionFor(Candidatura.class)
public class Municipio {
    public String municipioEleicao;

    public String getMunicipioEleicao(){
        return this.municipioEleicao;
    }
}
