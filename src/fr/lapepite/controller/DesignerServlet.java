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

import fr.lapepite.javabean.Bijoux;
import fr.lapepite.javabean.Designer;
import fr.lapepite.services.BijouxServices;
import fr.lapepite.services.DesignerServices;

/**
 * Servlet implementation class DesignerServlet
 */
@WebServlet("/DesignerServlet")
public class DesignerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CHEMIN_JSP_DESIGNER = "/jsp/designer.jsp";
	private Designer designer;
	private DesignerServices designerServices;
	private BijouxServices bijouxServices;
	private HashMap<String, String> parametersList;
	private List<Bijoux> bijouxList;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DesignerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			designerServices = new DesignerServices();
			bijouxServices = new BijouxServices();
			bijouxList = new ArrayList<>();
			parametersList = new HashMap<>();
			designer = null;

			parametersList.putAll(getParameters(request));

			designer = designerServices.getOneDesigner(parametersList);

			bijouxList = bijouxServices.getBijouxByDesigner(parametersList);
			
			if (designer.getDescription_designer() == null) {
				
				request.setAttribute("errorMessage", "Le Designer demandé n'existe ap.");
				
			}

		} catch (Exception e) {
			request.setAttribute("errorMessage", e.getMessage());
		}

		request.setAttribute("designer", designer);
		request.setAttribute("bijouxList", bijouxList);

		getServletContext().getRequestDispatcher(CHEMIN_JSP_DESIGNER).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
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
