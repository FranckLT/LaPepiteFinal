/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lapepite.controller;

import fr.lapepite.javabean.Utilisateur;
import fr.lapepite.services.UtilisateurServices;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RegisteringServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UtilisateurServices utilisateurServices;
	private final String REDIRECT_HOME = "/LaPepite/home";
	private final String CHEMIN_JSP_REGISTER = "/jsp/register.jsp";
	private HashMap<String, String> parametersList;
	private Utilisateur utilisateur;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		getServletContext().getRequestDispatcher(CHEMIN_JSP_REGISTER).forward(request, response);

	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

		// variables
		utilisateurServices = new UtilisateurServices();
		parametersList = new HashMap<>();
		utilisateur = new Utilisateur();
		
		// récup parametres
		parametersList.putAll(getParameters(request));
		
		// register du user
		utilisateur =  utilisateurServices.registerUser(parametersList);
		
		// enregistrement en session
		request.getSession().setAttribute("utilisateur", utilisateur);
		
		// redirection si l'utilisateur à bien été enregistré
		response.sendRedirect(REDIRECT_HOME);
		
		} catch (Exception e) {
			request.setAttribute("errorMessage", e.getMessage());
			getServletContext().getRequestDispatcher(CHEMIN_JSP_REGISTER).forward(request, response);
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








