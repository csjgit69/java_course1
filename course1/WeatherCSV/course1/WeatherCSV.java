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

/*
Reader in = new StringReader("a,b,c");
for (CSVRecord record : CSVFormat.DEFAULT.parse(in)) {
    for (String field : record) {
        System.out.print("\"" + field + "\", ");
    }
    System.out.println();
}
*/

public class WeatherCSV {
    
    //*************
    public static void main(String[] args) {
        WeatherCSV test = new WeatherCSV();
        
        //test.testColdestHourInFile();
        //test.testFileWithColdestTemperature();
        //test.testLowestHumidityInFile("Humidity");
        //test.testLowestHumidityInFile("TemperatureF");
        test.testLowestHumidityInManyFiles("TemperatureF");
        //test.testLowestHumidityInManyFiles("Humidity");
        //test.testAverageTemperatureInFile("TemperatureF");
        //test.testAverageTemperatureWithHighHumidityInFile("Humidity", 80.0);
    }
    
    // #1 *************
    public void testColdestHourInFile() {
        CSVRecord rRecord = null;

        WeatherCSV test = new WeatherCSV();        
        FileResource iFile   = new FileResource();
        CSVParser    cParser = iFile.getCSVParser();
        rRecord = test.coldestHourInFile(cParser);
        System.out.println("Coldest Record #["+rRecord.getRecordNumber()+"]:");
        System.out.println(rRecord);
        System.out.println("Coldest Temp:");
        System.out.println(Double.parseDouble(rRecord.get("TemperatureF")));
    }
   
    
    // #1 *************
    public CSVRecord coldestHourInFile(CSVParser parser) {
        // Find the coldest hour in a file and return that record
        CSVRecord rRecord = null;
        for(CSVRecord record: parser){
            rRecord = testLowest(rRecord, record, "TemperatureF");
        }
        return rRecord;
    }
    
    // #2 *************
    public void testFileWithColdestTemperature() {
        String    fileName  = "";
        CSVRecord rRecord   = null;

        WeatherCSV test = new WeatherCSV();        
        fileName = fileWithColdestTemperature();

        FileResource iFile = new FileResource(fileName);
        CSVParser    cParser = iFile.getCSVParser();
        rRecord = test.coldestHourInFile(cParser);
        
        System.out.println("Coldest day file: "+fileName);
        System.out.println("Coldest Record #["+rRecord.getRecordNumber()+"]:");
        System.out.println(rRecord);
        System.out.println("Coldest Temp:");
        System.out.println(Double.parseDouble(rRecord.get("TemperatureF")));
        System.out.println("All the Temps on that day where:");
        cParser = iFile.getCSVParser();
        for(CSVRecord record: cParser){
            System.out.println( record.get("DateUTC") + ": " + Double.parseDouble(record.get("TemperatureF")));
        }

    }
    
    // #2 *************
    public String fileWithColdestTemperature() {
        // Find the file with the coldest temperature 
        String cFile = "";
        DirectoryResource dr = new DirectoryResource();
        CSVRecord rRecord = null;

        for(File file: dr.selectedFiles()) {
            FileResource f = new FileResource(file);
            CSVParser    cParser = f.getCSVParser();
            for(CSVRecord record: cParser){
                if (rRecord == null) {
                    rRecord = record;
                    cFile   = file.getAbsolutePath();
                } else if (  Double.parseDouble(record.get("TemperatureF")) < Double.parseDouble(rRecord.get("TemperatureF")) 
                          && Double.parseDouble(record.get("TemperatureF")) > -999) {
                    rRecord = record;
                    cFile   = file.getAbsolutePath();
                }
            }
        }
        return cFile;
    }
    
    // #3 *************
    public void testLowestHumidityInFile(String testVal) {
        CSVRecord rRecord   = null;

        WeatherCSV test = new WeatherCSV();        

        FileResource iFile = new FileResource();
        CSVParser    cParser = iFile.getCSVParser();
        rRecord = test.lowestHumidityInFile(cParser, testVal);
        
        System.out.println("Lowest "+ testVal +" Record #["+rRecord.getRecordNumber()+"]:");
        System.out.println(rRecord);
        System.out.println("Lowest " + testVal +":");
        System.out.println(Double.parseDouble(rRecord.get(testVal)) + " " + rRecord.get("DateUTC"));
    }
   
    // #3 *************
    public CSVRecord lowestHumidityInFile(CSVParser parser, String testVal) {
        // Find the coldest hour in a file and return that record
        CSVRecord rRecord = null;
        
        for(CSVRecord record: parser){
            if (record.get(testVal) != "N/A") {
                rRecord = testLowest(rRecord, record, testVal);
            }
        }
        return rRecord;
    }
    
    // #4 *************
    public void testLowestHumidityInManyFiles(String testVal) {
        String    fileName  = "";
        CSVRecord rRecord   = null;

        WeatherCSV test = new WeatherCSV();        
        fileName = lowestHumidityInManyFiles(testVal);

        FileResource iFile = new FileResource(fileName);
        CSVParser    cParser = iFile.getCSVParser();
        rRecord = test.lowestHumidityInFile(cParser, testVal);
        
        System.out.println("Lowest " + testVal + " day file: "+fileName);
        System.out.println("Lowest " + testVal + " Record #["+rRecord.getRecordNumber()+"]:");
        System.out.println(rRecord);
        System.out.println("Lowest " + testVal + " :");
        System.out.println(Double.parseDouble(rRecord.get(testVal)) + " " + rRecord.get("DateUTC"));
        System.out.println("All the " + testVal + " on that day where:");
        cParser = iFile.getCSVParser();
        for(CSVRecord record: cParser){
            System.out.println( record.get("DateUTC") + ": " + Double.parseDouble(record.get(testVal)));
        }
    }

