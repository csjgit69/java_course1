package course1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.io.*;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import edu.duke.FileResource;
import edu.duke.StorageResource;

public class CountriesExport {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        CountriesExport testCSV = new CountriesExport();
        
        testCSV.tester();

    }

    //*********
    public void tester() {
        // TODO Auto-generated method stub
        CountriesExport testCSV = new CountriesExport();
        String dbVal1           = "";
        String dbVal2           = "";
        
        FileResource iFile   = new FileResource("exportdata.csv");
//        FileResource iFile   = new FileResource("exports_small.csv");
        CSVParser    cParser = iFile.getCSVParser();
        //System.out.println("1 Parser line #"+cParser.getCurrentLineNumber());
/*        
        dbVal1  = "Nauru";
        System.out.println("Country Stats for: " + dbVal1);       
        testCSV.countryInfo(cParser, dbVal1);
        //System.out.println("2 Parser line #"+cParser.getCurrentLineNumber());
       
        cParser = iFile.getCSVParser();
        dbVal1  = "United States";
        System.out.println("Countries exporting: " + dbVal1);       
        testCSV.countryInfo(cParser, dbVal1);
       
        cParser = iFile.getCSVParser();
        dbVal1  = "coffee";
        System.out.println("Countries exporting: " + dbVal1);       
        testCSV.listExporters(cParser, dbVal1);

        cParser = iFile.getCSVParser();
        dbVal1  = "cotton";
        dbVal2  = "flowers";
        System.out.println("Countries exporting two things: " + dbVal1 +", "+ dbVal2);
        testCSV.listExportersTwoProducts(cParser, dbVal1, dbVal2);
        
        cParser = iFile.getCSVParser();
        dbVal1  = "cocoa";
        System.out.println("Number of countries exporting : " + dbVal1);
        testCSV.numberOfExporters(cParser, dbVal1);
*/       
        cParser = iFile.getCSVParser();
        dbVal1  = "$999,999,999,999";
        System.out.println("Number of countries exporting more than : " + dbVal1);
        testCSV.bigExporters(cParser, dbVal1);
     
        
    }
    
    //*********
    public String countryInfo(CSVParser parser, String country) {        
        String countryDB    = country+":";
        String exports      = "Exports";
        String values       = "Value (dollars)";
        CSVRecord pRecord;
        
        for (CSVRecord record: parser) {
            //System.out.println(record);

            String cInfo = record.get("Country");
            //System.out.println("Record :" + record);
            
            pRecord = record;
            System.out.println("Record :" + pRecord);
            
            
/*            
            if (record.get("Country").equals(country)) {
                System.out.println("*** SHIT this works");
            }
            
            if(record.get("Country") == country){
                System.out.println("*** SHIT this works 2222");
            }
*/
            
            if (cInfo.contains(country)){
                exports = record.get("Exports");
                values   = record.get("Value (dollars)");
                //System.out.println(cInfo);
            }
        }
        countryDB = countryDB + exports + ":" + values;
        System.out.println(countryDB);
        return countryDB;
    }
    
    //*********
    public void listExporters(CSVParser parser, String exportOfInterest) {        
        for (CSVRecord record: parser) {
            String export = record.get("Exports");
            if (export.contains(exportOfInterest)){
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }

    //*********
    public void listExportersTwoProducts(CSVParser parser, String exportVal1, String exportVal2) {        
        for (CSVRecord record: parser) {
            String exports = record.get("Exports");
            
            if (exports.contains(exportVal1) && exports.contains(exportVal2)){
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }
    
    //*********
    public int numberOfExporters(CSVParser parser, String exportVal1) {        
        int exporterCnt = 0;
        
        for (CSVRecord record: parser) {
            String exports = record.get("Exports");
            
            if (exports.contains(exportVal1)){
                exporterCnt++;
            }
        }
        System.out.println("Countries exporting: " + exportVal1 + " : " +exporterCnt);
        return exporterCnt;
    }
    
    //*********
    public void bigExporters(CSVParser parser, String dollarVal1) {        
        int exporterCnt = 0;
        
        for (CSVRecord record: parser) {
            String dollars = record.get("Value (dollars)");
            
            if (dollars.length() > dollarVal1.length()){
                String country = record.get("Country");
                System.out.println(record.get("Country") + " " + dollars);
            }
        }
    }
}
