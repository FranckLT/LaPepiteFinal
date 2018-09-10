/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lapepite.controller;

import fr.lapepite.javabean.Bijoux;
import fr.lapepite.javabean.Commentaire;
import fr.lapepite.javabean.Utilisateur;
import fr.lapepite.services.BijouxServices;
import fr.lapepite.services.CommentaireServices;
import fr.lapepite.services.PanierServices;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<String, String> parametersList;
	private Bijoux bijoux;
	private Utilisateur utilisateur;
	private ArrayList<Commentaire> commentairesList;
	private CommentaireServices commentairesService;


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		parametersList = new HashMap<>();
		commentairesList = new ArrayList<>();
		commentairesService = new CommentaireServices();
		
		parametersList.putAll(getParameters(request));

		bijoux = null;

		try {
			
			commentairesList.addAll(commentairesService.getCommentairesByProducts(parametersList));
			bijoux = new BijouxServices().getOneBijoux(parametersList);
			
			if (bijoux == null) {
				
				request.setAttribute("errorMessage", "Ce bijoux n'existe pas.");
				
				
			} else {
				
				request.setAttribute("bijoux", bijoux);
				request.setAttribute("commentairesList", commentairesList);
				
			}
			
		} catch (Exception e) {
			
			request.setAttribute("errorMessage", e.getMessage());
			
		}

		getServletContext().getRequestDispatcher("/jsp/product.jsp").forward(request, response);

	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		utilisateur = null;

		utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		
		parametersList = new HashMap<>();
		
		parametersList.putAll(getParameters(request));

		if (utilisateur == null) {
			
			response.sendRedirect("/LaPepite/login");
			
		} else {

			try {
				utilisateur = new PanierServices().addBijouxToPanier(parametersList, utilisateur);
				
				request.getSession().setAttribute("utilisateur", utilisateur);

				int idProduct = Integer.parseInt(parametersList.get("id"));

				response.sendRedirect("/LaPepite/shop/product?id="+idProduct);
				
			} catch (Exception e) {
				request.setAttribute("errorMessage", e.getMessage());
				getServletContext().getRequestDispatcher("/jsp/product.jsp").forward(request, response);
			}
		}

	}

	private HashMap<String, String> getParameters (HttpServletRequest request) {

		Enumeration<String> listParameters = request.getParameterNames();

		HashMap<String, String> parametersMap = new HashMap<>();

		while (listParameters.hasMoreElements()) {

			String parameterName = listParameters.nextElement();

			String string = (String) request.getParameter(parameterName);

			parametersMap.put(parameterName, string);

		}

		return parametersMap;

	}

}
