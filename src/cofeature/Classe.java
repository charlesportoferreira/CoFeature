/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cofeature;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author charleshenriqueportoferreira
 */
public class Classe {

    private String nome;
    private List<Arquivo> arquivos;

    Classe(String name) {
        arquivos = new ArrayList<>();
        this.nome = name;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Arquivo> getArquivos() {
        return arquivos;
    }

    public void setArquivos(List<Arquivo> arquivos) {
        this.arquivos = arquivos;
    }

    public void addArquivo(Arquivo arquivo) {
        this.arquivos.add(arquivo);
    }

    @Override
    public String toString() {
        return "\nClasse{" + "nome=" + nome + ",\n arquivos=" + arquivos + '}';
    }
    
    
}
