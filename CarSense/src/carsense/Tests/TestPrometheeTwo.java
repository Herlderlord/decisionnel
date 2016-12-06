/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsense.Tests;

import carsense.Modele.Problem;
import carsense.Modele.Result;
import carsense.Process.Builder;
import carsense.FunctionsPreference.FunctionPreferenceStrategy;
import carsense.FunctionsPreference.VoieBasiqueStrategy;
import carsense.FunctionsPreference.VoieNormalStrategy;
import carsense.Methods.MethodStrategy;
import carsense.Methods.PrometheeOne;
import carsense.Methods.PrometheeTwo;
import carsense.Output.HtmlGenerator;
import carsense.Output.OutputGenerator;
import carsense.Output.StringGenerator;
import java.io.IOException;

/**
 *
 * @author idolon
 */
public class TestPrometheeTwo {
    
    
    public static void main(String args[]) throws IOException {
        
        // -- Test de PROMETHEE II
        String filename = "res/voiture.csv";
        Problem problem = Builder.createProblemVoiture(filename, "res/criteres.csv");
        
        MethodStrategy method = new PrometheeTwo();
        FunctionPreferenceStrategy functionPreference = new VoieBasiqueStrategy();
        ((PrometheeTwo) method).function = functionPreference; 
        method.calcul(problem);
        
        
        OutputGenerator outputGenerator = new StringGenerator();
        System.out.println(outputGenerator.generate((PrometheeTwo)method));

    }
}
