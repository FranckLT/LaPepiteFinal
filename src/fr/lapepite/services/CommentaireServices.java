package fr.lapepite.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sun.xml.internal.ws.util.StringUtils;

import fr.lapepite.db.utils.DBCommentairesUtils;
import fr.lapepite.javabean.Commentaire;
import fr.lapepite.javabean.Utilisateur;

public class CommentaireServices {

	public List<Commentaire> getCommentairesByProducts(HashMap<String, String> parametersList) throws SQLException, Exception {
		
		ArrayList<Commentaire> commentairesList = new ArrayList<>();
		int idBijoux = Integer.parseInt(parametersList.get("id"));
		
		try {
			
			commentairesList.addAll(DBCommentairesUtils.selectCommentairesByBijoux(idBijoux));
			
			return commentairesList;
			
		} catch (Exception e) {
			
			throw new Exception(e.getMessage());
			
		}

	}
	
	public void insertCommentaire(HashMap<String, String> parametersList, Utilisateur utilisateur) throws SQLException, Exception {
		
		Commentaire commentaire = new Commentaire();
		int idBijoux = Integer.parseInt(parametersList.get("idBijoux"));
		String texteCommentaire = StringUtils.capitalize(parametersList.get("commentaire"));
		
		commentaire.setTexte_commentaire(texteCommentaire);
		commentaire.setUtilisateur(utilisateur);
		
		try {
			
			if (itCodeContainsSpecialChars(texteCommentaire)) {
				DBCommentairesUtils.insertCommentaire(commentaire, idBijoux);
			} else {
				throw new Exception("Les caractères spéciaux sont interdits.");
			}
			
			
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
	}
	
	
	public static boolean itCodeContainsSpecialChars(String code) throws SQLException, Exception {
		
		final Pattern pattern = Pattern.compile("[\\&\\(\\§\\)\\^\\_\\$\\;\\*\\%\\`\\\\£\\=\\+\\/\\\\\\}\\{\\<\\>]");
		
		Matcher matcher = pattern.matcher(code);
		
		while (matcher.find()) {
			
			return false;
			
		}
		
		return true;
		
	}
	
}
