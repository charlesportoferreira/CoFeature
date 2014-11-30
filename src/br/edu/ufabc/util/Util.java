/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufabc.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author charleshenriqueportoferreira
 */
public class Util {

    public static List<String> filePaths = new ArrayList<>();
    public static List<String> fileNames = new ArrayList<>();
 
    public static List<String> getFilesPath(File initialPath, int initialDepth) {

        filePaths = new ArrayList<>();
        List<String> filesPath = fileTreePrinter(initialPath, initialDepth);
        return filesPath;

    }

    public static List<String> fileTreePrinter(File initialPath, int initialDepth) {

        int depth = initialDepth++;
        if (initialPath.exists()) {
            File[] contents = initialPath.listFiles();
            for (File content : contents) {
                if (content.isDirectory()) {
                    fileTreePrinter(content, initialDepth + 1);
                } else {
                    char[] dpt = new char[initialDepth];
                    for (int j = 0; j < initialDepth; j++) {
                        dpt[j] = '+';
                    }
                    if (content.getName().contains(".txt")) {
                        filePaths.add(content.toString());
                    }
                }
            }
        }
        return filePaths;
    }
    
    
    
    public static List<String> fileTreeName(File initialPath, int initialDepth) {

        int depth = initialDepth++;
        if (initialPath.exists()) {
            File[] contents = initialPath.listFiles();
            for (File content : contents) {
                if (content.isDirectory()) {
                    fileTreeName(content, initialDepth + 1);
                } else {
                    char[] dpt = new char[initialDepth];
                    for (int j = 0; j < initialDepth; j++) {
                        dpt[j] = '+';
                    }
                    if (content.getName().contains(".txt")) {
                        System.out.println(content.getName());
                        fileNames.add(content.getName());
                    }
                }
            }
        }
        return fileNames;
    }
    

    public static String lerArquivo(String filePath) throws FileNotFoundException, IOException {
        StringBuilder linha = new StringBuilder();
        try (FileReader fr = new FileReader(filePath); BufferedReader br = new BufferedReader(fr)) {
            while (br.ready()) {
                linha.append(br.readLine());
                linha.append("\n");
            }
            br.close();
            fr.close();
        }
        return linha.toString();
    }

    public static String insertStopListTag(Set<String> dados) {
        StringBuilder stopList = new StringBuilder();
        stopList.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>").append("\n").append("<stopfile>").append("\n");

        for (String dado : dados) {
            dado = dado.replaceAll("[^a-zA-Z]", "");
            if (!dado.matches("")) {
                stopList.append("\t<stopword>").append(dado).append("</stopword>").append("\n");
            }

        }
        stopList.append("</stopfile>").append("\n");
        return stopList.toString();
    }

    public static void printFile(String fileName, String texto) throws IOException {
        try (FileWriter fw = new FileWriter(fileName); BufferedWriter bw = new BufferedWriter(fw)) {

            bw.write(texto);

            bw.newLine();

            bw.close();
            fw.close();
        }
    }

}
