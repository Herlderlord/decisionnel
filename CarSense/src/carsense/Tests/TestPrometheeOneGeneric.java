/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsense.Tests;

import carsense.FunctionsPreference.FunctionPreferenceStrategy;
import carsense.FunctionsPreference.VoieBasiqueStrategy;
import carsense.Methods.MethodStrategy;
import carsense.Methods.PrometheeOne;
import carsense.Modele.DataProblem;
import carsense.Modele.Problem;
import carsense.Output.HtmlGenerator;
import carsense.Output.OutputGenerator;
import carsense.Process.Builder;
import carsense.Process.DataProblemBuilder;

/**
 *
 * @author mathi
 */
public class TestPrometheeOneGeneric {
    
    public static void main(String[] args) {
        
        // -- Test de PROMETHEE I
        String filename = "res/voiture.csv";
        DataProblemBuilder builder = new DataProblemBuilder(); 
        builder.builderDataProblemBuilder(filename, filename);
        DataProblem problem = builder.getDataProblem();
        
        MethodStrategy method = new PrometheeOne();
        FunctionPreferenceStrategy functionPreference = new VoieBasiqueStrategy();
        ((PrometheeOne) method).function = functionPreference;
        method.calcul(problem);
        
        OutputGenerator outputGenerator = new HtmlGenerator();
        System.out.println(outputGenerator.generate((PrometheeOne)method));
    }
}
