package fr.lapepite.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fr.lapepite.services.DesignerServices;

/**
 * Servlet implementation class AdminAddDesignerServlet
 */
public class AdminAddDesignerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CHEMIN_JSP_ADD_DESIGNER = "/jsp/admin/formDesigner.jsp";
	private static final String URL_INDEX_DESIGNERS = "/LaPepite/admin/designers";
	private HashMap<String, String> parametersMap;
	private DesignerServices designerServices;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		redirectToView(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			parametersMap = new HashMap<>();

			parametersMap = getParameters(request);

			designerServices = new DesignerServices();

			designerServices.addOneDesigner(parametersMap);

			response.sendRedirect(URL_INDEX_DESIGNERS);
		}
		catch (Exception e) {
			
			request.setAttribute("errorMessage", e.getMessage());
			
			redirectToView(request, response);
			
		}
	}
	
	public void redirectToView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		getServletContext().getRequestDispatcher(CHEMIN_JSP_ADD_DESIGNER).forward(request, response);
		
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
