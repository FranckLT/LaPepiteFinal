/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package fr.lapepite.controller;

import fr.lapepite.services.PanierServices;
import fr.lapepite.services.UtilisateurServices;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RegisteringServlet extends HttpServlet {
	
	private UtilisateurServices utilisateurServices;
	private final String REDIRECT_HOME = "/LaPepite/home";
	private final String REDRIRECT_REGISTER = "/LaPepite/register";
   
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        getServletContext().getRequestDispatcher("/jsp/register.jsp").forward(request, response);
        
    }
    
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	utilisateurServices = new UtilisateurServices();
    	
    	boolean registeringOk =  utilisateurServices.registerUser(request);

    	if (registeringOk) {
    		// redirection si l'utilisateur à bien été enregistré
    		response.sendRedirect(REDIRECT_HOME);
    		
		} else {
			//redirection si l'utilisateur n'a pas pu être enregistré
			response.sendRedirect(REDRIRECT_REGISTER);

		}
       
    }
      
}








