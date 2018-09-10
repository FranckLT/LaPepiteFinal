/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lapepite.controller;

import fr.lapepite.db.utils.DBCategorieUtils;
import fr.lapepite.db.utils.DBDesignerUtils;
import fr.lapepite.javabean.Categorie;
import fr.lapepite.javabean.Designer;
import fr.lapepite.services.BijouxServices;
import fr.lapepite.services.CategorieServices;
import fr.lapepite.services.DesignerServices;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.sun.jndi.url.corbaname.corbanameURLContextFactory;


public class AdminAddBijouxServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String VUE_DO_GET = "/jsp/admin/formBijoux.jsp";
	public static final String VUE_DO_POST = "/LaPepite/admin/bijoux";
	private HashMap<String, String> parametersMap;
	private Part part;
	private BijouxServices bijouxServices;
	private CategorieServices categorieServices;
	private DesignerServices designerServices;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<Designer> listDesigners = new ArrayList<>();
		ArrayList<Categorie> listCategories = new ArrayList<>();
		categorieServices = new CategorieServices();
		designerServices = new DesignerServices();
		
		try {
			
			listCategories.addAll(categorieServices.getAllCategories());
			listDesigners.addAll(designerServices.getAllDesigners());
			
			request.setAttribute("listDesigners", listDesigners);
			request.setAttribute("listCategories", listCategories);

			
		} catch (Exception e) {
			
			request.setAttribute("errorMessage", e.getMessage());
			
		}

		
		redirectToView(request, response);
		

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
		parametersMap = new HashMap<>();
		bijouxServices = new BijouxServices();
		
		try {
				
			parametersMap.putAll(getParameters(request));
			part = request.getPart("fichier");
			
			bijouxServices.addOne(parametersMap, part);

			response.sendRedirect(VUE_DO_POST);
			
		}
		catch (Exception e) {
			
			request.setAttribute("errorMessage", e.getMessage());
		
			redirectToView(request, response);
			
		}

	}
	
	private void redirectToView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		getServletContext().getRequestDispatcher(VUE_DO_GET).forward(request, response);
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