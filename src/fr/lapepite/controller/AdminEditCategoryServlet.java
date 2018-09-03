package fr.lapepite.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.lapepite.javabean.Categorie;
import fr.lapepite.services.CategorieServices;

/**
 * Servlet implementation class AdminEditCategoryServlet
 */
public class AdminEditCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CHEMIN_JSP_EDIT_CATEGORY = "/jsp/admin/formCategory.jsp";
	private static final String URL_INDEX_CATEGORIES = "/LaPepite/admin/categories";
	private Categorie categorie;
	private CategorieServices categorieServices;
	private HashMap<String, String> parametersList;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		categorieServices = new CategorieServices();
		categorie = new Categorie();
		parametersList = new HashMap<>();

		try {
			
			parametersList.putAll(getParameters(request));

			categorie = categorieServices.getOneCategory(parametersList);

			request.setAttribute("categorie", categorie);
			
		} catch (Exception e) {

			request.setAttribute("errorMessage", e.getMessage());

		}

		redirectToView(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			parametersList = new HashMap<>();

			parametersList.putAll(getParameters(request));

			categorieServices.updateOneCategory(parametersList);

			response.sendRedirect(URL_INDEX_CATEGORIES);
			
		} catch (Exception e) {
			
			request.setAttribute("errorMessage", e.getMessage());
			
			redirectToView(request, response);
		}

	}

	private void redirectToView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher(CHEMIN_JSP_EDIT_CATEGORY).forward(request, response);
	}

	private HashMap<String, String> getParameters (HttpServletRequest request) {

		Enumeration<String> listParameters = request.getParameterNames();

		HashMap<String, String> parametersMap = new HashMap<>();

		while (listParameters.hasMoreElements()) {

			String parameterName = listParameters.nextElement();
			
			System.out.println(parameterName);

			String string = (String) request.getParameter(parameterName);

			parametersMap.put(parameterName, string);

		}

		return parametersMap;

	}


}
