package fr.lapepite.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.lapepite.db.utils.DBCategorieUtils;
import fr.lapepite.db.utils.DBDesignerUtils;
import fr.lapepite.javabean.Bijoux;
import fr.lapepite.javabean.Categorie;
import fr.lapepite.javabean.Designer;
import fr.lapepite.services.BijouxServices;
import fr.lapepite.services.CategorieServices;
import fr.lapepite.services.DesignerServices;

/**
 * Servlet implementation class AdminEditBijouxServlet
 */
public class AdminEditBijouxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE_DO_GET = "/jsp/admin/formBijoux.jsp";
	public static final String REDIRECT_DO_POST = "/LaPepite/admin/bijoux";
	private BijouxServices bijouxServices;
	private CategorieServices categorieServices;
	private DesignerServices designerServices;
	private HashMap<String, String> parametersList;
	private ArrayList<Designer> listDesigners;
	private ArrayList<Categorie> listCategories;
	private Bijoux bijoux;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		bijouxServices = new BijouxServices();
		categorieServices = new CategorieServices();
		designerServices = new DesignerServices();
		parametersList = new HashMap<>();
		listDesigners = new ArrayList<>();
		listCategories = new ArrayList<>();
		bijoux = null;
		
		try {
			
			parametersList.putAll(getParameters(request));
			listCategories.addAll(categorieServices.getAllCategories());
			listDesigners.addAll(designerServices.getAllDesigners());
			bijoux = bijouxServices.getOneBijoux(parametersList);

			request.setAttribute("listDesigners", listDesigners);
			request.setAttribute("listCategories", listCategories);
			
			if (bijoux == null) {
				request.setAttribute("errorMessage", "Aucun Bijoux correspondant");
			} else {
				request.setAttribute("bijoux", bijoux);
			}
			

		} catch (Exception e) {

			request.setAttribute("errorMessage", e.getMessage());

		}

		getServletContext().getRequestDispatcher(VUE_DO_GET).forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {	

			parametersList.putAll(getParameters(request));

			bijouxServices.updateOne(parametersList);

			response.sendRedirect(REDIRECT_DO_POST);


		} catch (Exception e) {
			
			request.setAttribute("errorMessage", e.getMessage());
			
			getServletContext().getRequestDispatcher(VUE_DO_GET).forward(request, response);
			
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
