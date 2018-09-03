package fr.lapepite.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.lapepite.db.utils.DBLigneCommandeUtils;
import fr.lapepite.javabean.Commande;
import fr.lapepite.javabean.LigneCommande;
import fr.lapepite.javabean.LignePanier;

public class LigneCommandeServices {
	
	public LigneCommande createLigneCommande(LignePanier lignePanier) {
		
		LigneCommande ligneCommande = new LigneCommande();
		
		ligneCommande.setBijoux(lignePanier.getBijoux());
		ligneCommande.setQuantite_lignecommande(lignePanier.getQuantite_lignepanier());
		
		return ligneCommande;
		}
	
	public void insertLigneCommande(Commande commande) throws Exception {
		
		for (LigneCommande ligneCommande : commande.getListLigne_commande()) {
			DBLigneCommandeUtils.insertLigneCommande(commande, ligneCommande);
		}
		
	}
	
	public List<LigneCommande> getAllLigneCommandeForOneCommande(HashMap<String, String> parametersList) throws Exception {
		
		List<LigneCommande> ligneCommandesList = new ArrayList<>();
		
		int idCommande = Integer.parseInt(parametersList.get("id"));
		
		ligneCommandesList.addAll(DBLigneCommandeUtils.selectAllLigneCommandeForOneCommande(idCommande));
		
		return ligneCommandesList;
		
	}

}
