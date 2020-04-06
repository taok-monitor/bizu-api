package br.com.taok.bizu.tse.service.coleta;

import br.com.taok.bizu.model.Bem;
import br.com.taok.bizu.model.Candidatura;
import br.com.taok.bizu.model.Cassacao;
import com.opencsv.CSVReader;

import javax.enterprise.context.Dependent;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Dependent
public class LeitorCSV {

    public List<Candidatura> lerCSV(String csvFile, int anoEleicao) {

        System.out.println(csvFile);
        CSVReader reader = null;
        List<Candidatura> candidaturas = new ArrayList<>();
        try {
            reader = new CSVReader(new FileReader(csvFile), ';');
            String[] line;
            int contador = 0;
            while ((line = reader.readNext()) != null) {
                if(contador > 0 ){

                    Candidatura candidatura = new Candidatura();
                    candidatura.setCodigoCandidato(line[15]);
                    candidatura.setNomeCandidato(line[17]);
                    candidatura.setNumeroEleicao(Integer.parseInt(line[16]));
                    candidatura.setPartido(line[28]);
                    candidatura.setEleito(estaEleito(line[53]));
                    candidatura.setAnoEleicao(anoEleicao);
                    candidatura.setEstadoEleicao(line[10]);
                    candidatura.setMunicipioEleicao(line[12]);
                    candidatura.setCargoEleicao(line[14]);
                    candidatura.setStatusCandidatura(line[25]);
                    candidatura.setColigacao(line[31]);
                    candidatura.setNomeCandidatoNaUrna(line[18]);

                    candidaturas.add(candidatura);
                }
                contador ++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return candidaturas;
    }

    public List<Bem> lerCSVBem(String csvFile) {

        System.out.println(csvFile);
        CSVReader reader = null;
        List<Bem> bens = new ArrayList<>();
        try {
            reader = new CSVReader(new FileReader(csvFile), ';');
            String[] line;
            int contador = 0;
            while ((line = reader.readNext()) != null) {
                if(contador > 0 ){

                    Bem bem = new Bem();
                    bem.setCodigoCandidato(line[11]);
                    bem.setTipo(line[14]);
                    bem.setValor(new BigDecimal(line[16].replace(",",".")));

                    bens.add(bem);
                }
                contador ++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bens;
    }

    public List<Cassacao> lerCSVCassacao(String csvFile) {

        System.out.println(csvFile);
        CSVReader reader = null;
        List<Cassacao> cassacoes = new ArrayList<>();
        try {
            reader = new CSVReader(new FileReader(csvFile), ';');
            String[] line;
            int contador = 0;
            while ((line = reader.readNext()) != null) {
                if(contador > 0 ){

                    Cassacao cassacao = new Cassacao();
                    cassacao.setCodigoCandidato(line[10]);
                    cassacao.setMotivoCanssacao(line[11]);
                    cassacoes.add(cassacao);
                }
                contador ++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cassacoes;
    }

    private static boolean estaEleito(String situacao){
        return situacao.equals("ELEITO") || situacao.equals("ELEITO POR QP")|| situacao.equals("ELEITO POR MÉDIA");
    }
}