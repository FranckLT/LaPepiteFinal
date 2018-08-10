/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lapepite.controller;

import fr.lapepite.db.utils.DBCategorieUtils;
import fr.lapepite.db.utils.DBDesignerUtils;
import fr.lapepite.javabean.Bijoux;
import fr.lapepite.javabean.Categorie;
import fr.lapepite.javabean.Designer;
import fr.lapepite.services.BijouxServices;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


public class AdminAddBijouxServlet extends HttpServlet {

	public static final String CHAMP_DESCRIPTION = "description";
	public static final String CHAMP_FICHIER = "fichier";
	public static final String CHEMIN = "chemin";
	public static final int TAILLE_TAMPON = 10240;
	public static final String VUE_DO_GET = "/jsp/admin/formBijoux.jsp";
	public static final String REDIRECT_DO_POST = "/LaPepite/admin/bijoux";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<Designer> listDesigners = new ArrayList<>();
		ArrayList<Categorie> listCategories = new ArrayList<>();

		listCategories.addAll(DBCategorieUtils.requestSelect());
		listDesigners.addAll(DBDesignerUtils.requestSelect());

		request.setAttribute("listDesigners", listDesigners);
		request.setAttribute("listCategories", listCategories);

		getServletContext().getRequestDispatcher(VUE_DO_GET).forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			BijouxServices services = new BijouxServices();

			BijouxServices.addOne(request);

			response.sendRedirect(REDIRECT_DO_POST);
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	protected void testImage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		/*
		 * Lecture du paramètre 'chemin' passé à la servlet via la déclaration
		 * dans le web.xml
		 */
		String chemin = this.getServletConfig().getInitParameter( CHEMIN );

		/* Récupération du contenu du champ de description */
		String description = request.getParameter( CHAMP_DESCRIPTION );
		request.setAttribute( CHAMP_DESCRIPTION, description );

		/*
		 * Les données reçues sont multipart, on doit donc utiliser la méthode
		 * getPart() pour traiter le champ d'envoi de fichiers.
		 */
		Part part = request.getPart( CHAMP_FICHIER );

		/*
		 * Il faut déterminer s'il s'agit d'un champ classique 
		 * ou d'un champ de type fichier : on délègue cette opération 
		 * à la méthode utilitaire getNomFichier().
		 */
		String nomFichier = getNomFichier( part );

		/*
		 * Si la méthode a renvoyé quelque chose, il s'agit donc d'un champ
		 * de type fichier (input type="file").
		 */
		if ( nomFichier != null && !nomFichier.isEmpty() ) {
			String nomChamp = part.getName();
			/*
			 * Antibug pour Internet Explorer, qui transmet pour une raison
			 * mystique le chemin du fichier local à la machine du client...
			 * 
			 * Ex : C:/dossier/sous-dossier/fichier.ext
			 * 
			 * On doit donc faire en sorte de ne sélectionner que le nom et
			 * l'extension du fichier, et de se débarrasser du superflu.
			 */
			nomFichier = nomFichier.substring( nomFichier.lastIndexOf( '/' ) + 1 )
					.substring( nomFichier.lastIndexOf( '\\' ) + 1 );

			/* Écriture du fichier sur le disque */
			ecrireFichier( part, nomFichier, chemin );

			request.setAttribute( nomChamp, nomFichier );
		}

	}

	/*
	 * Méthode utilitaire qui a pour but d'écrire le fichier passé en paramètre
	 * sur le disque, dans le répertoire donné et avec le nom donné.
	 */
	@SuppressWarnings("resource")
	private void ecrireFichier( Part part, String nomFichier, String chemin ) throws IOException {
		/* Prépare les flux. */
		BufferedInputStream entree = null;
		BufferedOutputStream sortie = null;
		try {
			/* Ouvre les flux. */
			entree = new BufferedInputStream( part.getInputStream(), TAILLE_TAMPON );
			sortie = new BufferedOutputStream( new FileOutputStream( new File( chemin + nomFichier ) ),
					TAILLE_TAMPON );

			/*
			 * Lit le fichier reçu et écrit son contenu dans un fichier sur le
			 * disque.
			 */
			byte[] tampon = new byte[TAILLE_TAMPON];
			int longueur;
			while ( ( longueur = entree.read( tampon ) ) > 0 ) {
				sortie.write( tampon, 0, longueur );
			}
		} finally {
			System.out.println("YO");
		}
	}

	private String getValeur( Part part ) throws IOException {
		BufferedReader reader = new BufferedReader( new InputStreamReader( part.getInputStream(), "UTF-8" ) );
		StringBuilder valeur = new StringBuilder();
		char[] buffer = new char[1024];
		int longueur = 0;
		while ( ( longueur = reader.read( buffer ) ) > 0 ) {
			valeur.append( buffer, 0, longueur );
		}
		return valeur.toString();
	}

	private static String getNomFichier( Part part ) {
		/* Boucle sur chacun des paramètres de l'en-tête "content-disposition". */
		for ( String contentDisposition : part.getHeader( "content-disposition" ).split( ";" ) ) {
			/* Recherche de l'éventuelle présence du paramètre "filename". */
			if ( contentDisposition.trim().startsWith( "filename" ) ) {
				/*
				 * Si "filename" est présent, alors renvoi de sa valeur,
				 * c'est-à-dire du nom de fichier sans guillemets.
				 */
				return contentDisposition.substring( contentDisposition.indexOf( '=' ) + 1 ).trim().replace( "\"", "" );
			}
		}
		/* Et pour terminer, si rien n'a été trouvé... */
		return null;
	}
}