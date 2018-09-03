package fr.lapepite.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.lapepite.javabean.Designer;
import fr.lapepite.services.DesignerServices;

/**
 * Servlet implementation class DesignersIndexServlet
 */

public class DesignersIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CHEMIN_JSP_DESIGNERS_INDEX = "/jsp/designersIndex.jsp";
	private List<Designer> designersList;
	private DesignerServices designerServices;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DesignersIndexServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		designersList = new ArrayList<>();
		designerServices = new DesignerServices();

		try {
			designersList = designerServices.getAllDesigners();
			request.setAttribute("designersList", designersList);
		} catch (Exception e) {
			request.setAttribute("errorMessage", e.getMessage());
		}


		getServletContext().getRequestDispatcher(CHEMIN_JSP_DESIGNERS_INDEX).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
