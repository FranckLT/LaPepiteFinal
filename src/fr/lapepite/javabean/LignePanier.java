/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.lapepite.javabean;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class LignePanier {
    
    private Bijoux bijoux;
    
    private int quantite_lignepanier;

    public Bijoux getBijoux() {
        return bijoux;
    }

    public void setBijoux(Bijoux bijoux) {
        this.bijoux = bijoux;
    }

    public int getQuantite() {
        return quantite_lignepanier;
    }

    public void setQuantite(int quantite) {
        this.quantite_lignepanier = quantite;
    }
    
    

}
