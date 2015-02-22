/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cofeature;

import br.edu.ufabc.util.Util;
import static br.edu.ufabc.util.Util.printFile;
import facade.CoFeatureFacade;
import facade.FeatureFacade;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.swing.SizeRequirements;

/**
 *
 * @author charleshenriqueportoferreira
 */
@Entity
public class CoFeature implements Serializable {
    
    String nome;
    String nomeArquivoOrigem;
    String nomeClasseOrigem;
    double dominancia;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
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
    
    public double getDominancia() {
        return dominancia;
    }
    
    public void setDominancia(double dominancia) {
        this.dominancia = dominancia;
    }
    
    public CoFeature() {
    }
    
    public CoFeature(String nome, String arquivo, String classe) {
        this.nome = nome;
        this.nomeArquivoOrigem = arquivo;
        this.nomeClasseOrigem = classe;
    }
    @Transient
    public List<Feature> coFeaturesTemporario = new ArrayList<>();
    @Transient
    List<String> dadosUsados = new ArrayList<>();
    @Transient
    Set<String> grams = new HashSet<>();
    
    public static void main(String[] args) {
        String baseDados = "C50_Maid";
        CoFeature cf = new CoFeature();
        String diretorio = System.getProperty("user.dir") + "/" + baseDados;
        GainInformation gi = new GainInformation(diretorio);
        List<String> textos = Util.getFilesPath(new File(diretorio), 0);
        List<Feature> features = new ArrayList<>();
        System.out.println("Criando as Features");
        //create the features
        //for (String texto : textos) {
        //  features.addAll(cf.getFeatures(texto, gi));
        //}
        System.out.println("Lendo Dados dp banco...\n\n");
        FeatureFacade ff = new FeatureFacade();
        //System.out.println("Salvando...");
        //features = ff.findAll();
        //int k = 0;
        //for (Feature feature : features) {
        //  k++;
        //System.out.print("\r" + (k * 100) / features.size() + " %");
        //System.out.print("\n\r" + k + " de " + features.size());

        List<String> nomeClasses = new ArrayList<>();
        for (Classe classe : gi.getClasses()) {
            nomeClasses.add(classe.getNome());
        }
//        System.out.println("\nCalculando o ganho de informacao...\n");
//        List<Informacao2> informacoes2 = ff.findAllSQLTeste2(nomeClasses);
//        gi.getGanhoInformacaoBanco(informacoes2);
//
//        System.out.println("\nAtualizando os dados das features para salvar no banco\n");
//        int p = 0;
//        Feature featureLocal = new Feature();
//        Iterator<Feature> i = features.iterator();
//        int l = 0;
//        for (Informacao2 info : informacoes2) {
//            l++;
//            System.out.print("\r" + l + " de " + informacoes2.size());
//            if (featureLocal.getNome() != null && featureLocal.getNome().equals(info.getNome())) {
//                featureLocal.setInfoGain(info.getInfogain());
//            }
//            while (i.hasNext()) {
//                if (p == 1057) {
//                    int a = 0;
//                }
//                p++;
//                Feature s = i.next(); // must be called before you can call i.remove()
//                // Do something
//                if (s.getNome().equals(info.getNome())) {
//                    s.setInfoGain(info.getInfogain());
//                } else {
//                    featureLocal = s;
//                    break;
//                }
//                //i.remove();
//            }
//        }
//        int k = 0;
//        System.out.println("\nSalvando no banco de dados ...\n");
//
//        for (Feature f : features) {
//            k++;
//            System.out.print("\r" + (k * 100) / features.size() + " %");
//            ff.edit(f);
//        }
//        System.out.println("\nSalvando 2...");
//        k = 0;
//        for (Informacao2 info : informacoes2) {
//            k++;
//            System.out.print("\r" + (k * 100) / informacoes2.size() + " %");
//            int a = 0;
//            ff.specialEdit(info.getNome(), info.getInfogain());
//            // System.exit(0);
//        }
// feature.setInfoGain(gi.getGanhoInformacaoFeature(feature, informacoes));
        //if (k == 500) {
        //  System.exit(0);
        //}
        //}
        // System.exit(0);
//        for (Feature feature : features) {
//            ff.edit(feature);
//        }
//        for (Informacao dado : dados) {
//            System.out.println(dado);
//        }
        // List<Feature> dados = ff.findAllSQLTeste();
        //for (Feature dado : dados) {
        //  System.out.println(dado.toString());
        //}
        //System.out.println(features.size());
        // System.exit(0);
        //System.out.println(features.size());
        // System.out.println("\nCalculando o gain de informacao para cada feature");
        //set the information gain to each features created
//        int i = 0;
//        double min = 10000;
//        double max = -10000;
//        for (Feature feature : features) {
//            i++;
//            System.out.print("\r" + (i * 100) / features.size() + " %");
//            double gain = gi.getGanhoInformacao(feature, features);
//            min = gain < min ? gain : min;
//            max = gain > max ? gain : max;
//            feature.setInfoGain(gain);
//        }
//        try {
//            cf.saveFile(baseDados + ".txt", features, min, max);
//        } catch (IOException ex) {
//            Logger.getLogger(CoFeature.class.getName()).log(Level.SEVERE, null, ex);
//        }
        // System.out.println(features.toString());
//        System.exit(0);
//        System.out.println("\nEliminando as features que possuem ganho de informacao menor que 0.5");
        //List<Feature> featuresSelecionadas = ff.findAll(3.9);
        System.out.println("\nCriando as cofeatures");
        //create the cofeatures

        //List<Feature> coFeatures = cf.generateCoFeature(null, baseDados, gi);
        //System.exit(0);
        //coFeatures.clear();
        CoFeatureFacade cff = new CoFeatureFacade();
//        System.out.println("Lendo todas as cofeatures");
//        List<CoFeature> cofeatures = cff.findAll();
//        System.out.println("lendo a contagem de classes das cofeatures");
//        List<Informacao2> info2 = cff.findAllSQLTeste2(nomeClasses);
//        System.out.println("dados carregados");
//        CoFeature cofeatureLocal = new CoFeature();
//        Iterator<CoFeature> i = cofeatures.iterator();
//        int l = 0;
//        for (Informacao2 info : info2) {
//            l++;
//            System.out.print("\r" + l + " de " + info2.size());
//            if (cofeatureLocal.getNome() != null && cofeatureLocal.getNome().equals(info.getNome())) {
//                cofeatureLocal.setDominancia(cf.calculaDominancia(info, nomeClasses, cofeatureLocal.getNomeClasseOrigem()));
//            }
//            while (i.hasNext()) {
//                
//                CoFeature s = i.next(); // must be called before you can call i.remove()
//                // Do something
//                if (s.getNome().equals(info.getNome())) {
//                    s.setDominancia(cf.calculaDominancia(info, nomeClasses, s.getNomeClasseOrigem()));
//                } else {
//                    cofeatureLocal = s;
//                    break;
//                }
//                //i.remove();
//            }
//        }
//        System.out.println("Salvando no banco de dados");
//        for (CoFeature cofeature : cofeatures) {
//            cff.edit(cofeature);
//        }

//        List<CoFeature> cofs = cff.findAll();
//        int k = 0;
//        for (CoFeature cof : cofs) {
//            k++;
//            System.out.print("\r" + (k * 100) / cofs.size() + " %");
//            List<Informacao> infos = cff.findAllSQLTeste(cof.getNome());
//            cof.setDominancia(cf.calculaDominancia(cof, infos));
//            cff.edit(cof);
//
//            if (cof.getDominancia() < 1) {
//                System.out.println(cof.toString());
//                //System.exit(0);
//
//            }
//        }
//        System.out.println("\nGerando o arquivo NGram.txt");
//       // System.out.println(cofeaturesSelecionadas.toString());
//        //cf.createNgram(gi.getClasses(), cofeaturesSelecionadas, baseDados);
//        int i = 0;
//        for (Classe classe : gi.getClasses()) {
//            for (Arquivo arquivo : classe.getArquivos()) {
//                i++;
//                System.out.println("\r" + i + " de " + gi.getClasses().size() * classe.getArquivos().size());
//                List<CoFeature> cofeatures = cff.findAll(1, classe.getNome(), arquivo.getNome());
//                cf.createNgram(classe.getNome(), cofeatures, baseDados, arquivo.getNome());
//                //System.exit(0);
//            }
//        }
        try {
            cf.procuraNrOcorrencias("2Gram.txt", 2500);
        } catch (IOException ex) {
            Logger.getLogger(CoFeature.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.exit(0);
    }
    
    private List<Feature> getFeatures(String filePath, GainInformation gi) {
        List<Feature> features = new ArrayList<>();
        String pattern = "[a-zA-Z]+";
        String[] palavras;
        grams.clear();
        try (FileReader fr = new FileReader(filePath); BufferedReader br = new BufferedReader(fr)) {
            while (br.ready()) {
                palavras = br.readLine().split(" ");
                for (String palavra : palavras) {
                    palavra = palavra.replaceAll("\\.", "");
                    if (palavra.matches(pattern)) {
                        grams.add(palavra);
                    }
                }
            }
            for (String gram : grams) {
                Feature f = new Feature(gram, filePath);
                gi.updateClasseArquivoOrigem(f);
                features.add(f);
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
    
    public List<Feature> generateCoFeature(List<Feature> features, String baseDados, GainInformation gi) {
        CoFeatureFacade cff = new CoFeatureFacade();
        FeatureFacade ff = new FeatureFacade();
        String diretorio = System.getProperty("user.dir") + "/" + baseDados;
        List<String> filePaths = Util.getFilesPath(new File(diretorio), 0);
        List<Feature> cofeaturesGeral = new ArrayList<>();
        List<Feature> featuresDoArquivo = new ArrayList<>();
        List<Feature> f;
        //procura as features referentes ao arquivo
        for (Classe classe : gi.getClasses()) {
            f = ff.findAll(3.9, classe.getNome());
            System.out.println(classe.getNome() + "  " + f.size());
            //System.exit(0);
            for (Arquivo arquivo : classe.getArquivos()) {
                for (Feature f1 : f) {
                    if (f1.getNomeArquivoOrigem().equals(arquivo.getNome())) {
                        featuresDoArquivo.add(f1);
                    }
                }
                //cria as cofeature a partir das features selecionadas
                combineUnigram(featuresDoArquivo, arquivo.getPath(), baseDados, cff);
                featuresDoArquivo.clear();
                //System.exit(0);
            }
            //System.exit(0);
        }
        System.exit(0);
        return cofeaturesGeral;
    }
    
    private List<Feature> combineUnigram(List<Feature> features, String filePath, String baseDados, CoFeatureFacade cff) {
        String nomeClasse = "";
        String nomeArquivo = "";
        if (features.size() > 0) {
            nomeClasse = features.get(0).getNomeClasseOrigem();
            nomeArquivo = features.get(0).getNomeArquivoOrigem();
        }
        for (Feature feature : features) {
            for (Feature feature1 : features) {
                //if (feature.getNome().equals(feature1.getNome())) {
                //  continue;
                //}
                String cf = feature.getNome() + "_" + feature1.getNome() + ":1";
                CoFeature c = new CoFeature();
                c.nome = cf;
                c.nomeArquivoOrigem = nomeArquivo;
                c.nomeClasseOrigem = nomeClasse;
                cff.save(c);
//                System.exit(0);
            }
        }
        return null;
    }
    
    private double calculaDominancia(CoFeature cf, List<Informacao> infos) {
        double nrDocClasse = 0.0;
        double nrDocTotal = 0.0;
        
        for (Informacao info : infos) {
            nrDocTotal += info.getCount().intValue();
            if (cf.nomeClasseOrigem.equals(info.getNomeClasseOrigem())) {
                nrDocClasse = info.getCount().intValue();
            }
        }
        
        double domin = nrDocClasse / nrDocTotal;
        return domin;
    }
    
    private double calculaDominancia(Informacao2 info, List<String> nomeClasses, String nomeClasseOrigem) {
        double nrTextosTotalClasses = 0;
        double nrTextosClasseOrigem = 0;
        for (int i = 0; i < info.counts.size(); i++) {
            if (nomeClasses.get(i).equals(nomeClasseOrigem)) {
                nrTextosClasseOrigem += info.counts.get(i).doubleValue();
                nrTextosTotalClasses += info.counts.get(i).doubleValue();
            } else {
                nrTextosTotalClasses += info.counts.get(i).doubleValue();
            }
        }
        return nrTextosClasseOrigem / nrTextosTotalClasses;
    }
    
    public void createNgram(String classe, List<CoFeature> cofeatures, String baseDados, String arquivo) {
        StringBuilder sb = new StringBuilder();
        
        sb.append(baseDados).append("/").append(classe).append("/").append(arquivo);
        for (CoFeature cofeature : cofeatures) {
            
            sb.append("\n").append("       ").append(cofeature.getNome());
            
        }
        
        try {
            Util.printFile("2Gram.txt", sb.toString());
            // System.out.println(sb.toString());
        } catch (IOException ex) {
            Logger.getLogger(CoFeature.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void saveFile(String fileName, List<Feature> features, double min, double max) throws IOException {
        try (FileWriter fw = new FileWriter(fileName, true); BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(min + ":" + max);
            bw.newLine();
            for (Feature feature : features) {
                bw.write(feature.toString());
                bw.newLine();
            }
            bw.close();
            fw.close();
        }
    }
    
    public Long getID() {
        return ID;
    }
    
    public void setID(Long ID) {
        this.ID = ID;
    }
    
    @Override
    public String toString() {
        return "CoFeature{" + "nome=" + nome + ", nomeArquivoOrigem=" + nomeArquivoOrigem + ", nomeClasseOrigem=" + nomeClasseOrigem + ", dominancia=" + dominancia + '}';
    }
    
    public String procuraNrOcorrencias(String filePath, int nrTextos) throws FileNotFoundException, IOException {
        Map<String, NGram> hashBigram = new HashMap<>();
        String bg;
        int valor;
        String ng = "";
        StringBuilder sb = new StringBuilder();
        try (FileReader fr = new FileReader(filePath); BufferedReader br = new BufferedReader(fr)) {
            while (br.ready()) {
                ng = br.readLine();
                if (!ng.contains(".txt") && !ng.contains("ROOT") && !ng.equals("") && ng.length() > 2 && ng.contains(":")) {
                    bg = ng.split(":")[0];
                    bg = bg.replace("       ", "");
                    valor = Integer.parseInt(ng.split(":")[1]);
                    if (hashBigram.containsKey(bg)) {
                        NGram ngr = hashBigram.get(bg);
                        ngr.valor += valor;
                        ngr.qtdeTextos++;
                        hashBigram.put(bg, ngr);
                    } else {
                        hashBigram.put(bg, new NGram(valor, 1));
                    }
                }
            }
            br.close();
            fr.close();
            Iterator it = hashBigram.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pairs = (Map.Entry) it.next();
                // System.out.println(pairs.getKey() + " = " + pairs.getValue());
                NGram n = (NGram) pairs.getValue();
                sb.append(n.valor);
                sb.append(":(");
                sb.append(n.qtdeTextos);
                sb.append("/");
                sb.append(nrTextos);
                sb.append("):");
                sb.append(pairs.getKey());
                sb.append("\n");
                it.remove(); // avoids a ConcurrentModificationException

            }
            // System.out.println(sb.toString());
            String a = sb.toString();
            a = a.substring(0, a.length() - 1);
            printFile("2Gram.all", a);
        }
        
        return null;
    }
    
}
