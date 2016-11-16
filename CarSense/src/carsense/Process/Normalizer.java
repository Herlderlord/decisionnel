package carsense.Process;


import carsense.Modele.DataProblem;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author idolon
 */
public class Normalizer {
    
    
    
    /**
     * Normalize the problem. 
     * 
     * @param data 
     */
    public void normalizeProblem(DataProblem data) {
        
    }
    
    
    /**
     * 
     */
    public void transformInMaxProblem(DataProblem data) {
        
        for(int i = 0; i < data.getIsMaxProblem().length; i++) {
            if(!data.getIsMaxProblem()[i]) {
                
                for(int y = 0; y < data.getValues().length; y++) {
                    
                }
                // On doit inverser chaque valeur du problème.
                
            } 
        }
    }
}
