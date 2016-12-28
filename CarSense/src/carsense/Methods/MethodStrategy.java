/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsense.Methods;

import carsense.Modele.Problem;
import carsense.Modele.Result;
import carsense.FunctionsPreference.FunctionPreferenceStrategy;
import carsense.Modele.DataProblem;

/**
 *
 * @author mathi
 */
public abstract class MethodStrategy {
        
    /**
     * Calcul avec l'ancien type de données
     * @param problem
     * @return
     */
    public abstract void calcul(Problem problem); 
    
    /**
     * Calcul avec les données génériques
     * @param problem 
     */
    public abstract void calcul(DataProblem problem);

}
