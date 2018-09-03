/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lapepite.controller;

import fr.lapepite.javabean.Bijoux;
import fr.lapepite.javabean.Categorie;
import fr.lapepite.javabean.Designer;
import fr.lapepite.services.BijouxServices;
import fr.lapepite.services.CategorieServices;
import fr.lapepite.services.DesignerServices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShopIndexServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String CHEMIN_JSP_SHOP_INDEX = "/jsp/shopIndex.jsp";
	private BijouxServices bijouxServices;
	private CategorieServices categorieServices;
	private DesignerServices designerServices;
	private List<Bijoux> listBijoux;
	private List<Categorie> categoriesList;
	private List<Designer> designersList;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    	// initialisation des service 
        bijouxServices = new BijouxServices();
        designerServices = new DesignerServices();
        categorieServices = new CategorieServices();
        
        //initialisation des liste
        listBijoux = new ArrayList<>();
        categoriesList = new ArrayList<>();
        designersList =  new ArrayList<>();
        
        //ajout des listes de tout les bijoux/designers/categories aux arrayList
        try {
			listBijoux.addAll(bijouxServices.getAll());
			categoriesList.addAll(categorieServices.getAllCategories());
			designersList.addAll(designerServices.getAllDesigners());
		} catch (Exception e) {
			request.setAttribute("errorMessage", e.getMessage());
		}
        
        //on met la liste en attribut pour que la vue puisse la récupérer 
        request.setAttribute("listBijoux", listBijoux);
        request.setAttribute("categoriesList", categoriesList);
        request.setAttribute("designersList", designersList);
        
        // on affiche la vue
        getServletContext().getRequestDispatcher(CHEMIN_JSP_SHOP_INDEX).forward(request, response);
       
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }


}
