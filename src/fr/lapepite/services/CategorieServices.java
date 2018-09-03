/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.lapepite.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import fr.lapepite.db.utils.DBCategorieUtils;
import fr.lapepite.javabean.Categorie;


public class CategorieServices {

	public ArrayList<Categorie> getAllCategories() throws Exception {

		ArrayList<Categorie> categoriesList = new ArrayList<>();

		try {
			categoriesList.addAll(DBCategorieUtils.selectAllCategories());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}



		return categoriesList;
	}

	public Categorie getOneCategory (HashMap<String, String> parametersList) throws Exception {

		Categorie categorie = new Categorie();

		try {
			System.out.println("salut");
			categorie = DBCategorieUtils.selectOneCategoryById(Integer.parseInt(parametersList.get("id")));
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return categorie;

	}

	public void updateOneCategory(HashMap<String, String> parametersList) throws Exception {

		try {

			Categorie categorie = new Categorie();

			int id_categorie = Integer.parseInt(parametersList.get("id"));

			String nom_categorie = parametersList.get("nomCategorie");

			categorie.setId_categorie(id_categorie);
			categorie.setNom_categorie(nom_categorie);

			DBCategorieUtils.updateCategory(categorie);

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

	public void addOneCategory(HashMap<String, String> parametersList) throws Exception {

		try {
			if (verifForm(parametersList)) {

				if (!verifIfNomCategoryIsAlreadyUsed(parametersList)) {

					Categorie categorie = new Categorie();

					categorie.setNom_categorie(parametersList.get("nomCategorie"));

					DBCategorieUtils.insertCategory(categorie);

				}

			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}



	}

	public void deleteOneCategory(HashMap<String, String> parametersList) throws Exception {

		Categorie categorie = new Categorie();

		categorie.setId_categorie(Integer.parseInt(parametersList.get("idToDelete")));

		try {
			DBCategorieUtils.deleteCategory(categorie);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

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

	public static boolean verifIfNomCategoryIsAlreadyUsed(HashMap<String, String> parametersList) throws Exception{

		String nomCategorie = parametersList.get("nomCategorie");

		List<Categorie> listCategories = new ArrayList<>();

		listCategories.addAll(DBCategorieUtils.selectAllCategories());

		for (Categorie categorie : listCategories) {
			if (categorie.getNom_categorie().equals(nomCategorie)) {

				throw new Exception("Le nom est deja utilisé.");
			}
		}

		return false;

	}
}
