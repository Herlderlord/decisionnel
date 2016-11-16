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
import carsense.Modele.Voiture;
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
public class PrometheeOne extends MethodStrategy {
    
    
    public double[] fluxPositif; 
    public double[] fluxNegatif;
    public List<Voiture> classementPositif; 
    public List<Voiture> classementNegatif;
    
    public PrometheeOne() {
        
    }
    
    public void calcul(Problem problem, FunctionPreferenceStrategy function) {
        
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
        }
        
        // Sort Maps 
        map_positif = MapUtil.sortByValue(map_positif);
        map_negatif = MapUtil.sortByValue(map_negatif);
        
        
        // Transform maps into Classements.
        classementPositif = new LinkedList<Voiture>(); 
        classementNegatif = new LinkedList<Voiture>();
        
        
        // For Positif         
        for (Map.Entry<Voiture, Double> entry : map_positif.entrySet())
        {
            classementPositif.add(entry.getKey());
        }
        
        // For Negatif
        for (Map.Entry<Voiture, Double> entry : map_negatif.entrySet())
        {
            classementNegatif.add(entry.getKey());
        }
        
    }
    
    
}
