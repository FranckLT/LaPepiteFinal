package fr.lapepite.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.lapepite.db.utils.DBCommandeUtils;
import fr.lapepite.javabean.Commande;
import fr.lapepite.javabean.LigneCommande;
import fr.lapepite.javabean.LignePanier;
import fr.lapepite.javabean.Panier;
import fr.lapepite.javabean.Utilisateur;

public class CommandeServices {

	public Utilisateur createCommande(Utilisateur utilisateur, HashMap<String, String> parametersList) throws Exception {

		Panier panier = utilisateur.getPanier();
		Commande commande = new Commande();
		LigneCommandeServices ligneCommandeServices = new LigneCommandeServices();
		LigneCommande ligneCommande = new LigneCommande();
		ArrayList<LigneCommande> listLigne_commande = new ArrayList<>();

		try {

			commande.setListLigne_commande(listLigne_commande);

			List<LignePanier> listLignePanier = panier.getListProduit();

			// on trnsforme les ligne_panier en lignes commandes
			for (LignePanier lignePanier : listLignePanier) {
				ligneCommande = ligneCommandeServices.createLigneCommande(lignePanier);
				listLigne_commande.add(ligneCommande);
			}

			// calcul TVA arrondi à 2 chiffres
			BigDecimal bigDecimal = new BigDecimal(panier.getTotal_panier()*0.2);
			bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_DOWN);
			double tva = bigDecimal.doubleValue();

			//set des total de la commande
			commande.setTotalHT_commande(panier.getTotal_panier());
			commande.setTVA_commande(tva);
			commande.setTotalTTC_commande(commande.getTotalHT_commande() + commande.getTVA_commande());

			// enregistrement en bdd de la commande
			DBCommandeUtils.insertCommande(commande, utilisateur);

			// récupération de la commande pour récup son id
			commande = DBCommandeUtils.selectLastCommande(utilisateur);

			commande.setListLigne_commande(listLigne_commande);

			// on enregistre toutes les ligne de commandes 
			ligneCommandeServices.insertLigneCommande(commande);

			panier.emptyPanier();

			panier.updateTotal_panier();

			return utilisateur;
			
		} catch (Exception e) {

			throw new Exception(e.getMessage());

		}

	}
	
	public Commande getOneCommande(HashMap<String, String> parametersList) throws Exception {
		
		Commande commande;
		
		int idCommande = Integer.parseInt(parametersList.get("id"));
		
		commande = DBCommandeUtils.selectCommandeById(idCommande);
		
		return commande;
		
	}
	
	public List<Commande> getAllCommandes() throws Exception {
		
		ArrayList<Commande> commandesList = new ArrayList<>();
		
		commandesList.addAll(DBCommandeUtils.selectAllCommandes());
		
		return commandesList;
		
	}
	
	public int getNumberOfCommandes() throws Exception {
		
		ArrayList<Commande> commandesList = new ArrayList<>();
		
		commandesList.addAll(getAllCommandes());
		
		return commandesList.size();
		
	}

}
