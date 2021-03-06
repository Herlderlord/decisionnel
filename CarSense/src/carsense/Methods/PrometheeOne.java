/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsense.Methods;



import Utils.MapUtil;
import carsense.Modele.Problem;
import carsense.Modele.Result;
import carsense.FunctionsPreference.FunctionPreferenceStrategy;
import carsense.FunctionsPreference.VoieBasiqueStrategy;
import carsense.Modele.DataProblem;
import carsense.Modele.EntryData;
import carsense.Modele.Voiture;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mathi
 */
public class PrometheeOne extends Promethee {
    
    public DataProblem problem;
    public double[] fluxPositif; 
    public double[] fluxNegatif;
    public List<Voiture> classementPositif; 
    public List<Voiture> classementNegatif;
    public List<EntryData> classementPositifGeneric; 
    public List<EntryData> classementNegatifGeneric; 
    
    public PrometheeOne() {
    }
    
    @Override
    public void calcul(Problem problem, FunctionPreferenceStrategy function) {
        
        this.fields = new ArrayList<String>();
        this.fields.add("Prix");
        this.fields.add("VitesseMax"); 
        this.fields.add("CoffreMax");
        this.fields.add("ConsoMoyenne"); 
        this.fields.add("DistanceDeFreinage");
        this.fields.add("Confort");
        classementPositifGeneric = new LinkedList<EntryData>(); 
        classementNegatifGeneric = new LinkedList<EntryData>();
        
        double[][] result = function.calculPreference(problem);
        
        // Creation des flux positifs et négatifs.
        fluxPositif = new double[result.length]; 
        fluxNegatif = new double[result.length];
       
            
        Arrays.fill(fluxPositif, 0);
        Arrays.fill(fluxNegatif, 0);
        
        for(int i = 0; i < result.length; i++) {
            for(int j=0; j < result[i].length; j++) {
                fluxPositif[j] += result[i][j];  
                fluxNegatif[i] += result[i][j];
            }
        }
        
        
        // Création des liaisons entre voiture et flux.
        Map<Voiture, Double> map_positif = new HashMap<Voiture, Double>();
        Map<Voiture, Double> map_negatif = new HashMap<Voiture, Double>();
        
        Iterator<Voiture> it_voiture = problem.voitures.iterator();
        int i = 0;
        while(it_voiture.hasNext()) {
            Voiture voiture = it_voiture.next();
            map_positif.put(voiture, new Double(fluxPositif[i]));
            map_negatif.put(voiture, new Double(fluxNegatif[i]));
            i++;
        }
        
        // Sort Maps 
        map_positif = MapUtil.sortByValue(map_positif, false);
        map_negatif = MapUtil.sortByValue(map_negatif, true);
        
        
        // Transform maps into Classements.
        classementPositif = new LinkedList<Voiture>(); 
        classementNegatif = new LinkedList<Voiture>();
        
        
        // For Positif
        for (Map.Entry<Voiture, Double> entry : map_positif.entrySet()) {
            classementPositif.add(entry.getKey());
        }
        
        // For Negatif
        for (Map.Entry<Voiture, Double> entry : map_negatif.entrySet()) {
            classementNegatif.add(entry.getKey());
        }
    }
    
    
    @Override
    public void calcul(DataProblem problem, FunctionPreferenceStrategy function) {
        this.problem = problem;
        this.fields = problem.fields;
        double[][] result = function.calculPreference(problem);
        
        // Creation des flux positifs et négatifs.
        fluxPositif = new double[result.length]; 
        fluxNegatif = new double[result.length];
       
            
        Arrays.fill(fluxPositif, 0);
        Arrays.fill(fluxNegatif, 0);
        
        for(int i = 0; i < result.length; i++) {
            for(int j=0; j < result[i].length; j++) {
                fluxPositif[j] += result[i][j];  
                fluxNegatif[i] += result[i][j];
            }
        }
        
        // Création des liaisons entre voiture et flux.
        Map<EntryData, Double> map_positif = new HashMap<EntryData, Double>();
        Map<EntryData, Double> map_negatif = new HashMap<EntryData, Double>();
        
        Iterator<EntryData> it_entry = problem.data.iterator();
        
        int i = 0; 
        for(EntryData data : problem.data) {
            map_positif.put(data, new Double(fluxPositif[i]));
            map_negatif.put(data, new Double(fluxNegatif[i]));
            i++;
        }
        
        // Sort Maps 
        map_positif = MapUtil.sortByValue(map_positif, false);
        map_negatif = MapUtil.sortByValue(map_negatif, true);
        
        
        // Transform maps into Classements.
        classementPositif = new LinkedList<Voiture>(); 
        classementNegatif = new LinkedList<Voiture>();
        
        classementPositifGeneric = new LinkedList<EntryData>(); 
        classementNegatifGeneric = new LinkedList<EntryData>();
        
        
        // For Positif
        for (Map.Entry<EntryData, Double> entry : map_positif.entrySet()) {
            classementPositifGeneric.add(entry.getKey());
        }
        
        // For Negatif
        for (Map.Entry<EntryData, Double> entry : map_negatif.entrySet()) {
            classementNegatifGeneric.add(entry.getKey());
        }
    }
    
    
}