    // #4 *************
    public String lowestHumidityInManyFiles(String matchVal) {
        // Find the file with the coldest temperature 
        String cFile = "";
        DirectoryResource dr = new DirectoryResource();
        CSVRecord rRecord = null;
        System.out.println(" fuck me : " + matchVal + " fuck me");

        for(File file: dr.selectedFiles()) {
            FileResource f = new FileResource(file);
            CSVParser    cParser = f.getCSVParser();
            
            //System.out.println(" fuck me FILE NAME: " + file.getAbsolutePath());

            for(CSVRecord record: cParser){
                if ( (record.get(matchVal).equals("N/A")) ) { //|| Double.parseDouble(record.get(matchVal)) > 0 )  {
                    System.out.println(" bad record #[" +record.getRecordNumber() +"] :: " + record.get("DateUTC"));
                    System.out.println(" bad val #20: **[" +record.get(matchVal)+"]**");
                    System.out.println(file.getAbsolutePath());
//                    System.out.println(record);    
                }
                
                if ( !(record.get(matchVal).equals("N/A")) ) {    
                    if ( rRecord == null ) {
                        rRecord = record;
                        cFile   = file.getAbsolutePath();
                    } else if ((Double.parseDouble(record.get(matchVal)) < Double.parseDouble(rRecord.get(matchVal))) ) {
                        rRecord = record;
                        cFile   = file.getAbsolutePath();
                        //System.out.println(" fuck me : " + matchVal + " fuck me");
                        //System.out.println(Double.parseDouble(rRecord.get(matchVal)) + " " + rRecord.get("DateUTC"));

                    }
                }
            }
        }
        return cFile;
    }

    // #5 *************
    public void testAverageTemperatureInFile(String testVal) {
        double avgVal;
        WeatherCSV test = new WeatherCSV();        

        FileResource iFile = new FileResource();
        CSVParser    cParser = iFile.getCSVParser();
        avgVal = test.averageTemperatureInFile(cParser, testVal);
        
        System.out.println("Average "+ testVal +":");
        System.out.println(avgVal);
    }

    // #5 *************
    public double averageTemperatureInFile(CSVParser cParser, String testVal) {
        // Find the coldest hour in a file and return that record
        double avgVal   = 0.0;
        double sum      = 0.0;
        int cnt         = 0;
        
        for(CSVRecord record: cParser){
            if ( record.get(testVal) != "N/A") { 
                double tempVal = Double.parseDouble(record.get(testVal));
                if (tempVal > -999) {
                    cnt++;
                    sum = tempVal + sum;
                //} else {
                //    System.out.println("Record failded humidty/temp check :" +record);      
                }
            }
        }
        System.out.println("Records checked : " + cnt + " total value : " + sum);      
        
        return sum/cnt;
    }

    // #6 *************
    public void testAverageTemperatureWithHighHumidityInFile(String testStr, double testVal) {
        double avgRet;
        WeatherCSV test = new WeatherCSV();        

        FileResource iFile = new FileResource();
        CSVParser    cParser = iFile.getCSVParser();
        avgRet = test.averageTemperatureWithHighHumidityInFile(cParser, testStr, testVal);
        if ( avgRet > -999.0 ) {
            System.out.println("Average Temp on dayes with "+testStr+" greater than " +testVal+ " :");
            System.out.println(avgRet);            
        } else {
            System.out.println("No Temps on dayes with "+testStr+" greater than " +testVal+ " :");
            System.out.println(avgRet);            
            
        }
    }

    // #6 *************
    public double averageTemperatureWithHighHumidityInFile(CSVParser cParser, String testStr, double testVal) {
        // Find the average temp for the file for humidities > testVal
        //ddouble avgRet   = -999.0;
        double sum      = 0.0;
        int cnt         = 0;
        
        for(CSVRecord record: cParser){
            if ( record.get(testStr) != "N/A" && Double.parseDouble(record.get(testStr)) > testVal) { 
                double tempVal = Double.parseDouble(record.get("TemperatureF"));
                if (tempVal > -999) {
                    cnt++;
                    sum = tempVal + sum;
                }
//            } else {
//                System.out.println("Record failded humidty/temp check :" +record);      
            }
        }
        System.out.println("Records checked : " + cnt + " total value : " + sum);      

        if (cnt > 0) {
            return sum/cnt;
        } else {
            return -999.0;    
        }
        
    }    
    //*************
    public CSVRecord testLowest(CSVRecord oldRecord, CSVRecord newRecord, String checkVal) {
        // Find the coldest hour in a file and return that record
        CSVRecord rRecord = oldRecord;
        if (oldRecord == null || (Double.parseDouble(oldRecord.get(checkVal)) < -999) ) {
            rRecord = newRecord;
        } else if (Double.parseDouble(oldRecord.get(checkVal)) > Double.parseDouble(newRecord.get(checkVal)) ) {
            rRecord = newRecord;
        }
        return rRecord;
    }
    
}
