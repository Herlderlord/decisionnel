/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsense.Modele;

/**
 *
 * @author idolon
 */
public final class Voiture {    
    // TODO : Ajoutez tous les attributs d'une voiture.
    private String nom;
    
    private double prix;
    private double vitesse_max;
    private double conso_moyenne;
    private double dist_freinage;
    private int confort;
    private double volume_coffre;
    private double acceleration;

    public Voiture (String n, double p, double vm, double cm, double df, int c, double vc, double a) {
        setNom(n);
        setPrix(p);
        setVitesse_max(vm);
        setConso_moyenne(cm);
        setDist_freinage(df);
        setConfort(c);
        setVolume_coffre(vc);
        setAcceleration(a);
    }
    
    public Voiture (String nom, String line) {
        this (nom, 
                Double.valueOf(line.split(",")[0]), Double.valueOf(line.split(",")[1]), 
                Double.valueOf(line.split(",")[2]), Double.valueOf(line.split(",")[3]),
                Integer.valueOf(line.split(",")[4]), Double.valueOf(line.split(",")[5]), 
                Double.valueOf(line.split(",")[6]));
    }
    
    public void print() {
        System.out.println("[Nom="+nom+" Prix="+prix+" Vitesse_max="+
                vitesse_max+" Conso_moy="+conso_moyenne+" Dist_freinage="+
                dist_freinage+" Confort="+confort+" Volume_coffre="+
                volume_coffre+" Acceleration="+acceleration);
    }
    
    /**
     * @return the prix
     */
    public double getPrix() {
        return prix;
    }

    /**
     * @param prix the prix to set
     */
    public void setPrix(double prix) {
        this.prix = prix;
    }

    /**
     * @return the vitesse_max
     */
    public double getVitesse_max() {
        return vitesse_max;
    }

    /**
     * @param vitesse_max the vitesse_max to set
     */
    public void setVitesse_max(double vitesse_max) {
        this.vitesse_max = vitesse_max;
    }

    /**
     * @return the conso_moyenne
     */
    public double getConso_moyenne() {
        return conso_moyenne;
    }

    /**
     * @param conso_moyenne the conso_moyenne to set
     */
    public void setConso_moyenne(double conso_moyenne) {
        this.conso_moyenne = conso_moyenne;
    }

    /**
     * @return the dist_freinage
     */
    public double getDist_freinage() {
        return dist_freinage;
    }

    /**
     * @param dist_freinage the dist_freinage to set
     */
    public void setDist_freinage(double dist_freinage) {
        this.dist_freinage = dist_freinage;
    }

    /**
     * @return the confort
     */
    public int getConfort() {
        return confort;
    }

    /**
     * @param confort the confort to set
     */
    public void setConfort(int confort) {
        this.confort = confort;
    }

    /**
     * @return the volume_coffre
     */
    public double getVolume_coffre() {
        return volume_coffre;
    }

    /**
     * @param volume_coffre the volume_coffre to set
     */
    public void setVolume_coffre(double volume_coffre) {
        this.volume_coffre = volume_coffre;
    }

    /**
     * @return the acceleration
     */
    public double getAcceleration() {
        return acceleration;
    }

    /**
     * @param acceleration the acceleration to set
     */
    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
}
