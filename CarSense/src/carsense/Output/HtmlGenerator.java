/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsense.Output;

import carsense.Output.*;
import carsense.Methods.*;
import carsense.Modele.EntryData;
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
    
    public String title;
    
    private String generateString(HashMap<String, Object> scopes, String mustacheFile) {
       
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
        
        // Add field Scope
        String jsObjectFields = "[\"nom\", \"classementPositif\",\"classementNegatif\",";
        int counter = promethee.fields.size();
        for(String field : promethee.fields) {
            counter --;
            jsObjectFields += "\"" + field + "\"";
            if(counter != 0) {
                jsObjectFields += ", ";
            }
        }
        jsObjectFields += "]";
        
        // Data JSON
        String jsObjectData = "{";
        counter = promethee.classementPositifGeneric.size();
        for(EntryData entry : promethee.classementPositifGeneric) {
            counter --;
            jsObjectData += "\"" + entry.name + "\":{" 
                    + "classementPositif:" + (promethee.classementPositifGeneric.size() - counter) + ","
                    + "classementNegatif:" + ((promethee.classementNegatifGeneric.indexOf(entry)) + 1) + ","
                    + "nom:\"" + entry.name + "\",";
            int subCounter = promethee.fields.size();
            for(String field : promethee.fields) {
                subCounter --;
                jsObjectData += field + ":" + entry.data.get(field);
                if(subCounter != 0) {
                    jsObjectData += ", ";
                }
            }
            jsObjectData += "}";
            if(counter != 0) {
                jsObjectData += ", ";
            }
        }
        jsObjectData += "}";
        
        scopes.put("fieldsJSON", jsObjectFields);
        scopes.put("dataJSON", jsObjectData);
        
        if(this.getTitle() == null) 
            scopes.put("title", "Données");
        else 
            scopes.put("title", this.getTitle());
        
        // Generating html string value
        return generateString(scopes, "views/html_output_prometheeOne.mustache");
    }
    
    public String generate(PrometheeTwo promethee) {
        
        // Preparings Scope variables
        HashMap<String, Object> scopes = new HashMap<String, Object>();
        scopes.put("name", "Mustache");
        
        // Add field Scope
        String jsObjectFields = "[\"nom\", \"classement\",";
        int counter = promethee.fields.size();
        for(String field : promethee.fields) {
            counter --;
            jsObjectFields += "\"" + field + "\"";
            if(counter != 0) {
                jsObjectFields += ", ";
            }
        }
        jsObjectFields += "]";
        
        // Data JSON
        String jsObjectData = "{";
        counter = promethee.classementGeneric.size();
        for(EntryData entry : promethee.classementGeneric) {
            counter --;
            jsObjectData += "\"" + entry.name + "\":{" 
                    + "classement:" + (promethee.classementGeneric.size() - counter) + ","
                    + "nom:\"" + entry.name + "\",";
            int subCounter = promethee.fields.size();
            for(String field : promethee.fields) {
                subCounter --;
                jsObjectData += field + ":" + entry.data.get(field);
                if(subCounter != 0) {
                    jsObjectData += ", ";
                }
            }
            jsObjectData += "}";
            if(counter != 0) {
                jsObjectData += ", ";
            }
        }
        jsObjectData += "}";
        
        scopes.put("fieldsJSON", jsObjectFields);
        scopes.put("dataJSON", jsObjectData);
        if(this.getTitle() == null) 
            scopes.put("title", "Données");
        else 
            scopes.put("title", this.getTitle());
        
        
        // Generating html string value
        return generateString(scopes, "views/html_output_prometheeOne.mustache");
    }
    
    public String generate(Borda borda) {
        
        // Preparings Scope variables
        HashMap<String, Object> scopes = new HashMap<String, Object>();
        scopes.put("name", "Mustache");
        
        // Add field Scope
        String jsObjectFields = "[\"nom\", \"classement\",";
        int counter = borda.fields.size();
        for(String field : borda.fields) {
            counter --;
            jsObjectFields += "\"" + field + "\"";
            if(counter != 0) {
                jsObjectFields += ", ";
            }
        }
        jsObjectFields += "]";
        
        
        
        // Data JSON
        String jsObjectData = "{";
        counter = borda.classementGeneric.size();
        for(EntryData entry : borda.classementGeneric) {
            counter --;
            jsObjectData += "\"" + entry.name + "\":{" 
                    + "classement:" + (borda.classementGeneric.size() - counter) + ","
                    + "nom:\"" + entry.name + "\",";
            int subCounter = borda.fields.size();
            for(String field : borda.fields) {
                subCounter --;
                jsObjectData += field + ":" + entry.data.get(field);
                if(subCounter != 0) {
                    jsObjectData += ", ";
                }
            }
            jsObjectData += "}";
            if(counter != 0) {
                jsObjectData += ", ";
            }
        }
        jsObjectData += "}";
        
        scopes.put("fieldsJSON", jsObjectFields);
        scopes.put("dataJSON", jsObjectData);
        if(this.getTitle() == null) 
            scopes.put("title", "Données");
        else 
            scopes.put("title", this.getTitle());
        
        
        // Generating html string value
        return generateString(scopes, "views/html_output_prometheeOne.mustache");
    }
    
    @Override 
    public String getTitle() {
        return this.title; 
    }
    
    @Override 
    public void setTitle(String title) {
        this.title = title;
    }
}
