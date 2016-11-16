package carsense.Modele;

import carsense.Process.Builder;
import java.io.IOException;

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
    private String[] titles;
    
    /**
     * Values
     */
    private Double[][] values;
    
    /**
     * Each column is max problem or min ? 
     */
    private Boolean[] isMaxProblem;
    
    
    /**
     * Init each variable of DataProblem 
     * 
     * @param size_x
     * @param size_y 
     */
    public DataProblem(int size_x, int size_y) {
        this.titles = new String[size_x]; 
        this.values = new Double[size_x][size_y];
        this.isMaxProblem = new Boolean[size_x];
    }
    
    public void print() {
        for(int i=0;i<titles.length; i++) {
            System.out.print("\t\t"+titles[i]+", "+(isMaxProblem[i]?"Maximiser":"minimiser"));
        }
        System.out.println();
        for(Double [] dt : values) {
            for (Double d : dt ) {
                System.out.print("\t\t\t"+d);
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public static void main(String[]args) throws IOException {
        DataProblem dataProblem = Builder.createProblem("res/voiture.csv");
        dataProblem.print();
    }

    /**
     * @return the titles
     */
    public String[] getTitles() {
        return titles;
    }

    /**
     * @param titles the titles to set
     */
    public void setTitles(String[] titles) {
        this.titles = titles;
    }

    /**
     * @return the values
     */
    public Double[][] getValues() {
        return values;
    }

    /**
     * @param values the values to set
     */
    public void setValues(Double[][] values) {
        this.values = values;
    }
    
    public void setValuesAt(int ind, Double[] values) {
        this.values [ind] = values;
    }

    /**
     * @return the isMaxProblem
     */
    public Boolean[] getIsMaxProblem() {
        return isMaxProblem;
    }
    
    public boolean getIsMaxProblemAt(int ind) {
        return isMaxProblem[ind];
    }

    /**
     * @param isMaxProblem the isMaxProblem to set
     */
    public void setIsMaxProblem(Boolean[] isMaxProblem) {
        this.isMaxProblem = isMaxProblem;
    }
    
    public void setIsMaxProblemAt(int ind, Boolean isMaxProblem) {
        this.isMaxProblem [ind] = isMaxProblem;
    }
}
