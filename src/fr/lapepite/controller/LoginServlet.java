/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lapepite.controller;

import fr.lapepite.javabean.Utilisateur;
import fr.lapepite.services.UtilisateurServices;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class LoginServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UtilisateurServices utilisateurServices= new UtilisateurServices();;
	private Utilisateur utilisateur;
	private HashMap<String, String> parametersList = new HashMap<>();
	
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(request, response);
       
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { 
        
        try {

    		parametersList.putAll(getParameters(request));
 
            utilisateur = utilisateurServices.logUtilisateur(parametersList);
            
            request.getSession().setAttribute("utilisateur", utilisateur);
            
            response.sendRedirect("/LaPepite/home");
            
        } catch (Exception ex) {
            request.setAttribute("errorMessage", ex.getMessage());
            getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(request, response);
            
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
