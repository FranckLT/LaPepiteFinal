/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.lapepite.javabean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Panier {
    
    private int id_panier;
    
    private List<LignePanier> listProduit;
    
    private int total_panier;
    
    private double tva_panier;
    
    public Panier() {
		
    	listProduit = new ArrayList<>();
    	
	}

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
        
        updateTotal_panier();
        
    }
    
    public void removeLignePanier(LignePanier lignePanier) {
    	
    	listProduit.remove(lignePanier);
    	
    	updateTotal_panier();
		
	}
    
    public void updateTotal_panier() {
    	
    	int total = 0;
		
    	for (LignePanier lignePanier : listProduit) {
			
    		total += lignePanier.getQuantite_lignepanier() * lignePanier.getBijoux().getPrix_bijoux();
    		
		}
    	
    	total_panier = total;
    	
    	updateTva();
    	
	}

	public int getTotal_panier() {
		return total_panier;
	}

	public void setTotal_panier(int total_panier) {
		this.total_panier = total_panier;
	}
    
    public void emptyPanier() {
    	
    	listProduit = new ArrayList<>();
    	
	}

	public double getTva_panier() {
		return tva_panier;
	}

	public void setTva_panier(double tva_panier) {
		this.tva_panier = tva_panier;
	}
    
    public void updateTva() {
    	BigDecimal bigDecimal = new BigDecimal(total_panier*0.2);
		bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_DOWN);
		double tva = bigDecimal.doubleValue();
		tva_panier = tva;
		
	}
    
}
