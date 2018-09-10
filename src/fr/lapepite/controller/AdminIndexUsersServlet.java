package fr.lapepite.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.lapepite.javabean.Utilisateur;
import fr.lapepite.services.LignePanierServices;
import fr.lapepite.services.UtilisateurServices;

/**
 * Servlet implementation class AdminIndexUsersServlet
 */
@WebServlet("/AdminIndexUsersServlet")
public class AdminIndexUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CHEMIN_JSP_INDEX_USERS = "/jsp/admin/userIndex.jsp";
	private UtilisateurServices utilisateurServices;
	private List<Utilisateur> utilisateursList;
	private HashMap<String, String> parametersList;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminIndexUsersServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		utilisateurServices = new UtilisateurServices();
		utilisateursList = new ArrayList<>();

		try {

			utilisateursList.addAll(utilisateurServices.getAllUtilisateurs());

			request.setAttribute("utilisateursList", utilisateursList);

		} catch (Exception e) {

			request.setAttribute("errorMessage", e.getMessage());

		}

		getServletContext().getRequestDispatcher(CHEMIN_JSP_INDEX_USERS).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		parametersList = new HashMap<>();
		parametersList.putAll(getParameters(request));
		utilisateurServices = new UtilisateurServices();

		try {

			if (!parametersList.isEmpty()) {
				
				if (parametersList.containsKey("idToDelete")) {
					
					utilisateurServices.deleteUtilisateur(parametersList);

				} else if (parametersList.containsKey("id")) {

					utilisateurServices.adminUtilisateur(parametersList);

				}
			}

		} catch (Exception e) {
			request.setAttribute("errorMessage", e.getMessage());
		}

		doGet(request, response);
	}

	private HashMap<String, String> getParameters(HttpServletRequest request) {

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
