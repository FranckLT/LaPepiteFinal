package fr.lapepite.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.lapepite.javabean.Utilisateur;
import fr.lapepite.services.CommandeServices;
import fr.lapepite.services.UtilisateurServices;

/**
 * Servlet implementation class OrderServlet
 */
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CHEMIN_JSP_ORDER = "/jsp/order.jsp";
	private static final String URL_USER = "/LaPepite/user";

	private Utilisateur utilisateur;
	private UtilisateurServices utilisateurServices;
	private boolean panierEmpty;
	private HashMap<String, String> parametersList;
	private CommandeServices commandeServices;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");

		utilisateurServices = new UtilisateurServices();

		panierEmpty = utilisateurServices.verifIfPanierIsEmpty(utilisateur);

		if (panierEmpty) {
			response.sendRedirect(URL_USER);
		} else {
			redirectToView(request, response);
		}



	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		parametersList = new HashMap<>();

		commandeServices = new CommandeServices(); 


		try {
			utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");

			parametersList.putAll(getParameters(request));

			utilisateur = commandeServices.createCommande(utilisateur, parametersList);
			
			response.sendRedirect(URL_USER);
			
		} catch (Exception e) {
			
			request.setAttribute("errorMessage", e.getMessage());
			
			redirectToView(request, response);
			
		}

		

	}

	public void redirectToView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		getServletContext().getRequestDispatcher(CHEMIN_JSP_ORDER).forward(request, response);

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
