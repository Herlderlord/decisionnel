/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsense.Methods;

import carsense.FunctionsPreference.FunctionPreferenceStrategy;
import carsense.Modele.Problem;
import carsense.Modele.Result;
import carsense.Modele.Voiture;
import java.util.List;
import java.util.Map;

/**
 *
 * @author dem_l
 */
public class Borda extends MethodStrategy {

    public List<Voiture> voitures;
    
    public Map<Voiture, Double> critere1, score1;
    public Map<Voiture, Double> critere2, score2;
    public Map<Voiture, Double> critere3, score3;
    public Map<Voiture, Double> critere4, score4;
    public Map<Voiture, Double> critere5, score5;
    public Map<Voiture, Double> critere6, score6;
    public Map<Voiture, Double> critere7, score7;
    
    public Borda (List<Voiture> voitures) {
        this.voitures = voitures;
    }
    
    public void remplissage () {
        for(Voiture v : voitures ) {
            
        }
    }
    
    public Result calcul(Problem problem) {
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Result getResult() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void calcul(Problem problem, FunctionPreferenceStrategy function) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
