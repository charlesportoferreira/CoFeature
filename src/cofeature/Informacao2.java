/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cofeature;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author charleshenriqueportoferreira
 */
public class Informacao2 {
    private String nome;
    List<BigDecimal> counts;
    private double infogain;

    
    
    public Informacao2(String nome) {
        this.nome = nome;
        counts = new ArrayList<>();
    }
    
    public void add(BigDecimal bd){
        this.counts.add(bd);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getInfogain() {
        return infogain;
    }

    public void setInfogain(double infogain) {
        this.infogain = infogain;
    }

   
    
    
}
