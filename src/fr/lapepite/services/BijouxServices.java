/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.lapepite.services;

import fr.lapepite.db.utils.DBBijouxUtils;
import fr.lapepite.javabean.Bijoux;
import fr.lapepite.javabean.Categorie;
import fr.lapepite.javabean.Designer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


public class BijouxServices{
	public static final String CHAMP_DESCRIPTION = "description";
	public static final String CHAMP_FICHIER = "fichier";
	public static final String CHEMIN = "chemin";
	public static final String CHEMIN_FICHIER = "/Users/Franck/projetWorkspace/LaPepite/WebContent/image/bijoux/";
	public static final String CHEMIN_FICHIER2 = "/Users/Franck/Desktop/apache-tomcat-9.0.10/work/Catalina/localhost/LaPepite/photo/";

	public static final int TAILLE_TAMPON = 10240;

	public List<Bijoux> getAll() throws Exception {
		List<Bijoux> listBijoux = new ArrayList<>();
		try {
			//requête en base 
			listBijoux = DBBijouxUtils.requestSelectAll();
			return listBijoux;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public int getNumberOfBijoux() throws Exception {

		List<Bijoux> listBijoux = new ArrayList<>();

		listBijoux.addAll(getAll());

		return listBijoux.size();

	}

	public Bijoux getOneBijoux(HashMap<String, String> parametersList) throws Exception {

		try {

			int idBijoux = Integer.parseInt(parametersList.get("id"));

			Bijoux bijoux = new Bijoux();

			bijoux = DBBijouxUtils.selectOneBijouxById(idBijoux);

			return bijoux;

		} catch (Exception ex) {
			
			throw new Exception(ex.getMessage());
			
		}
	}

	public List<Bijoux> getBijouxByDesigner(HashMap<String, String> parametersList) throws SQLException, Exception {

		List<Bijoux> bijouxList = new ArrayList<>();

		int idDesigner = Integer.parseInt(parametersList.get("id"));

		bijouxList = DBBijouxUtils.selectBijouxByDesigner(idDesigner);

		return bijouxList;


	}



	public void addOne(HashMap<String, String> parametersList, Part part) throws NumberFormatException, Exception {

		if (verifForm(parametersList)) {

			if (!verifIfNomAndRefBijouxAlreadyUsedForAdd(parametersList)) {

				//récup de tout les parametre 
				String nomBijoux = parametersList.get("nomBijoux");
				String refBijoux = parametersList.get("refBijoux");
				int prixBijoux = Integer.parseInt(parametersList.get("prixBijoux"));
				int stockBijoux = Integer.parseInt(parametersList.get("stockBijoux"));
				String description = parametersList.get("descriptionBijoux");
				int idDesignerBijoux = Integer.parseInt(parametersList.get("designerBijoux"));
				int idCategorieBijoux = Integer.parseInt(parametersList.get("categorieBijoux"));
				String imageBijoux = writeImage(part);
				
				
				//initialisation objets
				Bijoux bijoux = new Bijoux();
				Designer designer = new Designer();
				Categorie categorie = new Categorie();

				//set des objets
				designer.setId_designer(idDesignerBijoux);
				categorie.setId_categorie(idCategorieBijoux);

				bijoux
				.setNom_bijoux(nomBijoux)
				.setRef_bijoux(refBijoux)
				.setPrix_bijoux(prixBijoux)
				.setStock_bijoux(stockBijoux)
				.setDescription_bijoux(description)
				.setDesigner(designer)
				.setCategorie(categorie)
				.setImage_bijoux(imageBijoux);

				//ajout à la BDD
				DBBijouxUtils.insertBijoux(bijoux);

			}
		}

	}

	public void deleteBijoux(HashMap<String, String> parametersList) throws Exception {

		int idBijoux = Integer.parseInt(parametersList.get("idToDelete"));

		Bijoux bijoux = new Bijoux();

		bijoux.setId_bijoux(idBijoux);

		try {
			DBBijouxUtils.deleteBijoux(bijoux);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}



	}


	public void updateOne(HashMap<String, String> parametersList) throws Exception {

		int idBijoux = Integer.parseInt(parametersList.get("id"));
		String nomBijoux = parametersList.get("nomBijoux");
		String refBijoux = parametersList.get("refBijoux");
		int prixBijoux = Integer.parseInt(parametersList.get("prixBijoux"));
		int stockBijoux = Integer.parseInt(parametersList.get("stockBijoux"));
		String description = parametersList.get("descriptionBijoux");
		int idDesignerBijoux = Integer.parseInt(parametersList.get("designerBijoux"));
		int idCategorieBijoux = Integer.parseInt(parametersList.get("categorieBijoux"));

		//initialisation objets
		Bijoux bijoux = new Bijoux();
		Designer designer = new Designer();
		Categorie categorie = new Categorie();

		//set des objets
		designer.setId_designer(idDesignerBijoux);
		categorie.setId_categorie(idCategorieBijoux);

		bijoux.setId_bijoux(idBijoux)
		.setNom_bijoux(nomBijoux)
		.setRef_bijoux(refBijoux)
		.setPrix_bijoux(prixBijoux)
		.setStock_bijoux(stockBijoux)
		.setDescription_bijoux(description)
		.setDesigner(designer)
		.setCategorie(categorie);

		try {

			if (!verifIfNomAndRefBijouxAlreadyUsed(parametersList)) {

				//ajout à la BDD
				DBBijouxUtils.updateBijoux(bijoux);

			} 

		} catch (Exception e) {

			throw new Exception(e.getMessage());
		}

	}


	public static boolean verifIfNomAndRefBijouxAlreadyUsed(HashMap<String, String> parametersList) throws Exception{

		String nomBijoux = parametersList.get("nomBijoux");

		String refBijoux = parametersList.get("refBijoux");

		List<Bijoux> listNomBijoux = new ArrayList<>();

		listNomBijoux.addAll(DBBijouxUtils.requestSelectAll());

		for (Bijoux bijoux : listNomBijoux) {
				if (bijoux.getId_bijoux() != Integer.parseInt(parametersList.get("id"))) {
					if (bijoux.getNom_bijoux().equals(nomBijoux)) {
						throw new Exception("Nom déjà utilisé.");
					}
					if (bijoux.getRef_bijoux().equals(refBijoux)) {
						throw new Exception("Ref déjà utilisé.");
					}
				}
		}

		return false;

	}
	
	public static boolean verifIfNomAndRefBijouxAlreadyUsedForAdd(HashMap<String, String> parametersList) throws Exception{

		String nomBijoux = parametersList.get("nomBijoux");

		String refBijoux = parametersList.get("refBijoux");

		List<Bijoux> listNomBijoux = new ArrayList<>();

		listNomBijoux.addAll(DBBijouxUtils.requestSelectAll());

		for (Bijoux bijoux : listNomBijoux) {
					if (bijoux.getNom_bijoux().equals(nomBijoux)) {
						throw new Exception("Nom déjà utilisé.");
					}
					if (bijoux.getRef_bijoux().equals(refBijoux)) {
						throw new Exception("Ref déjà utilisé.");
					}
		}

		return false;

	}

	public static boolean verifForm(HashMap<String, String> parametersList) throws Exception {

		Set<String> keysList = parametersList.keySet();

		for (String parameterName : keysList) {

			if ("".equals(parametersList.get(parameterName))) {

				throw new Exception("Le champ " +parameterName+" ne peut pas être nul.");

			}

		}

		return true;
	}
	
	protected String writeImage(Part part) throws Exception {

		/*
		 * Lecture du paramètre 'chemin' passé à la servlet via la déclaration
		 * dans le web.xml
		 */
		String chemin = CHEMIN;

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
			ecrireFichier( part, nomFichier, CHEMIN_FICHIER);
		}
		
		return nomFichier;

	}

	/*
	 * Méthode utilitaire qui a pour but d'écrire le fichier passé en paramètre
	 * sur le disque, dans le répertoire donné et avec le nom donné.
	 */
	@SuppressWarnings("resource")
	private void ecrireFichier( Part part, String nomFichier, String chemin ) throws Exception {
		/* Prépare les flux. */
		BufferedInputStream entree = null;
		BufferedOutputStream sortie = null;
		try {
			/* Ouvre les flux. */
			entree = new BufferedInputStream( part.getInputStream(), TAILLE_TAMPON );
			File imageFile = new File( chemin + nomFichier ) ;
			sortie = new BufferedOutputStream( new FileOutputStream( imageFile ), TAILLE_TAMPON );
			
			/*
			 * Lit le fichier reçu et écrit son contenu dans un fichier sur le
			 * disque.
			 */
			byte[] tampon = new byte[TAILLE_TAMPON];
			int longueur;
			while ( ( longueur = entree.read( tampon ) ) > 0 ) {
				sortie.write( tampon, 0, longueur );
			
		} 
			
		}
		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
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
