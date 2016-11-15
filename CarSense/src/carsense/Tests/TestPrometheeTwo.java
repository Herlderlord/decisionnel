/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsense.Tests;

import Modele.Problem;
import Modele.Result;
import Process.Builder;
import carsense.FunctionsPreference.FunctionPreferenceStrategy;
import carsense.FunctionsPreference.VoieNormalStrategy;
import carsense.Methods.MethodStrategy;
import carsense.Methods.PrometheeOne;
import carsense.Methods.PrometheeTwo;
import carsense.Output.HtmlGenerator;

/**
 *
 * @author idolon
 */
public class TestPrometheeTwo {
    
    
    public static void main(String args[]) {
        
        // -- Test de PROMETHEE II
        String filename = "monFichier.csv";
        Problem problem = Builder.createProblemVoiture(filename);
        
        MethodStrategy method = new PrometheeTwo();
        FunctionPreferenceStrategy functionPreference = new VoieNormalStrategy();
        
        Result result = method.calcul(problem, functionPreference);
        
        HtmlGenerator htmlGenerator = new HtmlGenerator();
        htmlGenerator.generateHtml((PrometheeOne)method);

    }
}
