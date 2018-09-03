/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lapepite.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.swing.internal.plaf.basic.resources.basic;

import fr.lapepite.services.BijouxServices;
import fr.lapepite.services.CategorieServices;
import fr.lapepite.services.CommandeServices;
import fr.lapepite.services.DesignerServices;


public class AdminIndexServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static String CHEMIN_JSP_INDEX_ADMIN = "/jsp/admin/indexAdmin.jsp";
	private BijouxServices bijouxServices;
	private DesignerServices designerServices;
	private CategorieServices categorieServices;
	private CommandeServices commandeServices;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	bijouxServices = new BijouxServices();
    	designerServices = new DesignerServices();
    	categorieServices = new CategorieServices();
    	commandeServices = new CommandeServices();
    	
    	try {
			int numberOfBijoux = bijouxServices.getNumberOfBijoux();
			int numberOfDesigners = designerServices.getNumberOfDesigners();
			int numberofCategories = categorieServices.getNumberOfCategories();
			int numberOfCommandes = commandeServices.getNumberOfCommandes();
			
			request.setAttribute("bijouxNumber", numberOfBijoux);
			request.setAttribute("designersNumber", numberOfDesigners);
			request.setAttribute("categoriesNumber", numberofCategories);
			request.setAttribute("commandesNumber", numberOfCommandes);
			
			
		} catch (Exception e) {
			
			request.setAttribute("errorMessage", e.getMessage());
			
		}
    	
        
        redirectToView(request, response);
       
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
    	redirectToView(request, response);
    	
    }

  
    public void redirectToView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	getServletContext().getRequestDispatcher(CHEMIN_JSP_INDEX_ADMIN).forward(request, response);
	}

}
