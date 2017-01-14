/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsense.Output;

import carsense.Methods.Borda;
import carsense.Methods.MethodStrategy;
import carsense.Methods.PrometheeOne;
import carsense.Methods.PrometheeTwo;
import carsense.Modele.EntryData;
import carsense.Modele.Voiture;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author idolon
 */
public class StringGenerator implements OutputGenerator {

    public String title;
    
    @Override
    public String generate(PrometheeOne promethee) {
        // Déclaration du contenu à retourner.
        String content = "";
        int counter = 0;
        
        // Flux positifs et negatifs.
        double[] fluxNegatif = promethee.fluxNegatif; 
        double[] fluxPositif = promethee.fluxPositif;
        
        String message_negatif = ""; 
        String message_positif = "";
        for(int i = 0; i < fluxNegatif.length && i < fluxPositif.length; i++) {
            message_negatif += promethee.problem.data.get(i).name + " : " + fluxNegatif[i] + "\n";
            message_positif += promethee.problem.data.get(i).name + " : " + fluxPositif[i] + "\n";
        }
        message_negatif += ""; 
        message_positif += "";
        
        content += "Flux Positifs : \n" + message_positif + "\n"; 
        content += "Flux Negatifs : \n" + message_negatif + "\n";       
        
        // Classement Positif 
        content += "Classement Positif Générique : \n";
        Iterator<EntryData> it_positif_generic = promethee.classementPositifGeneric.iterator();
        
        content += "[";
        counter = 0;
        while(it_positif_generic.hasNext()) {
            EntryData entry = it_positif_generic.next();
            if(it_positif_generic.hasNext()) {
                content += entry.name;
                if(fluxPositif[counter] == fluxPositif[counter+1]) 
                    content += " = ";
                else 
                    content += " > ";
            }
            else 
                content += entry.name;
            counter ++;
        }
        content += "]\n";
        
        
        // Classement Négatif
        content += "Classement Negatif Générique :\n";
        Iterator<EntryData> it_negatif_generic = promethee.classementNegatifGeneric.iterator();
        
        content += "[";
        counter = 0;
        while(it_negatif_generic.hasNext()) {
            EntryData entry = it_negatif_generic.next();
            if(it_negatif_generic.hasNext()) {
                content += entry.name;    
                if(fluxNegatif[counter] == fluxNegatif[counter+1]) {
                    content += " = ";
                }
                else {
                    content += " > ";
                }
            }
            else {
                content += entry.name;
            }
            counter ++;
        }
        content += "]\n";
        
        
        return content;
    }
    
    @Override
    public String generate(PrometheeTwo promethee) {
        int counter = 0;
        double[] fluxNet = promethee.fluxNet;
        String content = "";
        
        // Flux net scores
        String message_net = "Flux nets : \n";
        for(int i = 0; i < fluxNet.length; i++) {
            message_net += promethee.problem.data.get(i).name + " : " + fluxNet[i] + "\n";
        }
        message_net += "\n";
        
        content += message_net;
        // Classement Net générique 
        content += "Classement Net Générique :\n"; 
        counter = promethee.classementGeneric.size();
        
        content += "[";
        for(EntryData entry : promethee.classementGeneric) {
            content += entry.name;
            if(--counter != 0) {
                content += " > ";
            }
        }
        content += "]";
        return content;
    }
    
    @Override
    public String generate (Borda borda) {
        List<Voiture> voitures = borda.classement;
        
        String content = "Scores finals : \n";
        for(int i = 0; i < borda.finalScores.length; i++) {
            content += borda.classementGeneric.get(i).name + " : " + borda.finalScores[i] + "\n";
        }
        content += "";
        
        content += "\n";
        content += "Liste générique \n"; 
        content += "[";
        for(EntryData entry : borda.classementGeneric) {
            content += entry.name + ", ";
        }
        content += "]";
        
        return content;
    }
    
    public String getTitle() {
        return this.title; 
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
}
