/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsense.Modele;

import carsense.Process.Builder;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author idolon
 */
public class Problem {
    
    public List<Voiture> voitures;
    
    public List<Integer> poids;
    public Integer poidsTotal;
    
    public Map<String, MethodeList> map;
    
    public Problem(List<Voiture> vList, List<Integer> intList, Map<String, MethodeList> map) {
        voitures = vList;
        poids = intList;
        poidsTotal = 0;
        poids.forEach((p) -> {
            poidsTotal += p;
        });
        this.map = map;        
    }
    
    public void print() {
        voitures.forEach((v) -> {
            v.print();
        });
        poids.forEach((p) -> {
            System.out.print (p+"\t");
        });
        System.out.println("Total="+poidsTotal);
        map.forEach( (K,m) -> { 
            System.out.print(K+" : ");
            m.print(); 
            System.out.println();
        });
    }
    
    public static void main(String[]args) throws IOException {
        Problem p = Builder.createProblemVoiture("res/voiture.csv", "res/criteres.csv");
        p.print();
    }
    
}
