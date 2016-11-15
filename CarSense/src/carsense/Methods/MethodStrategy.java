/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsense.Methods;

import Modele.Problem;
import Modele.Result;
import carsense.FunctionsPreference.FunctionPreferenceStrategy;

/**
 *
 * @author mathi
 */
public abstract class MethodStrategy {
    
    /**
     *
     */
    protected Result result;
    
    /**
     *
     * @param problem
     * @param function
     * @return
     */
    public abstract Result calcul(Problem problem, FunctionPreferenceStrategy function); 

    /**
     *
     * @return
     */
    public abstract Result getResult(); 
    
}
