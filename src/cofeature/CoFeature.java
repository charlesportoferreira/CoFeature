/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cofeature;

import br.edu.ufabc.util.Util;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author charleshenriqueportoferreira
 */
public class CoFeature {

    public static void main(String[] args) {
        CoFeature coFeature = new CoFeature();
        String diretorio = System.getProperty("user.dir") + "/20newsbydatetest_Maid";
        GainInformation gi = new GainInformation(diretorio);
        List<String> textos = Util.getFilesPath(new File(diretorio), 0);
        List<Feature> features = new ArrayList<>();
        for (String texto : textos) {
            features.addAll(coFeature.getFeatures(texto, gi));
        }
        System.out.println(features.size());

        for (Feature feature : features) {
            feature.setInfoGain(gi.getGanhoInformacao(feature));
        }
        features.sort(new ComparadorFeature());

        System.out.println(features.toString());
        coFeature.generateCoFeature(features);

    }

    private  List<Feature> getFeatures(String filePath, GainInformation gi) {
        List<Feature> features = new ArrayList<>();
        String pattern = "[a-zA-Z]+";
        String[] palavras;
        try (FileReader fr = new FileReader(filePath); BufferedReader br = new BufferedReader(fr)) {
            while (br.ready()) {
                palavras = br.readLine().split(" ");
                for (String palavra : palavras) {
                    palavra = palavra.replaceAll("\\.", "");
                    if (palavra.matches(pattern)) {
                        Feature f = new Feature(palavra, filePath);
                        gi.updateClasseArquivoOrigem(f);
                        features.add(f);
                    }
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
        return features;
    }

    public  List<Feature> generateCoFeature(List<Feature> features) {
        String diretorio = System.getProperty("user.dir") + "/20newsbydatetest_Maid";
        List<String> filePaths = Util.getFilesPath(new File(diretorio), 0);
        List<Feature> coFeatures = new ArrayList<>();
        List<Feature> featuresPorArquivo;
        for (String filePath : filePaths) {
            featuresPorArquivo = new ArrayList<>();
            for (Feature feature : features) {
                if (filePath.equals(feature.getPathArquivoOrigem())) {
                    featuresPorArquivo.add(feature);
                }
            }
            //coFeatures.addAll(combineUnigram(featuresPorArquivo));
            combineUnigram(featuresPorArquivo, filePath);
            //calculaDominancia();
        }
        // convert to set to remove duplicates
//        Set<Feature> s = new HashSet<>(features);
//        for (Feature s1 : s) {
//            for (Feature s2 : s) {
//                
//            }
//        }
        return coFeatures;
    }

    private  void combineUnigram(List<Feature> features, String filePath) {
        Map<String, Integer> cofeatures = new HashMap<>();
        String nomeClasse = features.get(0).getNomeClasseOrigem();
        String nomeArquivo = features.get(0).getNomeArquivoOrigem();
        System.out.print("20newsbydatetest_Maid/" + nomeClasse + "/" + nomeArquivo);
        StringBuilder sb = new StringBuilder();
        for (Feature feature : features) {
            for (Feature feature1 : features) {
                if (feature.getNome().equals(feature1.getNome())) {
                    continue;
                }
                String cf = feature.getNome() + "_" + feature1.getNome();
                if (cofeatures.containsKey(cf)) {
                    int valorAtual = cofeatures.get(cf);
                    valorAtual++;
                    cofeatures.put(cf, valorAtual);
                } else {
                    cofeatures.put(cf, 1);
                }
            }
        }
        Iterator it = cofeatures.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry) it.next();
            sb.append("\n");
            sb.append(pairs.getKey());
            sb.append(":");
            sb.append(pairs.getValue());
            it.remove(); // avoids a ConcurrentModificationException
        }
        // sb.append("\n").append(feature.getNome()).append("_").append(feature1.getNome()).append(":1");
        //try {
        System.out.println(sb.toString());
        //Util.printFile("2Gram.txt", sb.toString());
        //} catch (IOException ex) {
        //  Logger.getLogger(CoFeature.class.getName()).log(Level.SEVERE, null, ex);
        //}

    }
}
