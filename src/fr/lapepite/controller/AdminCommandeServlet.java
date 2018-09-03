package fr.lapepite.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

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
 * Servlet implementation class AdminCommandeServlet
 */

public class AdminCommandeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CHEMIN_JSP_ADMIN_COMMANDE = "/jsp/admin/commande.jsp";
	private CommandeServices commandeServices;
	private LigneCommandeServices ligneCommandeServices;
	private List<LigneCommande> ligneCommandesList;
	private HashMap<String, String> parametersList;
	private Commande commande;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminCommandeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		ligneCommandeServices = new LigneCommandeServices();
		commandeServices = new CommandeServices();
		commande = new Commande();
		ligneCommandesList = new ArrayList<>();
		parametersList = new HashMap<>();
		parametersList.putAll(getParameters(request));
		
		try {
			
			commande = commandeServices.getOneCommande(parametersList);
			
			ligneCommandesList.addAll(ligneCommandeServices.getAllLigneCommandeForOneCommande(parametersList));
			
			request.setAttribute("ligneCommandesList", ligneCommandesList);
			request.setAttribute("commande", commande);
			
		} catch (Exception e) {
			request.setAttribute("errorMessage", e.getMessage());
		}
		
		getServletContext().getRequestDispatcher(CHEMIN_JSP_ADMIN_COMMANDE).forward(request, response);
		
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
