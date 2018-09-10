package fr.lapepite.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.lapepite.javabean.Commande;
import fr.lapepite.javabean.Utilisateur;
import fr.lapepite.services.CommandeServices;

/**
 * Servlet implementation class UserCommandesServlet
 */
public class UserCommandesIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CHEMIN_JSP_USER_COMMANDES = "/jsp/commandesList.jsp";
	private CommandeServices commandeServices;
	private Utilisateur utilisateur;
	private List<Commande> commandesList;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserCommandesIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		commandesList = new ArrayList<>();
		commandeServices = new CommandeServices();
		utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		
		try {
			
			commandesList = commandeServices.getAllCommandesForOneUtilisateur(utilisateur);
			
			request.setAttribute("commandesList", commandesList);
			
		} catch (Exception e) {
			request.setAttribute("errorMessage", e.getMessage());
		}
		
		getServletContext().getRequestDispatcher(CHEMIN_JSP_USER_COMMANDES).forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
