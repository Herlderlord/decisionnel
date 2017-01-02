/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsense.Output;

import carsense.Methods.Borda;
import carsense.Methods.PrometheeOne;
import carsense.Methods.PrometheeTwo;

/**
 *
 * @author idolon
 */
public interface OutputGenerator {
    
    public String generate(PrometheeOne promethee);
    
    public String generate(PrometheeTwo promethee);
    
    public String generate(Borda borda);
    
    public String getTitle();
    
    public void setTitle(String title);
}
