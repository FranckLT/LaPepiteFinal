/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.lapepite.services;

import fr.lapepite.db.utils.DBBijouxUtils;
import fr.lapepite.javabean.Bijoux;
import fr.lapepite.javabean.Categorie;
import fr.lapepite.javabean.Designer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


public class BijouxServices{


	public List<Bijoux> getAll() throws Exception {
		List<Bijoux> listBijoux = new ArrayList<>();
		try {
			//requête en base 
			listBijoux = DBBijouxUtils.requestSelectAll();
			return listBijoux;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public int getNumberOfBijoux() throws Exception {
		
		List<Bijoux> listBijoux = new ArrayList<>();
		
		listBijoux.addAll(getAll());
		
		return listBijoux.size();
		
	}

	public Bijoux getOneBijoux(HashMap<String, String> parametersList) throws Exception {

		try {

			int idBijoux = Integer.parseInt(parametersList.get("id"));

			Bijoux bijoux = new Bijoux();

			bijoux = DBBijouxUtils.selectOneBijouxById(idBijoux);

			return bijoux;

		} catch (Exception ex) {


		}
		return null;

	}

	public List<Bijoux> getBijouxByDesigner(HashMap<String, String> parametersList) throws SQLException, Exception {

		List<Bijoux> bijouxList = new ArrayList<>();

		int idDesigner = Integer.parseInt(parametersList.get("id"));

		bijouxList = DBBijouxUtils.selectBijouxByDesigner(idDesigner);

		return bijouxList;


	}



	public void addOne(HashMap<String, String> parametersList) throws NumberFormatException, Exception {

		if (verifForm(parametersList)) {

			if (!verifIfNomAndRefBijouxAlreadyUsed(parametersList)) {

				//récup de tout les parametre 
				String nomBijoux = parametersList.get("nomBijoux");
				String refBijoux = parametersList.get("refBijoux");
				int prixBijoux = Integer.parseInt(parametersList.get("prixBijoux"));
				int stockBijoux = Integer.parseInt(parametersList.get("stockBijoux"));
				String description = parametersList.get("descriptionBijoux");
				int idDesignerBijoux = Integer.parseInt(parametersList.get("designerBijoux"));
				int idCategorieBijoux = Integer.parseInt(parametersList.get("categorieBijoux"));

				//initialisation objets
				Bijoux bijoux = new Bijoux();
				Designer designer = new Designer();
				Categorie categorie = new Categorie();

				//set des objets
				designer.setId_designer(idDesignerBijoux);
				categorie.setId_categorie(idCategorieBijoux);

				bijoux
				.setNom_bijoux(nomBijoux)
				.setRef_bijoux(refBijoux)
				.setPrix_bijoux(prixBijoux)
				.setStock_bijoux(stockBijoux)
				.setDescription_bijoux(description)
				.setDesigner(designer)
				.setCategorie(categorie);


				//ajout à la BDD
				DBBijouxUtils.insertBijoux(bijoux);

			}
		}

	}

	public void deleteBijoux(HashMap<String, String> parametersList) throws Exception {

		int idBijoux = Integer.parseInt(parametersList.get("idToDelete"));

		Bijoux bijoux = new Bijoux();

		bijoux.setId_bijoux(idBijoux);

		try {
			DBBijouxUtils.deleteBijoux(bijoux);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}



	}


	public void updateOne(HashMap<String, String> parametersList) throws Exception {

		int idBijoux = Integer.parseInt(parametersList.get("id"));
		String nomBijoux = parametersList.get("nomBijoux");
		String refBijoux = parametersList.get("refBijoux");
		int prixBijoux = Integer.parseInt(parametersList.get("prixBijoux"));
		int stockBijoux = Integer.parseInt(parametersList.get("stockBijoux"));
		String description = parametersList.get("descriptionBijoux");
		int idDesignerBijoux = Integer.parseInt(parametersList.get("designerBijoux"));
		int idCategorieBijoux = Integer.parseInt(parametersList.get("categorieBijoux"));

		//initialisation objets
		Bijoux bijoux = new Bijoux();
		Designer designer = new Designer();
		Categorie categorie = new Categorie();

		//set des objets
		designer.setId_designer(idDesignerBijoux);
		categorie.setId_categorie(idCategorieBijoux);

		bijoux.setId_bijoux(idBijoux)
		.setNom_bijoux(nomBijoux)
		.setRef_bijoux(refBijoux)
		.setPrix_bijoux(prixBijoux)
		.setStock_bijoux(stockBijoux)
		.setDescription_bijoux(description)
		.setDesigner(designer)
		.setCategorie(categorie);

		try {

			if (!verifIfNomAndRefBijouxAlreadyUsed(parametersList)) {

				//ajout à la BDD
				DBBijouxUtils.updateBijoux(bijoux);

			} 

		} catch (Exception e) {

			throw new Exception(e.getMessage());
		}



	}


	public static boolean verifIfNomAndRefBijouxAlreadyUsed(HashMap<String, String> parametersList) throws Exception{

		String nomBijoux = parametersList.get("nomBijoux");

		String refBijoux = parametersList.get("refBijoux");

		List<Bijoux> listNomBijoux = new ArrayList<>();

		listNomBijoux.addAll(DBBijouxUtils.requestSelectAll());

		for (Bijoux bijoux : listNomBijoux) {
			if (bijoux.getNom_bijoux().equals(nomBijoux)) {
				System.out.println("Nom deja utilisé");
				return true;
			}
			if (bijoux.getRef_bijoux().equals(refBijoux)) {
				System.out.println("Ref deja utilisé");
				return true;
			}
		}

		return false;

	}

	public static boolean verifForm(HashMap<String, String> parametersList) throws Exception {

		Set<String> keysList = parametersList.keySet();

		for (String parameterName : keysList) {

			if ("".equals(parametersList.get(parameterName))) {

				throw new Exception("Le champ " +parameterName+" ne peut pas être nul.");

			}

		}

		return true;
	}

}
