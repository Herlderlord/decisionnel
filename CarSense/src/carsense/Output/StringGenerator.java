/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsense.Output;

import carsense.Methods.PrometheeOne;
import carsense.Methods.PrometheeTwo;
import carsense.Modele.Voiture;
import java.util.Iterator;

/**
 *
 * @author idolon
 */
public class StringGenerator implements OutputGenerator {
    
    
    public String generate(PrometheeOne promethee) {
        // Déclaration du contenu à retourner.
        String content = "";
        
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
        
        content += "Flux Positif : " + message_positif + "\n"; 
        content += "Flux Negatif : " + message_negatif + "\n";
        
        // Classement Positif 
        content += "Classement Positif : \n";
        Iterator<Voiture> it_positif = promethee.classementPositif.iterator();
        
        content += "[";
        while(it_positif.hasNext()) {
            Voiture voiture = it_positif.next();
            if(it_positif.hasNext())
                content += voiture.getNom() + ", ";
            else 
                content += voiture.getNom();
        }
        content += "]\n";
        
        
        // Classement Négatif
        content += "Classement Negatif :\n";
        Iterator<Voiture> it_negatif = promethee.classementNegatif.iterator();
        
        content += "[";
        while(it_negatif.hasNext()) {
            Voiture voiture = it_negatif.next();
            if(it_negatif.hasNext())
                content += voiture.getNom() + ", ";
            else 
                content += voiture.getNom();
        }
        content += "]\n";
        
        return content;
    }
    
    public String generate(PrometheeTwo promethee) {
        return "";
    }
}
