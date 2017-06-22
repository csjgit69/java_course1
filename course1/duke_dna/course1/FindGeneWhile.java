package course1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.duke.FileResource;
import edu.duke.StorageResource;

public class FindGeneWhile {
    // *********    
    public int findStopCondon(String dna, String stopCodon, int startIdx) {
        //int result = dna.length();
        int result = -1;
        int crntIdx   = startIdx;
        boolean found = false;
        
        while (!found && crntIdx != -1){
            crntIdx  = crntIdx+3;
            crntIdx  = dna.indexOf(stopCodon, crntIdx);
            found    = ((startIdx - crntIdx)%3 == 0) && (crntIdx != -1);
            //System.out.println("Condon: "+ stopCodon + " startI: " + startIdx + " crntI: " + crntIdx + " is valid: " + found);
        }
        //System.out.println("startI: " + startIdx + " endI: " + endIdx + " is valid: " + validG);
        if (found) {
            result  = crntIdx;
        } 
        return result;
    }

    // *********    
    public String findNextGene(String dna, int dnaStart) {
        int startIdx  = dna.indexOf("ATG", dnaStart);
        int taaIdx = 0;
        int tagIdx = 0;
        int tgaIdx = 0;
        int endIdx = -1;

        List<Integer> endsPoints = new ArrayList<>();

        taaIdx = findStopCondon(dna, "TAA", startIdx);
        if (taaIdx != -1) {
            endsPoints.add(taaIdx);
        }
        tagIdx = findStopCondon(dna, "TAG", startIdx);
        if (tagIdx != -1) {
            endsPoints.add(tagIdx);
        }
        tgaIdx = findStopCondon(dna, "TGA", startIdx);
        if (tgaIdx != -1) {
            endsPoints.add(tgaIdx);
        }
        
        //System.out.println("findNextGene startIdx[" + startIdx + "], TAAIdx["+taaIdx+"], TAGIdx["+tagIdx+"], TGAIdx["+tgaIdx+"]");
        //System.out.println("findNextGene startIdx[" + startIdx + "], endIdx+3["+(endIdx+3)+"]");
        
/*        if ((taaIdx == -1) || (tagIdx < taaIdx && tagIdx != -1)){
                endIdx = tagIdx;
        } else {
            endIdx = taaIdx;
        }
        if ((endIdx == -1) || (tgaIdx < endIdx && tgaIdx != -1)){
            endIdx = tgaIdx;
        } 
*/      if (startIdx == -1 || endsPoints.isEmpty()) {
            return "";
        }
        endIdx = Collections.min(endsPoints);  
        if (startIdx == -1 || endIdx == -1) {
            return "";
        }
        return dna.substring(startIdx, endIdx+3);
    }

    // *********    
    public int findHowMany(String sString, String mString) {
        int crntIdx     = sString.indexOf(mString);
        int mCnt        = 0;
               
        while (crntIdx != -1) {
            mCnt++;
            //if (!crntGene.isEmpty() && crntGene != "") {
            //geneCnt++;
            //System.out.println("Gene #[" + geneCnt + "] : " + crntGene);
            //}
            crntIdx = sString.indexOf(mString, crntIdx + mString.length());
        }
        return mCnt;
    }

    // *********     
    public float cgRatio (String gene) {
        int cCnt        = findHowMany(gene, "C");
        int gCnt        = findHowMany(gene, "G");
        //System.out.println("cgRation Gene: " + gene + " cCnt: " + cCnt + " gCnt" + gCnt +" geneLen: " + gene.length());
        
        return (float) (cCnt+gCnt)/gene.length();
    }
    
    // *********     
    public void printGenes (StorageResource genes) {
        int geneCnt     = 0;
                   
        for (String gString: genes.data()) {
            geneCnt++;
            System.out.println("Gene #[" + geneCnt + "] : " + gString);
            //System.out.println("Gene CG ration is: " + cgRatio(gString));
            //System.out.println("Gene CGT count is: " + findHowMany(gString, "CTG"));
        }
    }
        
    // *********       
  /* public void findGeneWhile(String dna) {
       int crntIdx      = 0;
       String crntGene  = "a";
       int geneCnt      = 0;
       
       while (!dna.isEmpty() && crntGene != "" && crntIdx != -1) {
           crntGene = findNextGene(dna, crntIdx);
           if (!crntGene.isEmpty() && crntGene != "") {
               //System.out.println("Gene Found #[" + geneCnt + "] : " + crntGene);
               geneCnt++;
               System.out.println("Gene Found #[" + geneCnt + "]");
           }
           crntIdx = dna.indexOf(crntGene, crntIdx + crntGene.length());
       }
       if (geneCnt == 0) {
           System.out.println("Gene #[] : No Genes found");           
       }
   }
*/
   public void processGenes(StorageResource sr, int lLen, float hcg){
       // print all the Strings in sr that are longer than 9 characters
       // print the number of Strings in sr that are longer than 9 characters
       // print the Strings in sr whose C-G-ratio is higher than 0.35
       // print the number of strings in sr whose C-G-ratio is higher than 0.35
       // print the length of the longest gene in sr
       int gCnt     = 0;
       int longCnt  = 0;
       int cgCnt    = 0;
       int longest  = 0;
       String lGene = "";
       
       //System.out.println("");
       //System.out.println("**********************************");
       for (String s: sr.data()) {
               gCnt++;
       }
       System.out.println("Total Gene count\t\t: " + gCnt);
       //System.out.println("");
       //System.out.println("**********************************");
       for (String s: sr.data()) {
           if (s.length() > lLen) {
               //System.out.println("Gene > " +lLen+ " #[" + longCnt + "]: " + s);
               //System.out.println("Gene > " +lLen+ " #[" + longCnt + "]");
               longCnt++;
           }
       }
       System.out.println("Long Gene count\t\t\t: " + longCnt);
       //System.out.println("");
       //System.out.println("**********************************");
       for (String s: sr.data()) {
           if (cgRatio(s) > hcg) {
               //System.out.println("Gene C-G-Ratio > " +hcg+ " #[" + cgCnt + "] " + cgRatio(s) + " : " + s);
               //System.out.println("Gene C-G-Ratio > " +hcg+ " #[" + cgCnt + "] " + cgRatio(s));
               cgCnt++;               
           }
       }
       System.out.println("Total Large C-G-Ratio count\t: " + cgCnt);
       //System.out.println("");
       //System.out.println("**********************************");
       for (String s: sr.data()) {
           if (s.length() > longest) {
               longest  = s.length();
               lGene    = s;
           }
       }
       //System.out.println("Largest Gene Len [" +longest+ "] :" + lGene);
       System.out.println("Largest Gene Len\t\t: " +longest);
       //System.out.println("");
       //System.out.println("**********************************");

   }


