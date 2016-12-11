package carsense.Process;


import carsense.Modele.DataProblem;
import carsense.Modele.Problem;
import carsense.Modele.seuil.SeuilIndifferenceList;
import carsense.Modele.seuil.MethodeList;
import carsense.Modele.seuil.ModeDefSeuilList;
import carsense.Modele.seuil.SensPreferenceList;
import carsense.Modele.seuil.SeuilPreferenceList;
import carsense.Modele.seuil.SeuilVetoList;
import carsense.Modele.Voiture;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author idolon
 */
public class Builder {
    
    public static DataProblem createProblem(String filename) throws IOException {
        DataProblem result = null; 
        List<Voiture> voitureList = new ArrayList<>();
        List<Integer> poidsList = new ArrayList<>();
        List<String> attr = new ArrayList<>();
        List<Boolean> isMaxProblemList = new ArrayList<>();
        int i = 0;
        // TODO: Open csv file 
        File file = new File (filename);
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis);
        try ( Scanner scanner = new Scanner(isr)) {
            
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains("poids:")) {
                    //TODO traiter le tableau des poids
                    String [] strings = line.replace("poids:","").split(",");
                    for(String s : strings) {
                        poidsList.add (Integer.valueOf(s));
                    }
                }
                else if (line.contains("attr:")) {
                    String [] strings = line.replace("attr:","").split(",");
                    for(String s : strings) {
                        attr.add (s);
                    }
                }
                else if (line.contains("type:")) {
                    String [] strings = line.replace("type:","").split(",");
                    for(String s : strings) {
                        isMaxProblemList.add(s.equals("max"));
                    }
                }                
                else {
                voitureList.add (new Voiture("Voiture"+(++i), line));  
                }
            }
        }
        
        
        /*
            On a peut être des titres à la première ligne. 
            On a peut être des nom à la première colonne. 
            Le reste est les données. 
        */
        // TODO: Next version => Matrix : Max, min, max, min etc .. 
        
        // TODO create problem.
        
        result = new DataProblem ();
        /*
        //Traiter result.titles
        String [] attrTab = new String [attr.size()];
        for(int j=0;j<attrTab.length;j++) {
            attrTab[j] = attr.get(j);
        }
        result.setTitles(attrTab);
        //Traiter result.values
        int ind = 0;
        for(Voiture v : voitureList) {
            Double [] data = new Double [] {
                (Double)v.getPrix(), (Double)v.getVitesse_max(), 
                (Double)v.getConso_moyenne(), (Double)v.getDist_freinage(), 
                Double.valueOf(v.getConfort()), (Double)v.getVolume_coffre(),
                (Double)v.getAcceleration()};
            result.setValuesAt(ind++, data);
            
        }
        //Traiter result.isMaxProblemList
        Boolean [] isMaxPrbTab = new Boolean [isMaxProblemList.size()];
        for(int j=0;j<isMaxPrbTab.length;j++) {
            isMaxPrbTab[j] = isMaxProblemList.get(j);
        }
        result.setIsMaxProblem(isMaxPrbTab);
        */
        return result;
    }
    
    public static Problem createProblemVoiture(String filename1, String filename2) throws IOException {
        List<Voiture> voitureList = new ArrayList<>();
        List<Integer> poidsList = new ArrayList<>();
        int i = 0;
        
        File file = new File(filename1);
        try (FileInputStream fis = new FileInputStream(file); 
                InputStreamReader isr = new InputStreamReader(fis); 
                Scanner scanner = new Scanner(isr)) {
            
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains("poids:")) {
                    //TODO traiter le tableau des poids
                    String [] strings = line.replace("poids:","").split(",");
                    for(String s : strings) {
                        poidsList.add (Integer.valueOf(s));
                    }
                }
                else if (line.contains("attr:") || line.contains("type:")) {}
                else {
                voitureList.add (new Voiture("Voiture"+(++i), line));  
                }
            }
        }
        
        Map<String, MethodeList> map = new HashMap<>();
        map.put("sens des préférences", new SensPreferenceList(i));
        map.put("mode de définition des seuils", new ModeDefSeuilList(i));
        map.put("seuils d'indifférence", new SeuilIndifferenceList(i));
        map.put("seuils de préférence", new SeuilPreferenceList(i));
        map.put("seuils de veto", new SeuilVetoList(i));
        
        file = new File (filename2); //Ouvre criteres.csv
        try (FileInputStream fis = new FileInputStream(file); 
                InputStreamReader isr = new InputStreamReader(fis); 
                Scanner scanner = new Scanner(isr)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains("preferences:")) {
                    String [] strings = line.replace("preferences:","").split(",");
                    for(String s : strings) {
                        map.get("sens des préférences").addString(s);
                    }
                }
                else if (line.contains("defSeuils:")) {
                    String [] strings = line.replace("defSeuils:","").split(",");
                    for(String s : strings) {
                        map.get("mode de définition des seuils").addString(s);
                    }
                }
                else if (line.contains("seuilIndiff:")) {
                    String [] strings = line.replace("seuilIndiff:","").split(",");
                    for(String s : strings) {
                        map.get("seuils d'indifférence").addString(s);
                    }
                }
                else if (line.contains("seuilPref:")) {
                    String [] strings = line.replace("seuilPref:","").split(",");
                    for(String s : strings) {
                        map.get("seuils de préférence").addString(s);
                    }
                }
                else if (line.contains("seuilVeto:")) {
                    String [] strings = line.replace("seuilVeto:","").split(",");
                    for(String s : strings) {
                        map.get("seuils de veto").addString(s);
                    }
                }
            }
        }
        
        return new Problem(voitureList, poidsList, map);
    }
    
    
    
}
