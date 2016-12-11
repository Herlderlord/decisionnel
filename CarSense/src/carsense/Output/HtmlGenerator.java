/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsense.Output;

import carsense.Output.*;
import carsense.Methods.*;
import carsense.Modele.Voiture;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import java.io.CharArrayWriter;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Voiture.html 
 * Gui : ajout bouton 
 * 
 * @author mathi
 */
public class HtmlGenerator implements OutputGenerator {
    
    private String generateString(HashMap<String, Object> scopes, String mustacheFile) {
        
        // Test Basic Scope 
        scopes.put("fieldsJSON", "[\"classement\", \"nom\", \"vitesseMaximale\", \"prix\", \"truc\", \"Flux_positif\"]");
        scopes.put("title", "Un titre au hasard");
        scopes.put("dataJSON", " {\n" +
"  \"voiture1\": {\n" +
"    \"classement\": 1,\n" +
"    \"nom\": \"voiture1\",\n" +
"    \"vitesseMaximale\": 200,\n" +
"    \"prix\": 10,\n" +
"    \"truc\": 100,\n" +
"    \"Flux_positif\": 10\n" +
"  },\n" +
"  \"voiture2\": {\n" +
"    \"classement\": 2,\n" +
"    \"nom\": \"voiture2\",\n" +
"    \"vitesseMaximale\": 100,\n" +
"    \"prix\": 5,\n" +
"    \"truc\": 80,\n" +
"    \"Flux_positif\": 9\n" +
"  },\n" +
"  \"voiture3\": {\n" +
"    \"classement\": 3,\n" +
"    \"nom\": \"voiture3\",\n" +
"    \"vitesseMaximale\": 300,\n" +
"    \"prix\": 50,\n" +
"    \"truc\": 80,\n" +
"    \"Flux_positif\": 8\n" +
"  },\n" +
"\n" +
"  \"voiture4\": {\n" +
"    \"classement\": 4,\n" +
"    \"nom\": \"voiture4\",\n" +
"    \"vitesseMaximale\": 120,\n" +
"    \"prix\": 20,\n" +
"    \"truc\": 100,\n" +
"    \"Flux_positif\": 7\n" +
"  }\n" +
"}");
        
        
        // Preparing Writer 
        CharArrayWriter writer = new CharArrayWriter();
        String htmlValue = new String();
        
        // Preparing Mustache Variables 
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache = mf.compile(mustacheFile);
        mustache.execute(writer, scopes);
        htmlValue = writer.toString();

        // Return string value
        return htmlValue;
    }
    
    public String generate(PrometheeOne promethee) {
        // Preparings Scope variables
        HashMap<String, Object> scopes = new HashMap<String, Object>();
        scopes.put("name", "Mustache");
        
        // Generating html string value
        return generateString(scopes, "views/html_output_prometheeOne.mustache");
    }
    
    public String generate(PrometheeTwo promethee) {
        return "Output de promethee Two. ";
    }
    
    public String generate(Borda borda) {
        return "Ouput de borda";
    }
}
