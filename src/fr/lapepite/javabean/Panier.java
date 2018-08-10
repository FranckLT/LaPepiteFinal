/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.lapepite.javabean;

import java.util.List;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Panier {
    
    private int id_panier;
    
    private List<LignePanier> listProduit;

    public int getId_panier() {
        return id_panier;
    }

    public void setId_panier(int id_panier) {
        this.id_panier = id_panier;
    }
    
    
    

    public List<LignePanier> getListProduit() {
        return listProduit;
    }

    public void setListProduit(List<LignePanier> listProduit) {
        this.listProduit = listProduit;
    }

    public void addLigneToPanier(LignePanier lignePanier){
        listProduit.add(lignePanier);
    }
}
