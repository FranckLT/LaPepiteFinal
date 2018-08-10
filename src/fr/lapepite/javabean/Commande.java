package fr.lapepite.javabean;

import java.sql.Date;

public class Commande {

	private int id_commande;
	
	private Date date_commande;

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
	
	
}
