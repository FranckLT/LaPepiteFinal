/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.lapepite.services;

import fr.lapepite.db.utils.DBPanierUtils;
import fr.lapepite.javabean.Bijoux;
import fr.lapepite.javabean.LignePanier;
import fr.lapepite.javabean.Panier;
import fr.lapepite.javabean.Utilisateur;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class PanierServices {

	public void insertPanier(Panier panier){

		try {

			//Enregistrement en BD du panier
			DBPanierUtils.insertPanier(panier);

		} catch (Exception ex) {
			Logger.getLogger(PanierServices.class.getName()).log(Level.SEVERE, null, ex);
		}


	}


	public Utilisateur addBijouxToPanier(HashMap<String, String> parametersList, Utilisateur utilisateur) throws Exception{

		//variables
		Panier panier = new Panier();
		panier = utilisateur.getPanier();
		List<LignePanier> listLignePanier = new ArrayList<>();
		listLignePanier = panier.getListProduit();
		Bijoux bijoux = new Bijoux();
		BijouxServices bijouxServices = new BijouxServices();
		LignePanier lignePanierToAdd = new LignePanier();   
		int nbrBijouxCommandées = Integer.parseInt(parametersList.get("numberOfProduct"));
		LignePanierServices lignePanierServices = new LignePanierServices();

		//Création Bijoux
		bijoux = bijouxServices.getOneBijoux(parametersList);

		if (listLignePanier.isEmpty()) {
			//Création LignePanier

			lignePanierToAdd = lignePanierServices.createOneLigne_panier(bijoux, nbrBijouxCommandées);

			//Ajout de la ligne au panier


			panier.addLigneToPanier(lignePanierToAdd);

			System.out.println("Ajout ligne");
		} else {

			for (LignePanier lignePanier : listLignePanier) {

				if (bijoux.getId_bijoux() == lignePanier.getBijoux().getId_bijoux()) {

					lignePanierServices.addXtoQuantity(lignePanier, nbrBijouxCommandées);

					System.out.println("Modifié qte");

				} else {

					lignePanierToAdd = lignePanierServices.createOneLigne_panier(bijoux, nbrBijouxCommandées);

					//Ajout de la ligne au panier


					panier.addLigneToPanier(lignePanierToAdd);

					System.out.println("Ajout ligne");

				}

			}
			
		}
		
		panier.updateTotal_panier();

		return utilisateur;
	}

}
