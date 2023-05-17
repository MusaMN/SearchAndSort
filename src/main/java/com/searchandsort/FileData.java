
package com.searchandsort;

import java.io.BufferedReader;
import java.io.FileReader;

public class FileData {
    
    Database db = new Database();
    private String line="";
    private String[] dataLine=null;
    
    public void loadData(String filePath) throws ClassNotFoundException{
        db.connect();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))){
            while((line = br.readLine()) != null){
                dataLine = line.split(",");
                db.insertData(dataLine);
            }
       }
       catch (Exception e){
           System.out.println("error ==== " + e.getMessage());           
       }
    }
}
