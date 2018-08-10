package fr.lapepite.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.lapepite.db.utils.DBCategorieUtils;
import fr.lapepite.db.utils.DBDesignerUtils;
import fr.lapepite.javabean.Bijoux;
import fr.lapepite.javabean.Categorie;
import fr.lapepite.javabean.Designer;
import fr.lapepite.services.BijouxServices;

/**
 * Servlet implementation class AdminEditBijouxServlet
 */
public class AdminEditBijouxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE_DO_GET = "/jsp/admin/formBijoux.jsp";
	public static final String REDIRECT_DO_POST = "/LaPepite/admin/bijoux";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminEditBijouxServlet() {
		super();
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ArrayList<Designer> listDesigners = new ArrayList<>();
		ArrayList<Categorie> listCategories = new ArrayList<>();

		listCategories.addAll(DBCategorieUtils.requestSelect());
		listDesigners.addAll(DBDesignerUtils.requestSelect());

		request.setAttribute("listDesigners", listDesigners);
		request.setAttribute("listCategories", listCategories);

		Bijoux bijoux = new Bijoux();

		bijoux=BijouxServices.getOneBijoux(request);

		request.setAttribute("bijoux", bijoux);

		getServletContext().getRequestDispatcher(VUE_DO_GET).forward(request, response);
	}

	/**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {	
			
			BijouxServices.updateOne(request);

			response.sendRedirect(REDIRECT_DO_POST);


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
