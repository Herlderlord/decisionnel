package Process;


import Modele.DataProblem;
import Modele.Problem;
import Modele.Voiture;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
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
    
    public static DataProblem createProblem(String filename) {
        DataProblem result = null; 
        // TODO: Open csv file 
        
        
        /*
            On a peut être des titres à la première ligne. 
            On a peut être des nom à la première colonne. 
            Le reste est les données. 
        */
        // TODO: Next version => Matrix : Max, min, max, min etc .. 
        
        // TODO create problem.
        
        
        return result;
    }
    
    public static Problem createProblemVoiture(String filename) throws IOException {
        List<Voiture> voitureList = new ArrayList<>();
        List<Integer> poidsList = new ArrayList<>();
        
        File file = new File(filename);
        try (FileInputStream fis = new FileInputStream(file); 
                InputStreamReader isr = new InputStreamReader(fis); 
                Scanner scanner = new Scanner(isr)) {
            int i = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains("poids:")) {
                    //TODO traiter le tableau des poids
                    String [] strings = line.replace("poids:","").split(",");
                    for(String s : strings) {
                        poidsList.add (Integer.valueOf(s));
                    }
                }
                else {
                voitureList.add (new Voiture("Voiture"+(++i), line));  
                }
            }
        }
        return new Problem(voitureList, poidsList);
    }
    
    
    
}
