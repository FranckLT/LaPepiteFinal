package fr.lapepite.javabean;

import java.util.ArrayList;

public class Designer {
	
	private int id_designer;
	
	private String nom_designer;
	
	private ArrayList<Bijoux> listBijoux;

	public int getId() {
		return id_designer;
	}

	public void setId(int id) {
		this.id_designer = id;
	}

	public String getNom() {
		return nom_designer;
	}

	public void setNom(String nom) {
		this.nom_designer = nom;
	}

	public ArrayList<Bijoux> getListBijoux() {
		return listBijoux;
	}

	public void setListBijoux(ArrayList<Bijoux> listBijoux) {
		this.listBijoux = listBijoux;
	}
	
	public void addBijou(Bijoux bijou) {
		listBijoux.add(bijou);
	}
	

}
