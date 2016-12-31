/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsense.Tests;

import carsense.Modele.Problem;
import carsense.Modele.Result;
import carsense.Process.Builder;
import carsense.FunctionsPreference.*;
import carsense.Methods.*;
import carsense.Output.HtmlGenerator;
import carsense.Output.OutputGenerator;
import carsense.Output.StringGenerator;
import carsense.Tests.*;
import java.io.IOException;

/**
 *
 * @author mathi
 */
public class TestPrometheeOne {
    
    public static void main(String [] args) throws IOException {
        
        // -- Test de PROMETHEE I
        String filename = "res/voiture.csv";
        Problem problem = Builder.createProblemVoiture(filename, "res/criteres.csv");
        
        MethodStrategy method = new PrometheeOne();
        FunctionPreferenceStrategy functionPreference = new VoieBasiqueStrategy();
        ((PrometheeOne) method).function = functionPreference;
        method.calcul(problem);
        
        OutputGenerator outputGenerator = new StringGenerator();
        System.out.println(outputGenerator.generate((PrometheeOne)method));        
    }
}
