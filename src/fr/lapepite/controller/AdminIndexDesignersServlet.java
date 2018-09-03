package fr.lapepite.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.lapepite.javabean.Designer;
import fr.lapepite.services.DesignerServices;

/**
 * Servlet implementation class AdminIndexDesignersServlet
 */

public class AdminIndexDesignersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CHEMIN_JSP_INDEX_DESIGNERS = "/jsp/admin/indexDesigners.jsp"; 
	private ArrayList<Designer> designersList;
	private DesignerServices designerServices;
	private HashMap<String, String> parametersList;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		designersList = new ArrayList<>();

		designerServices = new DesignerServices();

		try {

			designersList.addAll(designerServices.getAllDesigners());

			request.setAttribute("designersList", designersList);

		} catch (Exception e) {

			request.setAttribute("errorMessage", e.getMessage());

		}

		redirectToView(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		designerServices = new DesignerServices();

		parametersList = new HashMap<>();

		try {

			parametersList.putAll(getParameters(request));

			designerServices.deleteOneCategory(parametersList);
			
		} catch (Exception e) {
			
			
			request.setAttribute("errorMessage", e.getMessage());
		}

		doGet(request, response);

	}

	private void redirectToView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		getServletContext().getRequestDispatcher(CHEMIN_JSP_INDEX_DESIGNERS).forward(request, response);

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
