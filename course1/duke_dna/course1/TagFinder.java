package course1;

import edu.duke.*;
import java.io.*;

public class TagFinder {


  public int findStopIndex(String dna, int index) {
    int stop1 = dna.indexOf("tga", index);
    int stop2 = dna.indexOf("taa", index);
    int stop3 = dna.indexOf("tag", index);
    if(stop1 == -1 || (stop1 - index) % 3 != 0){
      stop1 = dna.length();
    }
    if(stop2 == -1 || (stop2 - index) % 3 != 0){
      stop2 = dna.length();
    }
    if(stop3 == -1 || (stop3 - index) % 3 != 0){
      stop3 = dna.length();
    }
    return Math.min(stop1, Math.min(stop2, stop3));
  }

  public double cgRatio(String dna){
    dna = dna.toLowerCase();
    int idx = 0;
    double count = 0.0;
    while(idx < dna.length()){
      String chr = dna.substring(idx, idx + 1);
      if(chr.indexOf("c") != -1 || chr.indexOf("g") != -1){
        count = count + 1.0;
      }
      idx = idx + 1;
    }
    return count/dna.length(); 
  }

  public int countCTG(String dna){
    dna = dna.toLowerCase();
    int count = 0;
    int idx = 0;
    while(true){
      idx  = dna.indexOf("ctg", idx); 
      if(idx == -1){
        break;
      }
      count = count + 1;
      idx = idx + 3;
    }
    return count;
  }

  public StorageResource storeAllGenes(String dna){
    dna = dna.toLowerCase();
    StorageResource store = new StorageResource();
    int startIdx = dna.indexOf("atg");
    int geneCnt = 0;
    while(startIdx != -1){
      int stopIdx = findStopIndex(dna, startIdx + 3);
      if(stopIdx != dna.length()){
        String newGene = dna.substring(startIdx, stopIdx + 3);
        store.add(newGene);
        if (geneCnt < 10) {
//            System.out.println("Gene #[" + geneCnt + "]: " + newGene);
        }
geneCnt++;
        startIdx = dna.indexOf("atg", stopIdx + 3);
      } else {
        startIdx = dna.indexOf("atg", startIdx + 3);
      }
    }
    return store;
  }

  public int longestGene(StorageResource sr){
    int longest = 0;
    for(String gene: sr.data()){
      if(gene.length() > longest){
        longest = gene.length();
      }
    }
    return longest;
  }


  public void printGenes(StorageResource sr){
    int count60 = 0;
    int countRat = 0;
    for(String gene : sr.data()){
      if(gene.length() > 60){
//        System.out.println(gene);
        count60 = count60 + 1;
      }
      if(cgRatio(gene) > 0.35){
//        System.out.println(gene);
        countRat = countRat + 1;
      }
    }
    System.out.println("Count of genes longer than 60:");
    System.out.println(count60);
    System.out.println("Count of genes with >0.35 CG ratio:");
    System.out.println(countRat);
  }


  public void testStorageFinder(){
    FileResource file = new FileResource("GRch38dnapart.fa");
    String line = file.asString();
    StorageResource store = storeAllGenes(line);
    System.out.println(">>>> TagFinder <<<<");
    System.out.println(">>>> TagFinder <<<<");

    printGenes(store);
    System.out.println("Longest gene length found:");
    System.out.println(longestGene(store));
    System.out.println("Count of genes found:");
    System.out.println(store.size());
    System.out.println("Count of ctg codon in str:");
    System.out.println(countCTG(line));
  }
  
  public static void main(String[] args) {

      TagFinder fuck = new TagFinder();
      fuck.testStorageFinder();

  }
}