   // *********     
   public StorageResource getAllGenes(String dna) {
       int crntIdx     = 0;
       int geneCnt     = 0;
       StorageResource geneDB = new StorageResource(); 
       String crntGene = "";
       
//       System.out.println("Gene Found Start#[" + geneCnt + "]");
                  
//       while (crntIdx != -1 && !crntGene.isEmpty() && crntGene != "") {
       while (true) {
           //System.out.println("");
           //System.out.println("");
           //System.out.println("getAll gene#[" + geneCnt + "] : " + geneCnt);
           //System.out.println("getAll crntIdx[" + crntIdx + "], lenght ["+crntGene.length()+"]");
           crntGene = findNextGene(dna, crntIdx);
           //System.out.println("getAll gene backG[" + crntGene + "],gene idx["+dna.indexOf(crntGene, crntIdx)+"], gene len["+crntGene.length()+"]");
           //System.out.println("");
           
           if (!crntGene.isEmpty() && crntGene != "") {
               geneDB.add(crntGene);
               geneCnt++;
               crntIdx = dna.indexOf(crntGene, crntIdx) + crntGene.length();
               if (geneCnt < 10) {
                   //System.out.println("GeneS#[" + crntIdx + "]: " + "GeneE#[" + crntIdx + crntGene.length() + "]");
                   //System.out.println("Gene #[" + geneCnt + "]: " + crntGene);
               }

           } else {
               break;
           }
//           crntIdx = dna.indexOf(crntGene, crntIdx + crntGene.length());
       }
      //System.out.println("getAllGenes last Index #[" + crntIdx + "]");
      //System.out.println("last substring : " + dna.substring(crntIdx) );
      return geneDB;
   }
   
   public String mystery(String dna) {
       int pos = dna.indexOf("T");
       int count = 0;
       int startPos = 0;
       String newDna = "";
       if (pos == -1) {
         return dna;
       }
       while (count < 3) {
         count += 1;
         newDna = newDna + dna.substring(startPos,pos);
         startPos = pos+1;
         pos = dna.indexOf("T", startPos);
         if (pos == -1) {
           break;
         }
       }
       newDna = newDna + dna.substring(startPos);
       return newDna;
     }   

   public void testDna(String dna, String prnt, String gPrnt) {
       StorageResource genes = new StorageResource();
       System.out.println("");
       if (prnt == "p") {
           System.out.println("DNA strand is:  " + dna);
       }
       genes = getAllGenes(dna);
System.out.println("gene array size\t\t\t: " + genes.size());       
       processGenes(genes, 60, (float).35);
       System.out.println("CTG #\t\t\t\t: " + findHowMany(dna,"CTG"));
       System.out.println("***********************");
       
       if (gPrnt == "p") {
           printGenes(genes);
       }
       //System.out.println("Gene is:        " + gene);
     }   

   
   // *********
   public void testFindGeneWhile() {
       //FileResource fr = new FileResource("brca1line.fa");
       FileResource fr = new FileResource("GRch38dnapart.fa");
       String dna;
       
       dna = "TAATAAATGTGAATGTAAATGTAGATG";
       testDna(dna,"p","p");
       dna = "TAATAA ATGTAATAGTGA ATGTAGTGATAA ATGTGATAATAG";
       testDna(dna,"p","p");
       dna = "TAATAA  ATGTAAGTTAA  ATGTAGGTTAA  ATGTGAGTTAA";
       testDna(dna,"p","p");
       dna = "ATGAG GTAGAA";
       testDna(dna,"p","p");
       dna = "ATGTATGTAA";//GCGTAAATGTAGATGTAGATGAAATGATGTAA";//GGCGTAGTTAGTGCGTAG";//CGATGGCGGGGTAATTAGTCGATGGCGGGGTAATTAGTCGATGGCGGGGTAATTAGTCGATGGCGGGGTAATTAGTCG";
       //testDna(dna,"p");

       dna = fr.asString();
       dna = dna.toUpperCase();
       testDna(dna,"n","n");       
       
       fr = new FileResource("brca1line.fa");
       dna = fr.asString();
       dna = dna.toUpperCase();
       testDna(dna,"n","n");       

    }
   
   // *********   
   public static void main(String[] args) {
       FindGeneWhile test = new FindGeneWhile();
       test.testFindGeneWhile();
        
   }    
}
