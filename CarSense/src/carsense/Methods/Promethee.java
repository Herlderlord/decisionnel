/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsense.Methods;

import carsense.FunctionsPreference.FunctionPreferenceStrategy;
import carsense.FunctionsPreference.VoieBasiqueStrategy;
import carsense.Modele.DataProblem;
import carsense.Modele.Problem;

/**
 *
 * @author mathi
 */
public abstract class Promethee extends MethodStrategy {
    
    public FunctionPreferenceStrategy function = null; 
    
    /**
     * Calcul avec les anciennes données
     * @param problem
     * @param function 
     */
    public abstract void calcul(Problem problem, FunctionPreferenceStrategy function); 
    
    /**
     * Calcul avec les données génériques
     * @param problem
     * @param function 
     */
    public abstract void calcul(DataProblem problem, FunctionPreferenceStrategy function); 
    
    @Override
    public void calcul(Problem problem) {
        if(function == null) {
            this.calcul(problem, new VoieBasiqueStrategy());
        } else 
            this.calcul(problem, function);
    }
    
    @Override
    public void calcul(DataProblem problem) {
        if(function == null) {
            this.calcul(problem, new VoieBasiqueStrategy());
        } else 
            this.calcul(problem, function);
    }

}
