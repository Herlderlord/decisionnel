/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsense.FunctionsPreference;

import carsense.Modele.DataProblem;
import carsense.Modele.Problem;

/**
 *
 * @author mathi
 */
public interface FunctionPreferenceStrategy {
    
    public double[][] calculPreference(Problem problem); 
    public double[][] calculPreference(DataProblem problem);
}
