package fr.lapepite.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.lapepite.javabean.Commande;
import fr.lapepite.javabean.LigneCommande;
import fr.lapepite.services.CommandeServices;
import fr.lapepite.services.LigneCommandeServices;

/**
 * Servlet implementation class UserCommandeServlet
 */

public class UserCommandeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CHEMIN_JSP_COMMANDE = "/jsp/commande.jsp";
	private CommandeServices commandeServices;
	private LigneCommandeServices ligneCommandeServices;
	private Commande commande;
	private List<LigneCommande> ligneCommandeList;
	private HashMap<String, String> parametersList;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserCommandeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		commandeServices = new CommandeServices();
		ligneCommandeServices = new LigneCommandeServices();
		commande = new Commande();
		parametersList = new HashMap<>();
		ligneCommandeList = new ArrayList<>();
		
		parametersList.putAll(getParameters(request));
		
		try {
			ligneCommandeList = ligneCommandeServices.getAllLigneCommandeForOneCommande(parametersList);
			
			commande = commandeServices.getOneCommande(parametersList);
			
			request.setAttribute("commande", commande);
			request.setAttribute("ligneCommandesList", ligneCommandeList);
			
		} catch (Exception e) {
			
			request.setAttribute("errorMessage", e.getMessage());
			
		}
		
		getServletContext().getRequestDispatcher(CHEMIN_JSP_COMMANDE).forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
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
