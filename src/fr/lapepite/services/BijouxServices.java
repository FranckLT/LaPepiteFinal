/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.lapepite.services;

import fr.lapepite.db.utils.DBBijouxUtils;
import fr.lapepite.db.utils.DBUtilisateurUtils;
import fr.lapepite.javabean.Bijoux;
import fr.lapepite.javabean.Categorie;
import fr.lapepite.javabean.Designer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.PortableServer.ServantActivator;


public class BijouxServices{


	public List<Bijoux> getAll() throws Exception {


		List<Bijoux> listBijoux = new ArrayList<>();
		
		//requête en base 
		listBijoux = DBBijouxUtils.requestSelectAll();

		return listBijoux;
		
	}

	public static Bijoux getOneBijoux(HttpServletRequest request) throws ServletException, IOException {

		try {

			int idBijoux = Integer.parseInt(request.getParameter("id"));

			Bijoux bijoux = new Bijoux();

			bijoux = DBBijouxUtils.selectOneBijouxById(idBijoux);

			return bijoux;

		} catch (Exception ex) {

			Logger.getLogger(BijouxServices.class.getName()).log(Level.SEVERE, null, ex);
		}

		return null;
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

	public void deleteBijoux(HttpServletRequest request) {

		int idBijoux = Integer.parseInt(request.getParameter("idToDelete"));

		Bijoux bijoux = new Bijoux();

		bijoux.setId_bijoux(idBijoux);

		DBBijouxUtils.deleteBijoux(bijoux);

	}


	public static void updateOne(HttpServletRequest request) throws Exception {

		int idBijoux = Integer.parseInt(request.getParameter("id"));
		
		
		
		Enumeration attrs =  request.getParameterNames();
		while(attrs.hasMoreElements()) {
		    System.out.println(attrs.nextElement());
		}
		
			
			String nomBijoux = request.getParameter("nomBijoux");
			
			
			String refBijoux = request.getParameter("refBijoux");
			
			//int prixBijoux = Integer.parseInt(request.getParameter("prixBijoux"));
			//int stockBijoux = Integer.parseInt(request.getParameter("stockBijoux"));
			String description = request.getParameter("descriptionBijoux");
	
			//int idDesignerBijoux = Integer.parseInt(request.getParameter("designerBijoux"));
			//int idCategorieBijoux = Integer.parseInt(request.getParameter("categorieBijoux"));

			//initialisation objets
			Bijoux bijoux = new Bijoux();
			Designer designer = new Designer();
			Categorie categorie = new Categorie();

			//set des objets
		//	designer.setId(idDesignerBijoux);
		//	categorie.setId_categorie(idCategorieBijoux);

			

			//ajout à la BDD
		//	DBBijouxUtils.updateBijoux(bijoux);


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
			
			if (parametersList.get(parameterName).equals("")) {
				throw new Exception("Le champ " +parameterName+" ne peut pas être nul.");
			}
			
		}

		return true;
	}

}
