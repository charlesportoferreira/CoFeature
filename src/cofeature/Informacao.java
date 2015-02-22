/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cofeature;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author charleshenriqueportoferreira
 */
public class Informacao implements Serializable {
  
    private String nome;
    private String nomeClasseOrigem;
    private BigInteger count;

    public Informacao(String nome, String nomeClasseOrigem, BigInteger count) {
        this.nome = nome;
        this.nomeClasseOrigem = nomeClasseOrigem;
        this.count = count;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeClasseOrigem() {
        return nomeClasseOrigem;
    }

    public void setNomeClasseOrigem(String nomeClasseOrigem) {
        this.nomeClasseOrigem = nomeClasseOrigem;
    }

    public BigInteger getCount() {
        return count;
    }

    public void setCount(BigInteger count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "NewClass{" + "nome=" + nome + ", nomeClasseOrigem=" + nomeClasseOrigem + ", count=" + count + '}';
    }

   

    
    
    
}
