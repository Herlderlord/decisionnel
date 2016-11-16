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
import carsense.FunctionsPreference.VoieNormalStrategy;
import carsense.Methods.MethodStrategy;
import carsense.Methods.PrometheeOne;
import carsense.Methods.PrometheeTwo;
import carsense.Output.HtmlGenerator;
import java.io.IOException;

/**
 *
 * @author idolon
 */
public class TestPrometheeTwo {
    
    
    public static void main(String args[]) throws IOException {
        
        // -- Test de PROMETHEE II
        String filename = "monFichier.csv", filename1 = "monFichier1.csv";
        Problem problem = Builder.createProblemVoiture(filename, filename1);
        
        MethodStrategy method = new PrometheeTwo();
        FunctionPreferenceStrategy functionPreference = new VoieNormalStrategy();
        
        Result result = method.calcul(problem, functionPreference);
        
        HtmlGenerator htmlGenerator = new HtmlGenerator();
        htmlGenerator.generateHtml((PrometheeOne)method);

    }
}
