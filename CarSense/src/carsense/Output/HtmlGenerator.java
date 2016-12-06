/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsense.Output;

import carsense.Output.*;
import carsense.Methods.*;
import carsense.Modele.Voiture;
import java.util.Iterator;

/**
 *
 * @author mathi
 */
public class HtmlGenerator implements OutputGenerator {
    
    public String generate(PrometheeOne promethee) {
        return "Output de promethee One. ";
    }
    
    public String generate(PrometheeTwo promethee) {
        return "Output de promethee Two. ";
    }
    
    public String generate(Borda borda) {
        return "Ouput de borda";
    }
}
