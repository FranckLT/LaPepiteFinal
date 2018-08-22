/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lapepite.controller;

import fr.lapepite.javabean.Bijoux;
import fr.lapepite.services.BijouxServices;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShopIndexServlet extends HttpServlet {
	
	private BijouxServices bijouxServices;
	private List<Bijoux> listBijoux;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    	// initialisation du service 
        bijouxServices = new BijouxServices();
        
        //initialisation de la liste
        listBijoux = new ArrayList<>();
        
        //ajout de la liste de tout les bijoux a l'arrayList
        try {
			listBijoux.addAll(bijouxServices.getAll());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        //on met la liste en attribut pour que la vue puisse la récupérer 
        request.setAttribute("listBijoux", listBijoux);
        
        // on affiche la vue
        getServletContext().getRequestDispatcher("/jsp/shopIndex.jsp").forward(request, response);
       
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
