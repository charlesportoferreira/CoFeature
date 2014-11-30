/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cofeature;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author charleshenriqueportoferreira
 */
public class GainInformation {

    private final List<Classe> classes;

    public GainInformation(String caminhoBaseDados) {
        this.classes = new ArrayList<>();
        fileTreePrinter(new File(caminhoBaseDados), 0);
    }

    public double getGanhoInformacao(Feature feature) {

        int nrArquivos = getNumeroTotalArquivos();
        //feature.setInfoGain(getGanhoInformacao(feature, nrArquivos));
        // System.out.println(feature.toString());
        return getGanhoInformacao(feature, nrArquivos);
    }

    private double getGanhoInformacao(Feature feature, int nrTotalArquivos) {

        double somatorio1 = 0.0;
        double probClasse;
        double somatorio2 = 0.0;
        double somatorio3 = 0;
        double probTermo = getProbabilidadeTermo(feature);
        double probNaoTermo = 1 - probTermo;
        double probTermoClasse = 0;
        double probNaoTermoClasse = 0;

        for (Classe classe : classes) {
            probClasse = (double) classe.getArquivos().size() / nrTotalArquivos;
            somatorio1 += probClasse * Math.log(probClasse);
            probTermoClasse = getProbabilidadeTermoClasse(feature, classe);
            probNaoTermoClasse = 1 - probTermoClasse;
            somatorio2 += probTermoClasse == 0 ? 0 : probTermoClasse * Math.log(probTermoClasse);
            somatorio3 += probNaoTermoClasse == 0 ? 0 : probNaoTermoClasse * Math.log(probNaoTermoClasse);
        }

        return -somatorio1 + probTermo * somatorio2 + probNaoTermo * somatorio3;
    }

    private void fileTreePrinter(File initialPath, int initialDepth) {
        int depth = initialDepth;
        if (initialPath.exists()) {
            File[] contents = initialPath.listFiles();
            for (File content : contents) {
                if (content.isDirectory()) {
                    classes.add(new Classe(content.getName()));
                    fileTreePrinter(content, initialDepth + 1);
                } else {
                    if (content.getName().contains(".txt")) {
                        Arquivo a = new Arquivo();
                        a.setNome(content.getName());
                        a.setPath(content.getAbsolutePath());
                        classes.get(classes.size() - 1).addArquivo(a);
                    }
                }
            }
        }
    }

    public static void printFile(String fileName, String texto, boolean isSobreescrever) throws IOException {
        try (FileWriter fw = new FileWriter(new File(fileName), isSobreescrever); BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(texto);
            bw.newLine();
            bw.close();
            fw.close();
        }
    }

    public int getNumeroTotalArquivos() {
        int total = 0;
        for (Classe classe : classes) {
            total += classe.getArquivos().size();
        }
        return total;
    }

    public double getProbabilidadeTermo(Feature feature) {
        for (Classe classe : classes) {
            for (Arquivo arquivo : classe.getArquivos()) {
                if (possuiTermo(arquivo, feature)) {
                    feature.addArquivo(arquivo);
                    feature.addClasse(classe);
                    feature.addClasseArquivo(classe.getNome());
                }
            }
        }
        double probTermo = (double) feature.getClasses().size() / classes.size();
        return probTermo;
        //return feature.getArquivos().size() / getNumeroTotalArquivos();

    }

    private boolean possuiTermo(Arquivo arquivo, Feature feature) {
        boolean possuiTermo = false;
        String linha = "";
        try (FileReader fr = new FileReader(arquivo.getPath()); BufferedReader br = new BufferedReader(fr)) {
            while (br.ready()) {
                linha = br.readLine();
                if (linha.contains(feature.nome)) {
                    possuiTermo = true;
                    break;
                }
            }
            br.close();
            fr.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(GainInformation.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GainInformation.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return possuiTermo;
    }

    private int getNumeroClasses() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private double getProbabilidadeTermoClasse(Feature feature, Classe classe) {
        Iterator it = feature.arquivoClasse.entrySet().iterator();
        int qtdeArquivos = 0;
        int qtdePorClasse = 0;
        String c = "_";
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry) it.next();
            c = (String) pairs.getKey();
            if (c.equals(classe.getNome())) {
                qtdePorClasse += (int) pairs.getValue();
                qtdeArquivos += (int) pairs.getValue();
            } else {
                qtdeArquivos += (int) pairs.getValue();
            }
            it.remove(); // avoids a ConcurrentModificationException
        }
        if (qtdeArquivos == 0) {
            return 0;
        }
        double prob = 0;
        try {
            prob = (double) qtdePorClasse / qtdeArquivos;
        } catch (ArithmeticException e) {
            System.out.println(feature.nome + " " + c + " " + qtdePorClasse + " " + qtdeArquivos);
        }
        return prob;
    }

    public void updateClasseArquivoOrigem(Feature feature) {
        for (Classe classe : classes) {
            for (Arquivo arquivo : classe.getArquivos()) {
                if (arquivo.getPath().equals(feature.getPathArquivoOrigem())) {
                    feature.setNomeClasseOrigem(classe.getNome());
                    feature.setNomeArquivoOrigem(arquivo.getNome());
                    return;
                }
            }
        }
        throw new RuntimeException("classe nao encontrada: " + feature.getPathArquivoOrigem());
    }
}
