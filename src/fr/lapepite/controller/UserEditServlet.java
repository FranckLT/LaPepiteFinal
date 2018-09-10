package fr.lapepite.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.lapepite.javabean.Utilisateur;
import fr.lapepite.services.UtilisateurServices;

/**
 * Servlet implementation class UserEditServlet
 */

public class UserEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CHEMIN_JSP_USER_EDIT = "/jsp/editUser.jsp";
	private static final String REDIRECT_DO_POST="/LaPepite/user";
	private UtilisateurServices utilisateurServices;
	private Utilisateur utilisateur;
	private HashMap<String, String> parametersList;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		getServletContext().getRequestDispatcher(CHEMIN_JSP_USER_EDIT).forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		parametersList = new HashMap<>();
		utilisateur = new Utilisateur();
		utilisateurServices = new UtilisateurServices();
		
		try {
			parametersList.putAll(getParameters(request));
			
			utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");

			utilisateur = utilisateurServices.updateUtilisateur(utilisateur, parametersList);
			
			request.getSession().setAttribute("utilisateur", utilisateur);
			
			response.sendRedirect(REDIRECT_DO_POST);
			
		} catch (Exception e) {
			
			request.setAttribute("errorMessage", e.getMessage());
			
			getServletContext().getRequestDispatcher(CHEMIN_JSP_USER_EDIT).forward(request, response);
			
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
