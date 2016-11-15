/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsense.Modele;

import carsense.Process.Builder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author idolon
 */
public class Problem {
    
    public List<Voiture> voitures;
    public List<Integer> poids;
    public Integer poidsTotal;
    
    public Problem(List<Voiture> vList, List<Integer> intList) {
        voitures = vList;
        poids = intList;
        poidsTotal = 0;
        poids.forEach((p) -> {
            poidsTotal += p;
        });
    }
    
    public void print() {
        voitures.forEach((v) -> {
            v.print();
        });
        poids.forEach((p) -> {
            System.out.print (p+"\t");
        });
        System.out.println("Total="+poidsTotal);
    }
    
    public static void main(String[]args) throws IOException {
        Problem p = Builder.createProblemVoiture("res/voiture.csv");
        p.print();
    }
    
    
}
