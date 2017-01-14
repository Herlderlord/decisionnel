/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsense.Methods;

import Utils.MapUtil;
import carsense.FunctionsPreference.FunctionPreferenceStrategy;
import carsense.Modele.DataProblem;
import carsense.Modele.EntryData;
import carsense.Modele.Problem;
import carsense.Modele.Result;
import carsense.Modele.Voiture;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;



/**
 * Borda
 * on remplit des listes de criteres et de score
 * on applique borda comme si on avait un système de votes
 * 
 * @author Ludovic
 */
public class Borda extends MethodStrategy {

    public DataProblem problem;
    public List<Voiture> voitures;
    
    public Map<Voiture, Double> critere1;
    public Map<Voiture, Double> critere2;
    public Map<Voiture, Double> critere3;
    public Map<Voiture, Double> critere4;
    public Map<Voiture, Double> critere5;
    public Map<Voiture, Double> critere6;
    public Map<Voiture, Double> critere7;
    
    
    public List<Voiture> classement;
    // Final score est trié
    public double [] finalScores;
    public List<String> fields;
    
    
    // Generic Attributes 
    public List<EntryData> classementGeneric; 
    
    /**
     * Cette liste nous permet d'avoir pour chaque critère le classement 
     * des différentes entrées.
     */
    public Map<String,Map<EntryData, Double>> criteres;
    
    /**
     * 
     * @param voitures 
     */
    public Borda () {
        this.voitures = voitures;
        this.critere1 = new HashMap<> ();
        this.critere2 = new HashMap<> ();
        this.critere3 = new HashMap<> ();
        this.critere4 = new HashMap<> ();
        this.critere5 = new HashMap<> ();
        this.critere6 = new HashMap<> ();
        this.critere7 = new HashMap<> ();
        this.classement = new ArrayList<Voiture>();
        
        // Generic
        this.criteres = new HashMap<String, Map<EntryData,Double>>();
        this.classementGeneric = new LinkedList<EntryData>();
    }
    
    /**
     * 
     */
    private void remplissage () {
        for(Voiture v : this.voitures ) {
            critere1.put(v, v.getPrix()); //CritereValue ?
            critere2.put(v, v.getVitesse_max()); //idem + etc.
            critere3.put(v, v.getConso_moyenne());
            critere4.put(v, v.getDist_freinage());
            critere5.put(v, Double.valueOf(v.getConfort()));
            critere6.put(v, v.getVolume_coffre());
            critere7.put(v, v.getAcceleration());
        }
        //On trie dans l'ordre décroissant le critère qu'on veut maximiser
        //Dans l'ordre croissant le critère à minimiser
        critere1 = MapUtil.sortByValue (critere1, true); //min
        critere2 = MapUtil.sortByValue (critere2, false); //max
        critere3 = MapUtil.sortByValue (critere3, true); //min
        critere4 = MapUtil.sortByValue (critere4, true); //min
        critere5 = MapUtil.sortByValue (critere5, false); //max
        critere6 = MapUtil.sortByValue (critere6, false); //max
        critere7 = MapUtil.sortByValue (critere7, false); //max
        
        this.finalScores = new double[this.voitures.size()];
        
    }

