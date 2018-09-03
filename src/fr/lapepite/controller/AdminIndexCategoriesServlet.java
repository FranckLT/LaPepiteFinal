package fr.lapepite.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.lapepite.javabean.Categorie;
import fr.lapepite.method.Method;
import fr.lapepite.services.CategorieServices;


public class AdminIndexCategoriesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CHEMIN_JSP_ADMIN_CATEGORIES = "/jsp/admin/indexCategories.jsp";

	private ArrayList<Categorie> categoriesList;
	private CategorieServices categorieServices;
	private HashMap<String, String> parametersList;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		categoriesList = new ArrayList<>();

		categorieServices = new CategorieServices();

		try {

			categoriesList.addAll(categorieServices.getAllCategories());

			request.setAttribute("categoriesList", categoriesList);

		} catch (Exception e) {
			request.setAttribute("errorMessage", e.getMessage());
		}

		request.setAttribute("categoriesList", categoriesList);

		redirectToView(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		categorieServices = new CategorieServices();

		parametersList = new HashMap<>();

		try {

			parametersList.putAll(Method.getParameters(request));

			categorieServices.deleteOneCategory(parametersList);
			
		} catch (Exception e) {
			
			request.setAttribute("errorMessage", e.getMessage());
			
		}

		doGet(request, response);

	}

	private void redirectToView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		getServletContext().getRequestDispatcher(CHEMIN_JSP_ADMIN_CATEGORIES).forward(request, response);

	}

}
