package br.com.taok.bizu.tse.model;

import java.util.Arrays;
import java.util.List;

public enum EleicoesCargos {

    CARGOS_2018(Arrays.asList(3,4,5,6,7,8,9,10)),
    CARGOS_2016(Arrays.asList(11,12,13)),
    CARGOS_2014(Arrays.asList(3,4,5,6,7,8,9,10));

    private final List<Integer> cargos;

    EleicoesCargos(List<Integer> cargos) {
        this.cargos = cargos;
    }

    public List<Integer> getCargos() {
        return cargos;
    }
}
