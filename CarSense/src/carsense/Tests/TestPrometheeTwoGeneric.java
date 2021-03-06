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
import carsense.Methods.PrometheeTwo;
import carsense.Modele.DataProblem;
import carsense.Output.OutputGenerator;
import carsense.Output.StringGenerator;
import carsense.Process.DataProblemBuilder;

/**
 * Classe qui permet de tester la méthode promethee 2 sur un jeu de données
 * de base.
 * 
 * @author mathi
 */
public class TestPrometheeTwoGeneric {
    
    public static void main(String[] args) {
        
        // -- Test de PROMETHEE II
        // Récupération des données
        DataProblemBuilder builder = new DataProblemBuilder(); 
        builder.builderDataProblemBuilder("res/dataVoitures.csv", "res/problemDescription.json");
        DataProblem problem = builder.getDataProblem();
        
        // Instancier la méthode et sa fonction
        MethodStrategy method = new PrometheeTwo();
        FunctionPreferenceStrategy functionPreference = new VoieBasiqueStrategy();
        ((PrometheeTwo) method).function = functionPreference;
        
        // Calcul du probleme
        method.calcul(problem);
        
        // Output en String       
        OutputGenerator outputGenerator = new StringGenerator();
        System.out.println(outputGenerator.generate((PrometheeTwo)method));
    }
}
