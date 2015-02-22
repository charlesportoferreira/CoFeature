/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cofeature;

import br.edu.ufabc.util.Util;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
    private Map<String, StringBuilder> arquivosBaseDados;

    List<String> arquivosVisitados = new ArrayList<>();

    public List<Classe> getClasses() {
        return classes;
    }

    public GainInformation(String caminhoBaseDados) {
        this.classes = new ArrayList<>();
        arquivosBaseDados = new HashMap<>();
        fileTreePrinter(new File(caminhoBaseDados), 0);
        // getBaseDadosInMemory(caminhoBaseDados);
    }

    public double getGanhoInformacao(Feature feature, List<Feature> features) {

        int nrArquivos = getNumeroTotalArquivos();
        //feature.setInfoGain(getGanhoInformacao(feature, nrArquivos));
        // System.out.println(feature.toString());
        return getGanhoInformacao(feature, nrArquivos, features);
    }

    private double getGanhoInformacao(Feature feature, int nrTotalArquivos, List<Feature> features) {
        double somatorio1 = 0.0;
        double probClasse;
        double somatorio2 = 0.0;
        double somatorio3 = 0;
        double probTermo = getProbabilidadeTermo(feature, features);
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

    private double getQtdeArquivoClasse(Classe classe, Feature feature) {
        double qtdeArquivoClasse = 0.0;
        for (Arquivo arquivo : classe.getArquivos()) {
            if (possuiTermo(arquivo, feature)) {
                qtdeArquivoClasse++;
            }
        }
        return qtdeArquivoClasse;
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

    public double getProbabilidadeTermo(Feature feature, List<Feature> features) {
        arquivosVisitados.clear();
        String dadoProcurado = feature.getNome();
        double qtde = 0.0;

        for (Feature f : features) {
            if (f.getNome().equals(dadoProcurado) && !arquivosVisitados.contains(f.getNomeArquivoOrigem())) {
                qtde++;
                arquivosVisitados.add(f.getNomeArquivoOrigem());
                String classe = getClasse(f.getNomeArquivoOrigem());
                if (feature.arquivoClasse.containsKey(classe)) {
                    int value = feature.arquivoClasse.get(classe);
                    value++;
                    feature.arquivoClasse.put(classe, value);
                } else {
                    feature.arquivoClasse.put(classe, 1);
                }
            }
        }
        feature.setQtdeArquivos((int) qtde);
        //for (Classe classe : classes) {
        //  for (Arquivo arquivo : classe.getArquivos()) {
        //    if (possuiTermo(arquivo, feature)) {
        //      feature.addArquivo(arquivo);
        //    feature.addClasse(classe);
        //  feature.addClasseArquivo(classe.getNome());
        //}
        //}
        //}
        //double probTermo = (double) feature.getClasses().size() / classes.size();
        //return probTermo;
        return qtde / getNumeroTotalArquivos();

    }

    private boolean possuiTermo(Arquivo arquivo, Feature feature) {
        String texto = (String) arquivosBaseDados.get(arquivo.getPath()).toString();
        return texto.contains(feature.nome);
    }

    private int getNumeroClasses() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private double getProbabilidadeTermoClasse(Feature feature, Classe classe) {
        // Iterator it = feature.arquivoClasse.entrySet().iterator();
        int qtdeArquivos = 0;
        int qtdePorClasse = 0;
        //String c = "_";
        //while (it.hasNext()) {
        //  Map.Entry pairs = (Map.Entry) it.next();
        //c = (String) pairs.getKey();
        //if (c.equals(classe.getNome())) {
        //  qtdePorClasse += (int) pairs.getValue();
        //qtdeArquivos += (int) pairs.getValue();
        //} else {
        //   qtdeArquivos += (int) pairs.getValue();
        // }
        // it.remove(); // avoids a ConcurrentModificationException
        //}
        if (feature.arquivoClasse.containsKey(classe.getNome())) {
            qtdePorClasse = feature.arquivoClasse.get(classe.getNome());
        } else {
            return 0;
        }
        // if (qtdePorClasse == 0) {
        //   return 0;
        //}
        double prob = 0;
        try {
            prob = (double) qtdePorClasse / feature.getQtdeArquivos();
        } catch (ArithmeticException e) {
            // System.out.println(feature.nome + " " + c + " " + qtdePorClasse + " " + qtdeArquivos);
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

    public String getClasse(String arquivo) {
        for (Classe classe : classes) {
            for (Arquivo a : classe.getArquivos()) {
                if (a.getNome().equals(arquivo)) {
                    return classe.getNome();
                }
            }
        }
        throw new RuntimeException("\nClasse desse arquivo: " + arquivo + "  nao encontrada\n");
    }

    private void getBaseDadosInMemory(String diretorio) {
        //String diretorio = System.getProperty("user.dir") + "/" + baseDados;
        List<String> filePaths = Util.getFilesPath(new File(diretorio), 0);
        StringBuilder sb = new StringBuilder();
        for (String filePath : filePaths) {
            // System.out.print(". ");
            try (FileReader fr = new FileReader(filePath); BufferedReader br = new BufferedReader(fr)) {
                while (br.ready()) {
                    sb.append(br.readLine()).append("\n");
                }
                br.close();
                fr.close();
                arquivosBaseDados.put(filePath, sb);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(GainInformation.class
                        .getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(GainInformation.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        //System.exit(0);
        // printBaseDadosInMemory();
    }

    public void printBaseDadosInMemory() {
        Iterator it = arquivosBaseDados.entrySet().iterator();
        int qtdeArquivos = 0;
        int qtdePorClasse = 0;
        String c = "_";
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry) it.next();
            System.out.println((String) pairs.getKey());
            System.out.println((String) pairs.getValue());
            it.remove(); // avoids a ConcurrentModificationException
        }
        System.exit(0);
    }

    double getGanhoInformacaoFeature(Feature feature, List<Informacao> informacoes) {
        double probClasse = 0;
        double probTermo = 0;
        double probNaoTermo = 0;
        double probTermoClasse = 0;
        double probNaoTermoClasse = 0;
        double nrArquivosOcorrem = 0;
        double somatorio1 = 0;
        double somatorio2 = 0;
        double somatorio3 = 0;
        for (Informacao info : informacoes) {
            nrArquivosOcorrem += info.getCount().intValue();
        }
        int nrTotalArquivos = getNumeroTotalArquivos();
        probTermo = (double) nrArquivosOcorrem / nrTotalArquivos;
        probNaoTermo = 1 - probTermo;
        for (Classe classe : classes) {
            probClasse = (double) classe.getArquivos().size() / nrTotalArquivos;
            somatorio1 += probClasse * Math.log(probClasse);
            for (Informacao info : informacoes) {
                if (info.getNomeClasseOrigem().equals(classe.getNome())) {
                    probTermoClasse = (double) info.getCount().intValue() / nrArquivosOcorrem;
                    break;
                }
            }

            probNaoTermoClasse = 1 - probTermoClasse;
            somatorio2 += probTermoClasse == 0 ? 0 : probTermoClasse * Math.log(probTermoClasse);
            somatorio3 += probNaoTermoClasse == 0 ? 0 : probNaoTermoClasse * Math.log(probNaoTermoClasse);

        }

        return -somatorio1 + probTermo * somatorio2 + probNaoTermo * somatorio3;
    }

    double getGanhoInformacaoBanco(List<Informacao2> infos2) {
        double probClasse = 0;
        double probTermo = 0;
        double probNaoTermo = 0;
        double probTermoClasse = 0;
        double probNaoTermoClasse = 0;
        double nrArquivosOcorrem = 0;
        double somatorio1 = 0;
        double somatorio2 = 0;
        double somatorio3 = 0;
        int nrTotalArquivos = getNumeroTotalArquivos();
        int k = 0;
        for (Informacao2 atributo : infos2) {
            k++;
            System.out.print("\r" + (k * 100) / infos2.size() + " %");
            nrArquivosOcorrem = 0;
            somatorio1 = 0;
            somatorio2 = 0;
            somatorio3 = 0;

            for (BigDecimal bd : atributo.counts) {
                nrArquivosOcorrem += bd.intValue();
            }

            probTermo = (double) nrArquivosOcorrem / nrTotalArquivos;
            probNaoTermo = 1 - probTermo;
            for (int i = 0; i < classes.size(); i++) {
                probClasse = (double) classes.get(i).getArquivos().size() / nrTotalArquivos;
                somatorio1 += probClasse * Math.log(probClasse);
                probTermoClasse = (double) atributo.counts.get(i).doubleValue() / nrArquivosOcorrem;
                probNaoTermoClasse = 1 - probTermoClasse;
                somatorio2 += probTermoClasse == 0 ? 0 : probTermoClasse * Math.log(probTermoClasse);
                somatorio3 += probNaoTermoClasse == 0 ? 0 : probNaoTermoClasse * Math.log(probNaoTermoClasse);

            }
            atributo.setInfogain(-somatorio1 + probTermo * somatorio2 + probNaoTermo * somatorio3);
            //if (atributo.getNome().equals("affinity")) {
               int a = 0;
            //}
        }

        return -somatorio1 + probTermo * somatorio2 + probNaoTermo * somatorio3;
    }

}
