/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsense.Methods;

import carsense.Modele.Problem;
import carsense.Modele.Result;
import carsense.FunctionsPreference.FunctionPreferenceStrategy;

/**
 *
 * @author mathi
 */
public abstract class MethodStrategy {
        
    /**
     *
     * @param problem
     * @param function
     * @return
     */
    public abstract void calcul(Problem problem, FunctionPreferenceStrategy function); 

}
