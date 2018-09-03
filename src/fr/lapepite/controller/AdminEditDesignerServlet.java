package fr.lapepite.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.lapepite.javabean.Designer;
import fr.lapepite.services.DesignerServices;

/**
 * Servlet implementation class AdminEditDesignerServlet
 */
public class AdminEditDesignerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CHEMIN_JSP_EDIT_DESIGNER = "/jsp/admin/formDesigner.jsp";
	private static final String URL_INDEX_DESIGNER = "/LaPepite/admin/designers";
	private Designer designer;
	private DesignerServices designerServices;
	private HashMap<String, String> parametersList;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		designer = new Designer();

		designerServices = new DesignerServices();

		parametersList = new HashMap<>();

		try {

			parametersList.putAll(getParameters(request));

			designer = designerServices.getOneDesigner(parametersList);

			request.setAttribute("designer", designer);

		} catch (NumberFormatException e) {

			request.setAttribute("errorMessageNumber",e.getMessage());

		} catch (Exception e) {

			request.setAttribute("errorMessage",e.getMessage());

		}

		redirectToView(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		parametersList = new HashMap<>();

		designerServices = new DesignerServices();

		try {

			parametersList.putAll(getParameters(request));

			designerServices.updateOneDesigner(parametersList);
			
			response.sendRedirect(URL_INDEX_DESIGNER);
			
		} catch (Exception e) {
			request.setAttribute("errorMessage", e.getMessage());
			
			redirectToView(request, response);
		}

		
	}

	private void redirectToView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher(CHEMIN_JSP_EDIT_DESIGNER).forward(request, response);

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
