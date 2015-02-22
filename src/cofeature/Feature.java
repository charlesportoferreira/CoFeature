/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cofeature;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 *
 * @author charleshenriqueportoferreira
 */
@Entity
public class Feature implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    
    String nome;
    
    double infoGain;
    @Transient
    private  List<Classe> classes;
    @Transient
    private int qtdeArquivos;
    @Transient
    Map<String, Integer> arquivoClasse;
    @Transient
    private String pathArquivoOrigem;
    private String nomeClasseOrigem;
    private String nomeArquivoOrigem;
    private double dominancia;

    public Feature()    {

    }

    public Feature(String nome, double infoGain) {
        classes = new ArrayList<>();
        // qtdeArquivos = new ArrayList<>();
        arquivoClasse = new HashMap<>();
        this.nome = nome;
        this.infoGain = infoGain;
    }

    Feature(String nome, String pathArquivoOrigem) {
        classes = new ArrayList<>();
        //arquivos = new ArrayList<>();
        arquivoClasse = new HashMap<>();
        this.nome = nome;
        this.pathArquivoOrigem = pathArquivoOrigem;

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

    // public void addArquivo(Arquivo qtdeArquivos) {
    //   if (!qtdeArquivos.contains(qtdeArquivos)) {
    //     qtdeArquivos.add(qtdeArquivos);
    //}
    // }
    public void addClasse(Classe classe) {
        if (!classes.contains(classe)) {
            classes.add(classe);
        }
    }

    public List<Classe> getClasses() {
        return classes;
    }

    // public List<Arquivo> getArquivos() {
    //   return qtdeArquivos;
    //}
    @Override
    public String toString() {
        return nome + ":" + infoGain + ":" + nomeClasseOrigem + ":" + nomeArquivoOrigem;
    }

    public double getDominancia() {
        return dominancia;
    }

    public void setDominancia(double dominancia) {
        this.dominancia = dominancia;
    }

    public int getQtdeArquivos() {
        return qtdeArquivos;
    }

    public void setQtdeArquivos(int qtdeArquivos) {
        this.qtdeArquivos = qtdeArquivos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
