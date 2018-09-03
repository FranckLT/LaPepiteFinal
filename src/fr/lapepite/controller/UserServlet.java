/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lapepite.controller;

import fr.lapepite.javabean.Utilisateur;
import fr.lapepite.services.LignePanierServices;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class UserServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static String CHEMIN_JSP_USER = "/jsp/user.jsp";
	private LignePanierServices lignePanierServices;
	private Utilisateur utilisateur;
	private Enumeration<String> parametersList;
	private int idProduit;
	private HttpSession session;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		redirectToView(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		parametersList = request.getParameterNames();
		lignePanierServices = new LignePanierServices();
		utilisateur =  new Utilisateur();

		utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		
		while (parametersList.hasMoreElements()) {

			final String parameterName = parametersList.nextElement();

			if ("addOne".equals(parameterName)) {
				
				idProduit = Integer.parseInt(request.getParameter("addOne"));

				utilisateur = lignePanierServices.addOrDropOneToQuantity(utilisateur, true, idProduit);

			} else if ("dropOne".equals(parameterName)) {
				
				idProduit = Integer.parseInt(request.getParameter("dropOne"));

				utilisateur = lignePanierServices.addOrDropOneToQuantity(utilisateur, false, idProduit);

			}
		}
		
		session = request.getSession();
		
		session.setAttribute("utilisateur", utilisateur);

		doGet(request, response);

	}
	
	public void redirectToView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		getServletContext().getRequestDispatcher(CHEMIN_JSP_USER).forward(request, response);
		
	}

}
