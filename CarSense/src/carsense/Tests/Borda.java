/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsense.Tests;

import carsense.Modele.Voiture;
import carsense.Process.Builder;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author dem_l
 */
public class Borda {
    public static void main(String [] args) throws IOException {
        
        //Systeme de votes ??
        List<Voiture> voitures = Builder.createProblemVoiture("res/voitures.csv","res/criteres.csv").voitures;
        carsense.Methods.Borda b = new carsense.Methods.Borda(voitures);
        
        
        
        /*
        // -- Test de BORDA
        String filename = "monFichier.csv";
        Problem problem = Builder.createProblemVoiture(filename);
        
        Borda method = new Borda();
        
        Result result = method.calcul(problem);
        
        HtmlGenerator htmlGenerator = new HtmlGenerator();
        htmlGenerator.generateHtml((Borda)method);
        */

        
    }
}
