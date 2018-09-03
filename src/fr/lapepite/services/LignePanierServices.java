package fr.lapepite.services;

import java.util.ArrayList;
import fr.lapepite.javabean.Bijoux;
import fr.lapepite.javabean.LignePanier;
import fr.lapepite.javabean.Panier;
import fr.lapepite.javabean.Utilisateur;

public class LignePanierServices {

	public LignePanier createOneLigne_panier(Bijoux bijoux, int nbrBijouxCommandés) {

		LignePanier lignePanier = new LignePanier();       
		lignePanier.setBijoux(bijoux);
		lignePanier.setQuantite_lignepanier(nbrBijouxCommandés);
		
		return lignePanier;

	}
	
	public void addXtoQuantity(LignePanier lignePanier, int quantityToAdd ) {
		
		lignePanier.setQuantite_lignepanier(lignePanier.getQuantite_lignepanier() + quantityToAdd);
		
	}

	public Utilisateur addOrDropOneToQuantity(Utilisateur utilisateur, boolean b, int idBijoux) {

		Panier panier = utilisateur.getPanier();

		ArrayList<LignePanier> listLignePanier =  (ArrayList<LignePanier>) panier.getListProduit();
		
		LignePanier lignePanierToDelete = null;

		for (LignePanier lignePanier : listLignePanier) {

			if (lignePanier.getBijoux().getId_bijoux() == idBijoux) {

				if (b) {

					lignePanier.addOne();
					
					panier.updateTotal_panier();

				} else {

					if (lignePanier.getQuantite_lignepanier()==1) {

						lignePanierToDelete = lignePanier;

					} else {

						lignePanier.dropOne();
						
						panier.updateTotal_panier();

					}

				}

			}

		}
		
		if (lignePanierToDelete != null) {
			panier.removeLignePanier(lignePanierToDelete);
		}

		return utilisateur;

	}

}
