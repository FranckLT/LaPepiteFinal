package fr.lapepite.javabean;

public class LigneCommande {
	
	private Commande commande;
	
	private Bijoux bijoux;
	
	private int quantite_lignecommande;

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public Bijoux getBijoux() {
		return bijoux;
	}

	public void setBijoux(Bijoux bijoux) {
		this.bijoux = bijoux;
	}

	public int getQuantite_lignecommande() {
		return quantite_lignecommande;
	}

	public void setQuantite_lignecommande(int quantite_lignecommande) {
		this.quantite_lignecommande = quantite_lignecommande;
	}
	
	

}
