/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lapepite.controller;

import fr.lapepite.javabean.LignePanier;
import fr.lapepite.javabean.Utilisateur;
import fr.lapepite.services.UtilisateurServices;
import java.io.IOException;
import java.io.PrintWriter;
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
	private UtilisateurServices utilisateurServices;	
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    	utilisateurServices = new UtilisateurServices();
    	
        if (utilisateurServices.testIfUtilisateurIsConnected(request)) {
        	
            Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
            
            List<LignePanier> listLignePanier = utilisateur.getPanier().getListProduit();
            
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
