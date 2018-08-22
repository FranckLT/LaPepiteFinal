/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lapepite.controller;

import fr.lapepite.javabean.Utilisateur;
import fr.lapepite.services.LignePanierServices;
import fr.lapepite.services.UtilisateurServices;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserServlet extends HttpServlet {

	private final static String CHEMIN_JSP_USER = "/jsp/user.jsp";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		redirectToView(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Enumeration<String> list = request.getParameterNames();

		LignePanierServices lignePanierServices = new LignePanierServices();

		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		
		int idProduit;

		while (list.hasMoreElements()) {

			final String parameterName = list.nextElement();

			if ("addOne".equals(parameterName)) {
				
				idProduit = Integer.parseInt(request.getParameter("addOne"));

				utilisateur = lignePanierServices.addOrDropOneToQuantity(utilisateur, true, idProduit);

			} else if ("dropOne".equals(parameterName)) {
				
				idProduit = Integer.parseInt(request.getParameter("dropOne"));

				utilisateur = lignePanierServices.addOrDropOneToQuantity(utilisateur, false, idProduit);

			}
		}
		
		HttpSession session = request.getSession();
		
		session.setAttribute("utilisateur", utilisateur);

		doGet(request, response);

	}
	
	public void redirectToView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		getServletContext().getRequestDispatcher(CHEMIN_JSP_USER).forward(request, response);
		
	}

}
