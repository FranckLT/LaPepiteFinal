package fr.lapepite.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.lapepite.javabean.Utilisateur;
import fr.lapepite.services.CommentaireServices;

/**
 * Servlet implementation class CommentaireServlet
 */
public class CommentaireServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String URL_HOME = "/LaPepite/home";
	private static final String URL_PRODUCT = "/LaPepite/shop/product?id=";
	private static final String URL_LOGIN = "/LaPepite/login";
	private HashMap<String, String> parametersList;
	private CommentaireServices commentaireServices;
	private Utilisateur utilisateur;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CommentaireServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.sendRedirect(URL_HOME);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		parametersList = new HashMap<>();
		commentaireServices = new CommentaireServices();
		utilisateur = null;
		utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		parametersList.putAll(getParameters(request));
		int idProduit = Integer.parseInt(parametersList.get("idBijoux"));;

		try {

			commentaireServices.insertCommentaire(parametersList, utilisateur);

			response.sendRedirect(URL_PRODUCT+idProduit);


		} catch (Exception e) {

			e.printStackTrace();
			response.sendRedirect(URL_PRODUCT+idProduit);

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
