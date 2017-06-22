package course1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.io.*;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import edu.duke.FileResource;
import edu.duke.StorageResource;


public class FirstCSVExample {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        FirstCSVExample testCSV = new FirstCSVExample();
        testCSV.readFood();
    }
    
    public void readFood() {
        // TODO Auto-generated method stub
        FileResource iFile = new FileResource("foods.csv");
        CSVParser    cFile = iFile.getCSVParser();
        for (CSVRecord record: cFile) {
            System.out.print(record.get("Name")+" ");
            System.out.print(record.get("Favorite Color")+" ");
            System.out.println(record.get("Favorite Food"));
        }
//        for (String words : iFile.words()) {
//            System.out.println(words);
//        }
         
    }

}
