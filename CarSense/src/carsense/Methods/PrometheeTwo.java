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
import carsense.Modele.DataProblem;
import carsense.Modele.EntryData;
import carsense.Modele.Voiture;
import java.util.ArrayList;
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
    public List<EntryData> classementGeneric = null;
    
    @Override
    public void calcul(Problem problem, FunctionPreferenceStrategy function) {
        this.classementGeneric = new LinkedList<EntryData>();
        this.fluxNet = this.calculFluxNet(function.calculPreference(problem));
        
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
    
    @Override
    public void calcul(DataProblem problem, FunctionPreferenceStrategy function) {
        this.classement = new ArrayList<Voiture>();
        double[] fluxNet = this.calculFluxNet(function.calculPreference(problem));
        
        // Link each line with Flux Net
        Map<EntryData, Double> map_net = new HashMap<EntryData, Double>(); 
        Iterator<EntryData> it_entry = problem.data.iterator();
        
        int i = 0; 
        for(EntryData entry : problem.data) {
            map_net.put(entry, new Double(fluxNet[i]));
            i++;
        }
        
        // Sort Maps 
        map_net = MapUtil.sortByValue(map_net, false);
        
        
        // Make the classement for net map
        classementGeneric = new LinkedList<EntryData>(); 
        for (Map.Entry<EntryData, Double> entry : map_net.entrySet()) {
            classementGeneric.add(entry.getKey());
        }
    }
    
    
    /**
     * Allow user to calcul flux net.
     * @param problem 
     */
    public double[] calculFluxNet(double[][] scorePreference) {
        double[][] result = scorePreference;
        
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
        return fluxNet;
    }
    
}
