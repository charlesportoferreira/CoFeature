/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cofeature;

/**
 *
 * @author charleshenriqueportoferreira
 */
public class Arquivo {

    private String nome;
    private String path;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        // return "\nArquivo{" + "nome=" + nome + ", path=" + path + '}';
        return "\nArquivo{" + "nome=" + nome + '}';
    }

}
