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
public class AdminCommandesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<Commande> commandesList;
	private CommandeServices commandeServices;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminCommandesServlet() {
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
			
			getServletContext().getRequestDispatcher("").forward(request, response);
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
