/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsense.Methods;

import carsense.Modele.Problem;
import carsense.Modele.Result;
import carsense.FunctionsPreference.FunctionPreferenceStrategy;
import carsense.FunctionsPreference.VoieBasiqueStrategy;
import java.util.Arrays;

/**
 *
 * @author mathi
 */
public class PrometheeOne extends MethodStrategy {
    
    
    public double[] fluxPositif; 
    public double[] fluxNegatif;
    
    public PrometheeOne() {
        
    }
    
    public void calcul(Problem problem, FunctionPreferenceStrategy function) {
        
        double[][] result = function.calculPreference(problem);
        
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
        
    }
    
}