    @Override
    public void calcul(Problem problem) {
        
        this.fields = new ArrayList<String>();
        this.fields.add("Prix");
        this.fields.add("VitesseMax"); 
        this.fields.add("CoffreMax");
        this.fields.add("ConsoMoyenne"); 
        this.fields.add("DistanceDeFreinage");
        this.fields.add("Confort");
        
        this.voitures = problem.voitures;
        this.remplissage();
        int nbVoitures = this.voitures.size();
        int i=0;
        
        
        // Score Init 
        Map<Voiture, Double> scores; 
        scores = new HashMap<> ();
        
        // -- Calculating scores
        // Critere 1 
        for (Map.Entry<Voiture, Double> entry : critere1.entrySet()) {
            if(scores.get(entry.getKey()) == null)
                scores.put(entry.getKey(), entry.getValue());
            else
                scores.put(entry.getKey(), new Double(entry.getValue() + scores.get(entry.getKey())));
        }
        // Critere 2 
        for (Map.Entry<Voiture, Double> entry : critere2.entrySet()) {
            if(scores.get(entry.getKey()) == null)
                scores.put(entry.getKey(), entry.getValue());
            else
                scores.put(entry.getKey(), new Double(entry.getValue() + scores.get(entry.getKey())));
        }
        // Critere 3
        for (Map.Entry<Voiture, Double> entry : critere3.entrySet()) {
            if(scores.get(entry.getKey()) == null)
                scores.put(entry.getKey(), entry.getValue());
            else
                scores.put(entry.getKey(), new Double(entry.getValue() + scores.get(entry.getKey())));
        }
        // Critere 4 
        for (Map.Entry<Voiture, Double> entry : critere4.entrySet()) {
            if(scores.get(entry.getKey()) == null)
                scores.put(entry.getKey(), entry.getValue());
            else
                scores.put(entry.getKey(), new Double(entry.getValue() + scores.get(entry.getKey())));
        }
        // Critere 5
        for (Map.Entry<Voiture, Double> entry : critere5.entrySet()) {
            if(scores.get(entry.getKey()) == null)
                scores.put(entry.getKey(), entry.getValue());
            else
                scores.put(entry.getKey(), new Double(entry.getValue() + scores.get(entry.getKey())));
        }
        // Critere 6 
        for (Map.Entry<Voiture, Double> entry : critere6.entrySet()) {
            if(scores.get(entry.getKey()) == null)
                scores.put(entry.getKey(), entry.getValue());
            else
                scores.put(entry.getKey(), new Double(entry.getValue() + scores.get(entry.getKey())));
        }
        // Critere 7
        for (Map.Entry<Voiture, Double> entry : critere7.entrySet()) {
            if(scores.get(entry.getKey()) == null)
                scores.put(entry.getKey(), entry.getValue());
            else
                scores.put(entry.getKey(), new Double(entry.getValue() + scores.get(entry.getKey())));
        }
        
        // Sorting by score
        scores = MapUtil.sortByValue(scores, false);
    
        // Creating classment List 
        i = 0; 
        for (Map.Entry<Voiture, Double> entry : scores.entrySet()) {
            //System.out.println(entry.getKey().getNom());
            this.classement.add(entry.getKey());
            this.finalScores[i] = entry.getValue();
            i++; 
        }
    }
    
    @Override
    public void calcul(DataProblem problem) {
        this.problem = problem;
        this.fields = problem.fields;
        // Filing each critere with their entries values.
        for(EntryData entry : problem.data) {
            for(String field : entry.fields) {
                
                // If critere classement not created
                if(this.criteres.get(field) == null) {
                    HashMap<EntryData, Double> entries = new HashMap<>();
                    entries.put(entry, entry.data.get(field));
                    this.criteres.put(field, entries);
                } 
                // If critere classement already created
                else {
                    this.criteres.get(field).put(entry, entry.data.get(field));
                }
            }
        }
        
        // Sorting each critere by min or max 
        int i = 0; 
        for(String field : problem.fields) {
            this.criteres.put(field, MapUtil.sortByValue(this.criteres.get(field), problem.isMaxProblem[i]));
            i++;
        }
        
        this.finalScores = new double[problem.data.size()]; 
        
        // Scoring 
        Map<EntryData, Double> scoring; 
        scoring = new HashMap<>();
        
        for(String field : problem.fields) {
            for(Map.Entry<EntryData, Double> entry : this.criteres.get(field).entrySet()) {
                if(scoring.get(entry.getKey()) == null)
                    scoring.put(entry.getKey(), entry.getValue());
                else
                    scoring.put(entry.getKey(), new Double(entry.getValue() + scoring.get(entry.getKey())));
            }
        }
        
        // Sorting 
        scoring = MapUtil.sortByValue(scoring, false);
        
        i = 0;
        for (Map.Entry<EntryData, Double> entry : scoring.entrySet()) {
            this.classementGeneric.add(entry.getKey());
            this.finalScores[i] = entry.getValue(); 
            i++;
        }
        
    }
    
    
}
