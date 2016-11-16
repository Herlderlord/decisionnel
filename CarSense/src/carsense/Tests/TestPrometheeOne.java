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
import carsense.Tests.*;
import java.io.IOException;

/**
 *
 * @author mathi
 */
public class TestPrometheeOne {
    
    public static void main(String [] args) throws IOException {
        
        // -- Test de PROMETHEE I
        String filename = "monFichier.csv", filename1 = "monFichier1.csv";
        Problem problem = Builder.createProblemVoiture(filename, filename1);
        
        MethodStrategy method = new PrometheeOne();
        FunctionPreferenceStrategy functionPreference = new VoieNormalStrategy();
        
        Result result = method.calcul(problem, functionPreference);
        
        HtmlGenerator htmlGenerator = new HtmlGenerator();
        htmlGenerator.generateHtml((PrometheeOne)method);

        
    }
}
