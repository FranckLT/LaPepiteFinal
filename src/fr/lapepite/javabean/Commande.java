package fr.lapepite.javabean;

import java.sql.Date;
import java.util.List;

public class Commande {

	private int id_commande;
	
	private Date date_commande;
	
	private double totalHT_commande;
	
	private double TVA_commande;
	
	private double totalTTC_commande;
	
	private boolean cheked;
	
	private Utilisateur utilisateur;
	
	private List<LigneCommande> listLigne_commande;
	

	public int getId_commande() {
		return id_commande;
	}

	public void setId_commande(int id_commande) {
		this.id_commande = id_commande;
	}

	public Date getDate_commande() {
		return date_commande;
	}

	public void setDate_commande(Date date_commande) {
		this.date_commande = date_commande;
	}

	public double getTotalHT_commande() {
		return totalHT_commande;
	}

	public void setTotalHT_commande(double totalHT_commande) {
		this.totalHT_commande = totalHT_commande;
	}

	public double getTVA_commande() {
		return TVA_commande;
	}

	public void setTVA_commande(double TVA_commande) {
		this.TVA_commande = TVA_commande;
	}

	public double getTotalTTC_commande() {
		return totalTTC_commande;
	}

	public void setTotalTTC_commande(double totalTTC_commande) {
		this.totalTTC_commande = totalTTC_commande;
	}

	public List<LigneCommande> getListLigne_commande() {
		return listLigne_commande;
	}

	public void setListLigne_commande(List<LigneCommande> listLigne_commande) {
		this.listLigne_commande = listLigne_commande;
	}

	public void addOneLigneCommande(LigneCommande ligneCommande) {
		listLigne_commande.add(ligneCommande);
	}

	public boolean isCheked() {
		return cheked;
	}

	public void setCheked(boolean cheked) {
		this.cheked = cheked;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	
	
}
