/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author idolon
 */
public class MapUtil {
    
    public static <K, V extends Comparable<? super V>> Map<K, V>
            sortByValue( Map<K, V> map, boolean croissant) {
                
                // Creation du liste map entry.
                List<Map.Entry<K, V>> list = 
                        new LinkedList<Map.Entry<K, V>>(map.entrySet());
                
                
                
                // Sorting par rapport aux valeurs V.
                Collections.sort( list, new Comparator<Map.Entry<K, V>>() {
                   public int compare( Map.Entry<K, V> o1, Map.Entry<K,V> o2) {
                       if(croissant == true) {
                        return(o1.getValue().compareTo(o2.getValue()));   
                       } else {
                        return(o2.getValue().compareTo(o1.getValue()));
                       }
                       
                   } 
                           
                });
                
                
                // On recrée la map une fois triée.
                Map<K, V> result = new LinkedHashMap<K, V>();
                for(Map.Entry<K, V> entry : list) {
                    result.put(entry.getKey(), entry.getValue());
                }
                return result;
            }
}
