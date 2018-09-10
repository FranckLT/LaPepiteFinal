/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lapepite.controller;

import fr.lapepite.javabean.Bijoux;
import fr.lapepite.services.BijouxServices;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class AdminIndexBijouxServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String URL_AFTER_DELETE = "/LaPepite/admin/bijoux";
	private BijouxServices bijouxServices;
	private List<Bijoux> listBijoux;
	private HashMap<String, String> parametersList;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		bijouxServices = new BijouxServices();

		listBijoux = new ArrayList<>();

		try {
			listBijoux = bijouxServices.getAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("listBijoux", listBijoux);

		getServletContext().getRequestDispatcher("/jsp/admin/adminIndexBijoux.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		parametersList = new HashMap<>();
		bijouxServices = new BijouxServices();

		try {
			
		parametersList.putAll(getParameters(request));
	
		bijouxServices.deleteBijoux(parametersList);
		
		response.sendRedirect(URL_AFTER_DELETE);
		
		} catch (Exception e) {
			request.setAttribute("errorMessage", e.getMessage());
			getServletContext().getRequestDispatcher("/jsp/admin/adminIndexBijoux.jsp").forward(request, response);
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
