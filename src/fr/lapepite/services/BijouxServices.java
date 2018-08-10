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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.PortableServer.ServantActivator;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class BijouxServices{


	public List<Bijoux> getAll() {


		List<Bijoux> listBijoux = new ArrayList<>();

		listBijoux = DBBijouxUtils.requestSelect();

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


	public static void addOne(HttpServletRequest request) throws NumberFormatException, Exception {
		
		if (verifForm(request)) {

			if (verifIfNomAndRefBijouxAlreadyUsed(request)) {

				//récup de tout les parametre 
				String nomBijoux = request.getParameter("nomBijoux");
				String refBijoux = request.getParameter("refBijoux");
				int prixBijoux = Integer.parseInt(request.getParameter("prixBijoux"));
				int stockBijoux = Integer.parseInt(request.getParameter("stockBijoux"));
				String description = request.getParameter("descriptionBijoux");
				int idDesignerBijoux = Integer.parseInt(request.getParameter("designerBijoux"));
				int idCategorieBijoux = Integer.parseInt(request.getParameter("categorieBijoux"));

				//initialisation objets
				Bijoux bijoux = new Bijoux();
				Designer designer = new Designer();
				Categorie categorie = new Categorie();

				//set des objets
				designer.setId(idDesignerBijoux);
				categorie.setId_categorie(idCategorieBijoux);

				bijoux
				.setNom(nomBijoux)
				.setRef(refBijoux)
				.setPrix(prixBijoux)
				.setStock(stockBijoux)
				.setDescription(description)
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

		bijoux.setId(idBijoux);

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


	public static boolean verifIfNomAndRefBijouxAlreadyUsed(HttpServletRequest request){

		String nom = request.getParameter("nomBijoux");

		String ref = request.getParameter("refBijoux");

		List<Bijoux> listNomBijoux = new ArrayList<>();

		listNomBijoux.addAll(DBBijouxUtils.requestSelect());

		for (Bijoux bijoux : listNomBijoux) {
			if (bijoux.getNom().equals(nom)) {
				return true;
			}
			if (bijoux.getRef().equals(ref)) {
				return true;
			}
		}

		return false;

	}

	public static boolean verifForm(HttpServletRequest request) throws Exception {

		String nomBijoux = request.getParameter("nomBijoux");
		String refBijoux = request.getParameter("refBijoux");
		int prixBijoux = Integer.parseInt(request.getParameter("prixBijoux"));
		String description = request.getParameter("descriptionBijoux");

		if (nomBijoux.isEmpty()) {
			throw new Exception("Le nom du bijoux est vide.");
		}

		if (refBijoux.isEmpty()) {
			throw new Exception("La reférence du bijoux est vide.");
		}

		if (prixBijoux==0) {
			throw new Exception("Le prix du bijoux est vide.");
		}

		if (description.isEmpty()) {
			throw new Exception("La description du bijoux est vide.");
		}

		return true;
	}

}
