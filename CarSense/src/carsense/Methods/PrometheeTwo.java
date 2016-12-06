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
import carsense.Modele.Voiture;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mathi
 */
public class PrometheeTwo extends Promethee {
    
    public PrometheeOne prometheeOne = null; 
    public double[] fluxNet = null;
    public List<Voiture> classement = null;
     
    public void calcul(Problem problem, FunctionPreferenceStrategy function) {
        
        double[][] result = function.calculPreference(problem);
        
        // Creation des flux positifs et négatifs.
        double[] fluxPositif = new double[result.length]; 
        double[] fluxNegatif = new double[result.length];
        
       
            
        Arrays.fill(fluxPositif, 0);
        Arrays.fill(fluxNegatif, 0);
        
        for(int i = 0; i < result.length; i++) {
            for(int j=0; j < result[i].length; j++) {
                fluxPositif[j] += result[i][j];  
                fluxNegatif[i] += result[i][j];
            }
        }
        
        this.fluxNet = new double[fluxNegatif.length];
        
        for(int i = 0; i < this.fluxNet.length; i++) {
            this.fluxNet[i] = fluxPositif[i] - fluxNegatif[i];
        }
        
        
        // Création de la liaison entre voiture et fluxNet
        Map<Voiture, Double> map_net = new HashMap<Voiture, Double>();
        
        Iterator<Voiture> it_voiture = problem.voitures.iterator();
        int i = 0;
        while(it_voiture.hasNext()) {
            Voiture voiture = it_voiture.next();
            map_net.put(voiture, new Double(fluxNet[i]));
            i++;
        }
        
        
        // Sort Maps 
        map_net = MapUtil.sortByValue(map_net, false);
        
        
        // Make the classement
        classement = new LinkedList<Voiture>(); 
        
        // For Positif
        for (Map.Entry<Voiture, Double> entry : map_net.entrySet())
        {
            classement.add(entry.getKey());
        }
        
    }
    
}
