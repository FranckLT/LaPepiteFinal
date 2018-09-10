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
import fr.lapepite.db.utils.DBDesignerUtils;
import fr.lapepite.javabean.Designer;

public class DesignerServices {

	public ArrayList<Designer> getAllDesigners() throws Exception {

		ArrayList<Designer> designersList = new ArrayList<>();

		try {

			designersList.addAll(DBDesignerUtils.selectAllDesigners());

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return designersList;
	}

	public int getNumberOfDesigners() throws Exception {

		ArrayList<Designer> designersList = new ArrayList<>();

		designersList.addAll(getAllDesigners());

		return designersList.size();
	}

	public Designer getOneDesigner(HashMap<String, String> parametersList) throws NumberFormatException, Exception {

		Designer designer = new Designer();

		try {

			designer = DBDesignerUtils.selectOneDesignerById(Integer.parseInt(parametersList.get("id")));

			return designer;

		} catch (Exception e) {

			throw new Exception(e.getMessage());

		}

	}

	public void updateOneDesigner(HashMap<String, String> parametersList) throws Exception {

		Designer designer = new Designer();

		int id_designer = Integer.parseInt(parametersList.get("id"));

		String nom_designer = parametersList.get("nomDesigner");

		String description_designer = parametersList.get("descriptionDesigner");

		designer.setId_designer(id_designer);
		designer.setNom_designer(nom_designer);
		designer.setDescription_designer(description_designer);

		DBDesignerUtils.updateDesigner(designer);

	}

	public void addOneDesigner(HashMap<String, String> parametersList) throws Exception {

		if (verifForm(parametersList)) {

			if (!verifIfNomCategoryIsAlreadyUsed(parametersList)) {

				Designer designer = new Designer();

				designer.setNom_designer(parametersList.get("nomDesigner"));

				designer.setDescription_designer(parametersList.get("descriptionDesigner"));

				DBDesignerUtils.insertDesigner(designer);

			}

		}

	}

	public void deleteOneCategory(HashMap<String, String> parametersList) throws Exception {

		Designer designer = new Designer();

		designer.setId_designer(Integer.parseInt(parametersList.get("idToDelete")));

		DBDesignerUtils.deleteDesigner(designer);

	}

	public static boolean verifForm(HashMap<String, String> parametersList) throws Exception {

		Set<String> keysList = parametersList.keySet();

		for (String parameterName : keysList) {

			if ("".equals(parametersList.get(parameterName))) {

				throw new Exception("Le champ " + parameterName + " ne peut pas être nul.");

			}

		}

		return true;
	}

	public static boolean verifIfNomCategoryIsAlreadyUsed(HashMap<String, String> parametersList) throws Exception {

		String nomdesigner = parametersList.get("nomDesigner");

		List<Designer> listdesigners = new ArrayList<>();

		listdesigners.addAll(DBDesignerUtils.selectAllDesigners());

		for (Designer designer : listdesigners) {
			if (designer.getNom_designer().equals(nomdesigner)) {

				throw new Exception("Le nom est deja utilisé.");
			}
		}

		return false;

	}

}
