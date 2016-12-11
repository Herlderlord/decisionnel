/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsense.Process;

import Utils.CsvParser;
import carsense.Modele.DataProblem;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.*;
/**
 *
 * @author mathi
 */
public class DataProblemBuilder {
    
    private DataProblem dataProblem; 

    /**
     * Basic constructor which initialize some variables. 
     */
    public DataProblemBuilder() {
        this.dataProblem = null;
    }
    
    /**
     * 
     * @param dataProblem
     * @param dataFileName
     * @param problemDescriptionFileName 
     */
    public void builderDataProblemBuilder(String dataFilePath, String problemDescriptionFilePath) {
        
        // Data file
        FileInputStream fis = null;
        try {
            // data file
            this.dataProblem = new DataProblem();
            CsvParser csvParser = new CsvParser();
            File file = new File (dataFilePath);
            fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            Iterator<Map<String, String>> dataIt = csvParser.parseAsMaps(isr);
            boolean getTitles = false;
            
            
            int i = 0; 
            while(dataIt.hasNext()) {
                Map<String, String> line = dataIt.next();
                this.dataProblem.data.add(line);
            }
        // Problem Description file
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DataProblemBuilder.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(DataProblemBuilder.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       
        
        
        try {
            // Problem description JSON
            File file = new File (problemDescriptionFilePath);
            fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            JSONTokener tokener = new JSONTokener(isr);
            JSONObject root = new JSONObject(tokener);  
            
            // Get is max problem
            JSONArray max = root.getJSONArray("max");
            this.dataProblem.isMaxProblem = new boolean[max.length()];
            for(int i = 0; i < max.length(); i++) {
                this.dataProblem.isMaxProblem[i] = max.getBoolean(i);
            }
            
            // Get poids 
            JSONArray poids = root.getJSONArray("poids"); 
            this.dataProblem.poids = new double[poids.length()];
            for(int i = 0; i < poids.length(); i++) {
                this.dataProblem.poids[i] = poids.getDouble(i);
            }
            
            // Get seuilIndifference
            JSONArray seuilIndifference = root.getJSONArray("seuilIndifference");
            this.dataProblem.seuilIndifference = new double[seuilIndifference.length()];
            for(int i = 0; i < seuilIndifference.length(); i++) {
                this.dataProblem.seuilIndifference[i] = seuilIndifference.getDouble(i);
            }
            
            // Get seuilPref
            JSONArray seuilPref = root.getJSONArray("seuilPref"); 
            this.dataProblem.seuilPref = new double[seuilPref.length()]; 
            for(int i = 0; i < seuilPref.length(); i++) {
                this.dataProblem.seuilPref[i] = seuilPref.getDouble(i);
            }
            
            // Get seuilVeto
            JSONArray seuilVeto = root.getJSONArray("seuilVeto");
            this.dataProblem.seuilVeto = new double[seuilVeto.length()];
            for(int i = 0; i < seuilVeto.length(); i++) {
                this.dataProblem.seuilVeto[i] = seuilVeto.getDouble(i);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(DataProblemBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
    /**
     * 
     * @return 
     */
    DataProblem getDataProblem() {
        return this.dataProblem;
    }
    
    public static void main(String[] args) {
        // Tests for this class
        DataProblemBuilder dataBuilder = new DataProblemBuilder(); 
        
        dataBuilder.builderDataProblemBuilder("res/dataVoitures.csv", "res/problemDescription.json");
    }
}
