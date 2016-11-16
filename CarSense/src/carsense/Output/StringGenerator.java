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
        int counter = 0;
        
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
        counter = 0;
        while(it_positif.hasNext()) {
            Voiture voiture = it_positif.next();
            if(it_positif.hasNext()) {
                content += voiture.getNom();
                if(fluxPositif[counter] == fluxPositif[counter+1]) 
                    content += " = ";
                else 
                    content += " > ";
            }
            else 
                content += voiture.getNom();
        }
        content += "]\n";
        
        
        // Classement Négatif
        content += "Classement Negatif :\n";
        Iterator<Voiture> it_negatif = promethee.classementNegatif.iterator();
        
        content += "[";
        counter = 0;
        while(it_negatif.hasNext()) {
            Voiture voiture = it_negatif.next();
            if(it_negatif.hasNext()) {
                content += voiture.getNom();    
                if(fluxNegatif[counter] == fluxNegatif[counter+1])
                    content += " = ";
                else 
                    content += " > ";
            }
            else 
                content += voiture.getNom();
        }
        content += "]\n";
        
        return content;
    }
    
    public String generate(PrometheeTwo promethee) {
        
        double[] fluxNet = promethee.fluxNet;
        String content = "";
        
        // Flux net scores
        String message_net = "[";
        
        for(int i = 0; i < fluxNet.length; i++) {
            message_net += fluxNet[i] + " ,";
        }
        message_net += "]";
        
        content += "Flux Net : " + message_net + "\n";
        
        // Classement Net
        content += "Classement Negatif :\n";
        Iterator<Voiture> it_net = promethee.classement.iterator();
        
        content += "[";
        int i = 0; 
        while(it_net.hasNext()) {
            Voiture voiture = it_net.next();
            if(it_net.hasNext()) {
                content += voiture.getNom();
                if(fluxNet[i+1] == fluxNet[i])
                    content += " = ";
                else 
                    content += " > ";
            }
            else 
                content += voiture.getNom();
            i++;
        }
        content += "]\n";
        return content;
    }
}
