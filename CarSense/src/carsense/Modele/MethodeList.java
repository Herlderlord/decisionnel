/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsense.Modele;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dem_l
 */
public class MethodeList {
    protected List<String> list;
    
    public MethodeList (int t) {
        list = new ArrayList<>(t);
    }
    
    public void addString (String string) {
        list.add(string);
    }
    
    public void print() {
        list.forEach ( (s) -> {
                System.out.print("\t"+s);
        });
    }
}
