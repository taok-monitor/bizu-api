package br.com.taok.bizu.tse.model;

import java.util.ArrayList;
import java.util.List;

public class Candidatura {

    Localidade unidadeEleitoral;
    Cargo cargo;
    List<Candidato> candidatos = new ArrayList<>();

    public Localidade getUnidadeEleitoral() {
        return unidadeEleitoral;
    }

    public void setUnidadeEleitoral(Localidade unidadeEleitoral) {
        this.unidadeEleitoral = unidadeEleitoral;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public List<Candidato> getCandidatos() {
        return candidatos;
    }

    public void setCandidatos(List<Candidato> candidatos) {
        this.candidatos = candidatos;
    }
}
