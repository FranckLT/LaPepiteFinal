package fr.lapepite.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fr.lapepite.services.CategorieServices;

/**
 * Servlet implementation class AdminAddCategoryServlet
 */

public class AdminAddCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CHEMIN_JSP_ADD_CATEGORY = "/jsp/admin/formCategory.jsp";
	private static final String URL_INDEX_CATEGORY = "/LaPepite/admin/categories";
	
	private HashMap<String, String> parametersMap;
	private CategorieServices categorieServices;
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		redirectToView(request, response, CHEMIN_JSP_ADD_CATEGORY);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {

			parametersMap = new HashMap<>();

			parametersMap = getParameters(request);

			categorieServices = new CategorieServices();

			categorieServices.addOneCategory(parametersMap);

			response.sendRedirect(URL_INDEX_CATEGORY);
		}
		catch (Exception e) {
			
			request.setAttribute("errorMessage", e.getMessage());
			
			redirectToView(request, response, CHEMIN_JSP_ADD_CATEGORY);
			
		}


	}

	private void redirectToView(HttpServletRequest request, HttpServletResponse response, String chemin) throws ServletException, IOException {

		getServletContext().getRequestDispatcher(chemin).forward(request, response);

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
