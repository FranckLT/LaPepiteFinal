package fr.lapepite.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.lapepite.javabean.Commande;
import fr.lapepite.services.CommandeServices;

/**
 * Servlet implementation class AdminCommandesServlet
 */
public class AdminIndexCommandesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CHEMIN_JSP_INDEX_COMMANDES ="/jsp/admin/indexCommandes.jsp";
	private List<Commande> commandesList;
	private CommandeServices commandeServices;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminIndexCommandesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		commandesList = new ArrayList<>();
		commandeServices = new CommandeServices();
		
		try {
			
			commandesList = commandeServices.getAllCommandes();
			
			request.setAttribute("commandesList", commandesList);
			
		} catch (Exception e) {
			
			request.setAttribute("errorMessage", e.getMessage());
			
		}
		
		getServletContext().getRequestDispatcher(CHEMIN_JSP_INDEX_COMMANDES).forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	

}
