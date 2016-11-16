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
public class HtmlGenerator {
    
    public String generateHtml(PrometheeOne promethee) {
        
        
        // Flux positifs et negatifs.
        double[] fluxNegatif = promethee.fluxNegatif; 
        double[] fluxPositif = promethee.fluxPositif;
        
        String message_negatif = "["; 
        String message_positif = "[";
        for(int i = 0; i < fluxNegatif.length && i < fluxPositif.length; i++) {
            message_negatif += fluxNegatif[i] + " ,";
            message_positif += fluxPositif[i] + " ,";
        }
        message_negatif += "]"; 
        message_positif += "]";
        
        System.out.println("Flux Positif : " + message_positif); 
        System.out.println("Flux Negatif : " + message_negatif);
        
        // Classement Positif 
        System.out.println("Classement Positif : ");
        Iterator<Voiture> it_positif = promethee.classementPositif.iterator();
        
        System.out.print("[");
        while(it_positif.hasNext()) {
            Voiture voiture = it_positif.next();   
            System.out.print(voiture.getNom() + ",");
        }
        System.out.println("]");
        
        
        // Classement Négatif
        System.out.println("Classement Negatif :");
        Iterator<Voiture> it_negatif = promethee.classementNegatif.iterator();
        
        System.out.print("[");
        while(it_negatif.hasNext()) {
            Voiture voiture = it_negatif.next();
            System.out.print(voiture.getNom() + ",");
        }
        System.out.println("]");
        
        
        return "Output de promethee One. ";
    }
    
    public String generateHtml(PrometheeTwo promethee) {
        return "Output de promethee Two. ";
    }
}
