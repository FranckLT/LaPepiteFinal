package fr.lapepite.javabean;

import java.util.ArrayList;

public class Designer {
	
	private int id_designer;
	
	private String nom_designer;
	
	private String description_designer;
	
	private ArrayList<Bijoux> listBijoux;

	public int getId_designer() {
		return id_designer;
	}

	public void setId_designer(int id_designer) {
		this.id_designer = id_designer;
	}

	public String getNom_designer() {
		return nom_designer;
	}

	public void setNom_designer(String nom_designer) {
		this.nom_designer = nom_designer;
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

	public String getDescription_designer() {
		return description_designer;
	}

	public void setDescription_designer(String description_designer) {
		this.description_designer = description_designer;
	}
	
	
	

}
