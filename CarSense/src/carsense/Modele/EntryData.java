/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsense.Modele;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Structure de donnée d'un entrée dans les données CSV. 
 * 
 * @author mathi
 */
public class EntryData {
    // Attributes
    public int                      idEntry = 0;
    public String                   name = "";
    public Map<String, Double>      data;
    public List<String>             fields;
    
    private static int              nbEntryData = 0;
    
    
    /**
     * 
     */
    public EntryData() {
        data = new HashMap<String,Double>();
        this.idEntry = nbEntryData;
        nbEntryData ++;
    }
    
    /**
     * 
     * @return 
     */
    public String toString() {
        String value = "";
        
        value += "[" + name + ", ";
        for (Map.Entry<String, Double> entry : this.data.entrySet()) {
             value += entry.getValue() + ", ";
        }
        value += "]";
        return value;
    }
    
    /**
     * 
     */
    public void generateName() {
        this.name = "Entry_" + this.idEntry;
    }
    
}
