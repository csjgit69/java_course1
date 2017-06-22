package course1;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.io.*;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import edu.duke.FileResource;
import edu.duke.DirectoryResource;
import edu.duke.StorageResource;

public class TotalBirths {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        TotalBirths testTB = new TotalBirths();
       
        //System.out.println("\n**** List DB:");
        //FileResource testFile = new FileResource();
        //testTB.filePrintNames(2012, "small");

        System.out.println("\n**** Total births:");
        testTB.totalBirths(1900, "large");
        testTB.totalBirths(2012, "small");
        System.out.println("\n**** Total births:");
        testTB.totalBirths(1905, "large");
        testTB.totalBirths(2012, "small");
/*

        System.out.println("\n**** Get Ranks:");
        testTB.getRankYear(1960, "Emily", "F", "large" );
        testTB.getRankYear(1971, "Frank", "M", "large" );
        testTB.getRankYear(1994, "Mich", "M", "large" );
        
        System.out.println("\n**** Get Names:");
        testTB.getName(1980, 350, "F", "large" );
        testTB.getName(1982, 450, "M", "large" );
        
        System.out.println("\n**** What's your name:");
        testTB.whatIsNameInYear("Susan", 1972, 2014, "F" );
        testTB.whatIsNameInYear("Owen", 1974, 2014, "M" );
*/                
        System.out.println("\n**** Highest Rank Year:");
        testTB.yearOfHighestRank("Genevieve", "F");
        testTB.yearOfHighestRank("Mich", "M");
        
/*    
        System.out.println("\n**** Average Rank:");
        testTB.getAverageRank("Susan", "F");
        testTB.getAverageRank("Robert", "M");
        
        System.out.println("\n**** Average Rank:");
        testTB.getTotalBirthsRankedHigher(1990, "Emily", "F");
        testTB.getTotalBirthsRankedHigher(1990, "Drew", "M");
*/                
        
    }

    public int getTotalBirthsRankedHigher (int year, String name, String gender, String fType) {
        boolean genderFound = false;
        boolean nameFound   = false;
        int     birthCnt    = 0;

        // get the file
        FileResource file   = getFile(year, fType);
        
        // Count all births of correct gender until the name is found
        for (CSVRecord record: file.getCSVParser(false)) {
            genderFound = record.get(1).equals(gender);
            // if in correct gender region count records until name found or all names counted.
            // set a flag is the name is found, also break out of the search (speed up function).
            if (genderFound) {
                if (record.get(0).equals(name)) {
                    nameFound = true;
                    break;
                }
                int births = Integer.parseInt(record.get(2));
                birthCnt   += births;
            }
        }
        if (nameFound) {
            System.out.println("Numer of births ranked greater than \"" +name+ "\", " +gender+ " is : " + birthCnt);
            return birthCnt;
        } else {
            System.out.println("Name \"" +name+ "\", " +gender+ " was not found");
            return -1;
        }
    }
    
    public double getAverageRank (String name, String gender) {
        TotalBirths testTB  = new TotalBirths();
        DirectoryResource  dirFiles = new DirectoryResource(); 
        double rankAvg  = -1.0;
        double fileCnt  = 0;
        int rank        = 0;
        double rankTotal   = 0;
        int year        = 0;
        
        for (File fName: dirFiles.selectedFiles()) {
            FileResource file = new FileResource(fName);            
            rank    = testTB.getRankFile(file, name, gender);
            fileCnt++;
            //System.out.println("rank : " +rank+ ", fileCnt : " +fileCnt );
            if (rank > 0) {
                rankTotal       += rank;
                String tempYear = fName.getName();
                year            = Integer.parseInt(tempYear.substring(3,7));
            }
        }
        if (rankTotal > 0) {
            rankAvg = rankTotal/fileCnt;
            System.out.println("\"" +name+ "\", "+gender+", born in " +year+ " average rank : " +rankAvg );
            System.out.println("rankTotal : " +rankTotal+ ", fileCnt : " +fileCnt );
            return rankAvg;
        } else {
            System.out.println("\"" +name+ "\", "+gender+", name not found, no rank" );
            return -1;            
        }
    }

    public int yearOfHighestRank (String name, String gender) {
        TotalBirths testTB  = new TotalBirths();
        DirectoryResource  dirFiles = new DirectoryResource(); 
        int bestRank    = -1;
        int rank        = -1;
        String year     = "";
        
        for (File fName: dirFiles.selectedFiles()) {
            FileResource file = new FileResource(fName);            
            rank    = testTB.getRankFile(file, name, gender);
            
            String ty = fName.getName();

            //System.out.println("\"" +name+ "\", "+gender+", born in " +ty.substring(3,7)+ " is rank : " +rank+ " bestRank : " +bestRank);
            if ((bestRank > rank && rank > 0) || bestRank == -1) {
                bestRank        = rank;
                String tempYear = fName.getName();
                year            = tempYear.substring(3,7);
//                System.out.println("\"" +name+ "\", "+gender+", born in " +ty.substring(3,7)+ " is rank : " +rank );
//                System.out.println("bestRank : " +bestRank );

            }

        }
/*
        System.out.println("OUT ***" );
        System.out.println("\"" +name+ "\", "+gender+", born in " +year+ " is rank : " +rank );
        System.out.println("bestRank : " +bestRank );
        if (bestRank > 0) {
            System.out.println("\t> 0 : " +bestRank );
        }
        bestRank++;
        System.out.println("bestRank : " +bestRank );
        if (bestRank > 0) {
            System.out.println("\t> 0 : " +bestRank );
        }
*/
        
        if (bestRank > 0) {
            System.out.println("\"" +name+ "\", "+gender+", born in " +Integer.parseInt(year)+ " is rank : " +bestRank );
            return Integer.parseInt(year);
        } else {
            System.out.println("\"" +name+ "\", "+gender+", name not found, no rank" );
            return -1;            
        }
    }
    
    public String whatIsNameInYear (String name, int birthYear, int newYear, String gender, String fType) {
        TotalBirths testTB  = new TotalBirths();
        String  newName     = "NO NAME";
        int     rank        = 0;

        rank    = testTB.getRankYear(birthYear, name, gender, fType );
        newName = testTB.getName(newYear, rank, gender, fType );
        
        System.out.println("\"" +name+ "\", born in " +birthYear+ " is rank : " +rank );
        System.out.println("\"" +name+ "\", born in " +birthYear+ " would be \"" +newName+ "\" in " +newYear);
        return newName;
    }
    
    public String getName (int year, int rank, String gender, String fType) {
        boolean genderFound = false;
        String  name        = "NO NAME";
        int     rankCnt     = 0;

        // get the file, sub-function
        FileResource file   = getFile(year, fType);
        
        // find the correct gender region then count records until the name matches
        // break from the loop if the name is found
        for (CSVRecord record: file.getCSVParser(false)) {
            genderFound = record.get(1).equals(gender);
            if (genderFound) {
                rankCnt++;
                if (rankCnt == rank) {
                    name = record.get(0);
                    break;
                }
            }
        }
        System.out.println("Rank # \"" +rank+ "\", " +gender+ " is : " + name);
        return name;
    }
    
    public int getRankFile (FileResource file, String name, String gender) {
        boolean genderFound = false;
        boolean nameFound   = false;
        int     rankCnt     = 0;

        // look for the name, count how many records before finding the name
        // don't start the count unless in the correct gender group
        for (CSVRecord record: file.getCSVParser(false)) {
            genderFound = record.get(1).equals(gender);
            // if in correct gender region count records until name found or all names counted.
            // set a flag is the name is found, also break out of the search (speed up function).
            if (genderFound) {
                rankCnt++;
//                System.out.println("Record Name : " +record.get(0)+ " equals name : " +record.get(0).equals(name) );
//                System.out.println("Name : " +name+ ", genderFound : " +genderFound+ ", rankCnt : " +rankCnt );
                if (record.get(0).equals(name)) {
                    nameFound = true;
                    break;
                }
            }
        }
        if (nameFound) {
            //System.out.println("Rank of \"" +name+ "\", " +gender+ " is : " + rankCnt);
            return rankCnt;
        } else {
            //System.out.println("Name \"" +name+ "\", " +gender+ " was not found");
            return -1;
        }
    }
    
    public int getRankYear (int year, String name, String gender, String fType) {        
        boolean genderFound = false;
        boolean nameFound   = false;
        int     rankCnt     = 0;

        // get the file
        FileResource file   = getFile(year, fType);
        
        // look for the name, count how many records before finding the name
        // don't start the count unless in the correct gender group
        for (CSVRecord record: file.getCSVParser(false)) {
            genderFound = record.get(1).equals(gender);
            // if in correct gender region count records until name found or all names counted.
            // set a flag is the name is found, also break out of the search (speed up function).
            if (genderFound) {
                rankCnt++;
                if (record.get(0).equals(name)) {
                    nameFound = true;
                    break;
                }
            }
        }
        if (nameFound) {
            System.out.println("Rank of \"" +name+ "\", " +gender+ " is : " + rankCnt);
            return rankCnt;
        } else {
            System.out.println("Name \"" +name+ "\", " +gender+ " was not found");
            return -1;
        }
    }
    
    public void totalBirths (int year, String fType) {
        // get the file
        FileResource file   = getFile(year, fType);

        int sumBirths   = 0;
        int numBoys     = 0;
        int sumBoys     = 0;
        int numGirls    = 0;
        int sumGirls    = 0; 
        for (CSVRecord record: file.getCSVParser(false)) {
            int births = Integer.parseInt(record.get(2));
            sumBirths   += births;
            if (record.get(1).equals("F")) {
                numGirls++;
                sumGirls += births;
            } else {
                numBoys++;
                sumBoys  += births;
            }
        }
        System.out.println("\nFor year : "+year+", total births : " + sumBirths);
        System.out.println("total girls  : " +numGirls+ ", total births : "+ sumGirls);
        System.out.println("total boys   : " +numBoys+ ", total births : "+ sumBoys);
    }

    public FileResource getFile (int year, String fType) {
        // get the file
        FileResource file;
        if (fType.equals("small")) {
            String fName    = "./us_babynames/us_babynames_test/yob" + year + "short.csv";
            file            = new FileResource(fName);
        } else if (fType.equals("large")) {
            String fName    = "./us_babynames/us_babynames_by_year/yob" + year + ".csv";
            file            = new FileResource(fName);
        } else {
            file            = new FileResource();            
        }
        return file;
    }
    
    public void filePrintNames (int year, String ftype) {
        // get the file
        FileResource file   = getFile(year, "ftype");

        for (CSVRecord record: file.getCSVParser(false)) {
            printName(record);
        }
    }

    public void printName (CSVRecord record) {
        System.out.println("Name : " + record.get(0) +
                           "\t Gender : " + record.get(1) +
                           "\t Number : " + record.get(2));
    }
 /*   
    public void testGetRank () {
        TotalBirths testTB = new TotalBirths();
        int nameRank    = 0;
        int year        = 0;
        String name     = "";
        String gender   = "";
        
        year     = 2012;
        name     = "Jacob";
        gender   = "F";
        nameRank = testTB.getRankYear(year, name, gender );
        if (nameRank > 0) {
            System.out.println("\nRank of \"" +name+ "\", " +gender+ " is : " + nameRank);
        } else {
            System.out.println("\nName \"" +name+ "\", " +gender+ " was not found");
        }

        year     = 2012;
        name     = "Mason";
        gender   = "M";
        nameRank = testTB.getRankYear(year, name, gender );
        if (nameRank > 0) {
            System.out.println("\nRank of \"" +name+ "\", " +gender+ " is : " + nameRank);
        } else {
            System.out.println("\nName \"" +name+ "\", " +gender+ " was not found");
        }
    }
*/
    
}