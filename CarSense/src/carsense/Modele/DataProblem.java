package carsense.Modele;

import carsense.Process.Builder;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author idolon
 */
public class DataProblem {
    
    /**
     * Data with titles and values 
     */
    public List<Map<String, String>> data;
    public boolean[] isMaxProblem;
    public double[] poids;
    public double[] seuilIndifference;
    public double[] seuilPref;
    public double[] seuilVeto;
    
    
    /**
     * Init each variable of DataProblem 
     * 
     * @param size_x
     * @param size_y 
     */
    public DataProblem() {
        this.data = new LinkedList<Map<String, String>>();
    }
    
    public void print() {
    }

    
    public static void main(String[]args) throws IOException {
        DataProblem dataProblem = Builder.createProblem("res/voiture.csv");
        dataProblem.print();
        
        /*
        
        Comment faire les données de manière à être le plus générique. 
        
        fieldname1, fieldname2, fieldname3 ... 
        value1.1, value1.2, value1.3 ... 
        value2.1, value2.2, value2.3 ...
        
        On récupère les titres et on récupère les valeurs. 
        On doit pouvoir vérifier si on a les titres ou pas à la première ligne
        sinon on génère automatiquement les nouveaux titres.
        
        Le mieux est d'avoir un csv en entrée pour les données. Un truc basique
        
        On se base sur un jeu de données. 
        La définition du problème. 
        */
    }
}
