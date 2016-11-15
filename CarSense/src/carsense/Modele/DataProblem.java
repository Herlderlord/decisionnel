package carsense.Modele;

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
     * Title of each column. 
     */
    public String[] titles;
    
    /**
     * Values
     */
    public double[][] values;
    
    /**
     * Each column is max problem or min ? 
     */
    public boolean[] isMaxProblem;
    
    
    /**
     * Init each variable of DataProblem 
     * 
     * @param size_x
     * @param size_y 
     */
    public DataProblem(int size_x, int size_y) {
        this.titles = new String[size_x]; 
        this.values = new double[size_y][size_x];
        this.isMaxProblem = new boolean[size_x];
    }
}
