package carsense.Modele;

import carsense.Process.Builder;
import carsense.Process.DataProblemBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author idolon
 */
public class DataProblem {
    
    /**
     * Data with titles and values 
     */
    public List<EntryData> data;
    public List<String> fields;
    public boolean[] isMaxProblem;
    public double[] poids;
    public double[] seuilIndifference;
    public double[] seuilPref;
    public double[] seuilVeto;
    
    
    /**
     * Init each variable of DataProblem 
     * 
     * @param size_x
     * @param size_y 
     */
    public DataProblem() {
        this.data = new LinkedList<EntryData>();
        this.fields = new ArrayList<String>();
    }
    
    
    public String toString() {
        // Valeur à retourner
        String value = "";
        
        // -- Number of data. 
        value += "Number of lines : " + this.data.size() + "\n";
        
        // -- Fields 
        value += "Field : [";
        Iterator<String> it = fields.iterator();
        while(it.hasNext()) {
            String field = it.next();
            value += field + ", ";
        }
        value += "]\n";
        
        // -- 10 data lines maximum 
        Iterator<EntryData> itData = data.iterator();
        int i = 0;
        while(itData.hasNext() && i < 10) {
            EntryData data = itData.next(); 
            value += data.toString() + "\n";
            i++;
        }
        value += "\n";
        
        // -- On fera plus tard le reste.      
        // Poids
        value += "Poids : [";
        for(i=0; i < poids.length; i++) {
            value += poids[i] + ", ";
        }
        value += "]\n";
        
        // Seuil Indifference
        value += "Indifference : ["; 
        for(i=0; i < this.seuilIndifference.length; i++) {
            value += this.seuilIndifference[i] + ", ";
        }
        value += "]\n";
        
        // Preference
        value += "Pref : ["; 
        for(i=0; i < this.seuilPref.length; i++) {
            value += this.seuilPref[i] + ", ";
        }
        value += "]\n";
        
        // Seuil Veto
        value += "Veto : ["; 
        for(i=0; i < this.seuilVeto.length; i++) {
            value += this.seuilVeto[i] + ", ";
        }
        value += "]\n";
        
        return value;
    }
    
    public static void main(String[]args) throws IOException {
        /*
        TODO: 
            Tester avec des données de test, sans passer par un fichier.
            Tester affichage.
        */
        
        /*
        Je veux que mes données soient mises dans quoi ? 
            Une liste avec tous les champs pourquoi pas ? 
            On garde bien une map sinon. 
            Et je récupère à chaque fois un double.
        */
    }
}
