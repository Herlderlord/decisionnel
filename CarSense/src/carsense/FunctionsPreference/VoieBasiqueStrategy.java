/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsense.FunctionsPreference;

import carsense.Modele.DataProblem;
import carsense.Modele.EntryData;
import carsense.Modele.Problem;
import carsense.Modele.Voiture;
import java.util.Iterator;

/**
 *
 * @author mathi
 */
public class VoieBasiqueStrategy implements FunctionPreferenceStrategy {
    
    
    @Override
    public double[][] calculPreference(Problem problem) {
        
      
        
        Voiture voiture_ref = null; 
        Iterator<Voiture> voiture_ref_it = problem.voitures.iterator();
        int nbVoitures = problem.voitures.size();
        int i_ref = 0;
        
        
        double[][] result = new double[nbVoitures][nbVoitures];
        
        
        while(voiture_ref_it.hasNext()) {
            
            voiture_ref = voiture_ref_it.next();
            Voiture voiture_compared = null;
            Iterator<Voiture> voiture_compared_it = problem.voitures.iterator();
            int i_compared = 0;
            while(voiture_compared_it.hasNext()) {
                voiture_compared = voiture_compared_it.next();
                
                // Process only if not the same !
                if(voiture_compared != voiture_ref) {
                    result[i_compared][i_ref] = 0;
                    
                    
                    // Si la référence a son champs meilleur que le comparé.
                    // Prix 
                    if(voiture_ref.getPrix() > voiture_compared.getPrix())
                        result[i_compared][i_ref] += (double)problem.poids.get(0) / (double)problem.poidsTotal;
                    
                    // Vitesse Max
                    if(voiture_ref.getVitesse_max() > voiture_compared.getVitesse_max())
                        result[i_compared][i_ref] += (double)problem.poids.get(1) / (double)problem.poidsTotal;
                    
                    // Consommation Moyenne 
                    if(voiture_ref.getConso_moyenne() > voiture_compared.getConso_moyenne())
                        result[i_compared][i_ref] += (double)problem.poids.get(2) / (double)problem.poidsTotal;
                    
                    // Distance de freinage 
                    if(voiture_ref.getDist_freinage()> voiture_compared.getDist_freinage())
                        result[i_compared][i_ref] += (double)problem.poids.get(3) / (double)problem.poidsTotal;
                    
                    // Confort 
                    if(voiture_ref.getConfort() > voiture_compared.getConfort())
                        result[i_compared][i_ref] += (double)problem.poids.get(4) / (double)problem.poidsTotal;
                    
                    // Volume du coffre 
                    if(voiture_ref.getVolume_coffre() > voiture_compared.getVolume_coffre())
                        result[i_compared][i_ref] += (double)problem.poids.get(5) / (double)problem.poidsTotal;
                    
                    // Accélération 
                    if(voiture_ref.getAcceleration() > voiture_compared.getAcceleration())
                        result[i_compared][i_ref] += (double)problem.poids.get(6) / (double)problem.poidsTotal;
                } 
                else {
                    result[i_ref][i_compared] = 0;
                }
                i_compared ++;
            }
            i_ref++;
        }
        return result;
    }
    
    @Override
    public double[][] calculPreference(DataProblem problem) {
        // Get some information 
        EntryData entry_ref = null; 
        Iterator<EntryData> entry_ref_it = problem.data.iterator();
        int nbEntries = problem.data.size();
        int i_ref = 0;
        
        // Creating result array
        double[][] result = new double[nbEntries][nbEntries];
        
        // Get each line
        while(entry_ref_it.hasNext()) {
            entry_ref = entry_ref_it.next();
            EntryData entry_compared = null;
            Iterator<EntryData> entry_compared_it = problem.data.iterator();
            int i_compared = 0;
            
            // Compared whith each other line
            while(entry_compared_it.hasNext()) {
                entry_compared = entry_compared_it.next();
                
                // if not the same
                if(entry_compared != entry_ref) {
                    result[i_compared][i_ref] = 0;
                    
                }
                i_compared ++;
                
            }
            
            i_ref ++;
        }
                // Parcourir chaque valeur de la ligne pour comparer avec l'autre
        return null;
    }
}
