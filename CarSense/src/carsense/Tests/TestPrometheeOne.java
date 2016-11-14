/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsense.Tests;

import carsense.FunctionsPreference.*;
import carsense.Methods.*;
import carsense.Tests.*;

/**
 *
 * @author mathi
 */
public class TestPrometheeOne {
    
    public static void main(String [] args) {
        
        // -- Test de PROMETHEE I
        
        // Get data 
        String dataFilename = "nameFile"; 
        Data data = getDataFile(dataFilename); 
        
        // Choose Methods and Preference 
        PrometheeOne promethee = new PrometheeOne(); 
        VoieBasiqueStrategy voieBasique = new VoieBasiqueStrategy();
        
        // Calcul Promethee 
        promethee.calcul(data, promethee, voieBasique); 
        
        // Generate and write Html 
        HtmlGenerator htmlGenerator = new HtmlGenerator(); 
        writeFile("data", htmlGenerator.generateHtml(promethee));
        
        
    }
}
