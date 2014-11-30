/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cofeature;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author charleshenriqueportoferreira
 */
public class Feature {

    String nome;
    double infoGain;
    private final List<Classe> classes;
    private final List<Arquivo> arquivos;
    Map<String, Integer> arquivoClasse;
    private String pathArquivoOrigem;
    private String nomeClasseOrigem;
    private String nomeArquivoOrigem;
    private double dominancia;

    public Feature(String nome, double infoGain) {
        classes = new ArrayList<>();
        arquivos = new ArrayList<>();
        arquivoClasse = new HashMap<>();
        this.nome = nome;
        this.infoGain = infoGain;
    }

    Feature(String nome, String arquivoOrigem) {
        classes = new ArrayList<>();
        arquivos = new ArrayList<>();
        arquivoClasse = new HashMap<>();
        this.nome = nome;
        this.pathArquivoOrigem = arquivoOrigem;
        
    }

    public String getNomeArquivoOrigem() {
        return nomeArquivoOrigem;
    }

    public void setNomeArquivoOrigem(String nomeArquivoOrigem) {
        this.nomeArquivoOrigem = nomeArquivoOrigem;
    }
    
    public String getNomeClasseOrigem() {
        return nomeClasseOrigem;
    }

    public void setNomeClasseOrigem(String nomeClasseOrigem) {
        this.nomeClasseOrigem = nomeClasseOrigem;
    }
    
    public String getPathArquivoOrigem() {
        return pathArquivoOrigem;
    }

    public void setPathArquivoOrigem(String pathArquivoOrigem) {
        this.pathArquivoOrigem = pathArquivoOrigem;
    }

    public void addClasseArquivo(String nomeClasse) {
        if (arquivoClasse.containsKey(nomeClasse)) {
            int valorAtual = arquivoClasse.get(nomeClasse);
            valorAtual++;
            arquivoClasse.put(nomeClasse, valorAtual);
        } else {
            arquivoClasse.put(nomeClasse, 1);
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getInfoGain() {
        return infoGain;
    }

    public void setInfoGain(double infoGain) {
        this.infoGain = infoGain;
    }

    public void addArquivo(Arquivo arquivo) {
        if (!arquivos.contains(arquivo)) {
            arquivos.add(arquivo);
        }
    }

    public void addClasse(Classe classe) {
        if (!classes.contains(classe)) {
            classes.add(classe);
        }
    }

    public List<Classe> getClasses() {
        return classes;
    }

    public List<Arquivo> getArquivos() {
        return arquivos;
    }

    @Override
    public String toString() {
        return "\nFeature{" + "nome=" + nome + ", infoGain=" + infoGain + '}';
        //return nome + " ";
    }

    public double getDominancia() {
        return dominancia;
    }

    public void setDominancia(double dominancia) {
        this.dominancia = dominancia;
    }

}
