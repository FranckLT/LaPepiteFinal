/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lapepite.controller;

import fr.lapepite.javabean.LignePanier;
import fr.lapepite.javabean.Utilisateur;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class panierServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Utilisateur utilisateur;
	private List<LignePanier> listLignePanier;
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	utilisateur = null;
    	
    	utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
    	
        if (utilisateur != null) {
            
            listLignePanier = utilisateur.getPanier().getListProduit();
            
            request.setAttribute("listLignePanier", listLignePanier);
            
            getServletContext().getRequestDispatcher("/jsp/panier.jsp").forward(request, response);
            
        } else {
        	
            response.sendRedirect("/LaPepite/login");
            
        }
        
       
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

}
