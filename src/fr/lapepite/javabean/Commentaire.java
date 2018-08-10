package fr.lapepite.javabean;

import java.sql.Date;

public class Commentaire {

	private int id_commentaire;
	
	private String texte_commentaire;
	
	private Date date_commentaire;

	public int getId_commentaire() {
		return id_commentaire;
	}

	public void setId_commentaire(int id_commentaire) {
		this.id_commentaire = id_commentaire;
	}

	public String getTexte_commentaire() {
		return texte_commentaire;
	}

	public void setTexte_commentaire(String texte_commentaire) {
		this.texte_commentaire = texte_commentaire;
	}

	public Date getDate_commentaire() {
		return date_commentaire;
	}

	public void setDate_commentaire(Date date_commentaire) {
		this.date_commentaire = date_commentaire;
	}
	
	
}
