/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.lapepite.services;

import fr.lapepite.db.utils.DBPanierUtils;
import fr.lapepite.db.utils.DBUtilisateurUtils;
import fr.lapepite.javabean.LignePanier;
import fr.lapepite.javabean.Panier;
import fr.lapepite.javabean.Utilisateur;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import com.sun.xml.internal.ws.util.StringUtils;
import org.mindrot.jbcrypt.BCrypt;

public class UtilisateurServices {

	public static void addUtilisateur(Utilisateur utilisateur) throws Exception {

		try {
			DBUtilisateurUtils.insertUtilisateur(utilisateur);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

	public List<Utilisateur> getAllUtilisateurs() throws Exception {

		List<Utilisateur> utilisateursList = new ArrayList<>();

		try {

			utilisateursList.addAll(DBUtilisateurUtils.selectAllUtilisateurs());

			return utilisateursList;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public Utilisateur logUtilisateur(HashMap<String, String> parametersList)
			throws ServletException, IOException, Exception {
		Utilisateur utilisateur = new Utilisateur();

		if (verifLoginForm(parametersList)) {

			try {

				utilisateur = getUtilisateurByEmail(parametersList);

			} catch (Exception e) {

				throw new Exception(e.getMessage());

			}

			String passwordFromForm = parametersList.get("password");

			if (testIfPasswordMatch(passwordFromForm, utilisateur)) {

				Panier panier = new Panier();

				utilisateur.setPanier(panier);

				return utilisateur;

			} else {

				throw new Exception("Le mot de passe ne correspond pas.");

			}

		}

		return null;

	}

	public Utilisateur registerUser(HashMap<String, String> parametersList) throws Exception {

		try {

			if (verifRegisterForm(parametersList)) {

				if (!verifIfEmailAlreadyUsed(parametersList)) {

					// Recup des infos
					String email = parametersList.get("email");
					String password = parametersList.get("password");
					String nom = parametersList.get("nom");
					nom = StringUtils.capitalize(nom);
					String prenom = parametersList.get("prenom");
					prenom = StringUtils.capitalize(prenom);
					String adresse = parametersList.get("adresse");

					// création user
					Utilisateur utilisateur = new Utilisateur();

					// Hachage password
					password = BCrypt.hashpw(password, BCrypt.gensalt());

					// set de l'utilisateur
					utilisateur.setNom_utilisateur(nom).setPrenom_utilisateur(prenom).setMail_utilisateur(email)
							.setAdresse_utilisateur(adresse).setAdmin(false).setPassword_utilisateur(password);

					// enregistrement en base
					addUtilisateur(utilisateur);

					// récupération utilisateur en BDD
					utilisateur = DBUtilisateurUtils.selectUtilisateurByEmail(utilisateur);

					// Après récupértion de l'id de l'utilisateur on enregistre son panier
					PanierServices panierServices = new PanierServices();

					Panier panier = new Panier();

					panier.setId_panier(utilisateur.getId_utilisateur());

					panierServices.insertPanier(panier);

					utilisateur.setPanier(panier);

					return utilisateur;

				} else {

					throw new Exception("Email deja utilisé");

				}

			}
		} catch (Exception e) {

			throw new Exception(e.getMessage());
		}
		return null;

	}

	public Utilisateur updateUtilisateur(Utilisateur utilisateur, HashMap<String, String> parametersList)
			throws Exception {

		try {

			if (verifEditForm(parametersList)) {

				// Recup des infos
				String email = parametersList.get("mailUtilisateur");
				String nom = parametersList.get("nomUtilisateur");
				nom = StringUtils.capitalize(nom);
				String prenom = parametersList.get("prenomUtilisateur");
				prenom = StringUtils.capitalize(prenom);
				String adresse = parametersList.get("adresseUtilisateur");

				// set de l'utilisateur
				utilisateur.setNom_utilisateur(nom).setPrenom_utilisateur(prenom).setMail_utilisateur(email)
						.setAdresse_utilisateur(adresse);

				DBUtilisateurUtils.updateUtilisateur(utilisateur);

			}

			String password = parametersList.get("passwordUtilisateur");
			String newPassword1 = parametersList.get("newpassword1");
			String newPassword2 = parametersList.get("newpassword2");

			if (!password.isEmpty()) {
				if (isPasswordOK(utilisateur, password, newPassword1, newPassword2)) {

					password = BCrypt.hashpw(newPassword1, BCrypt.gensalt());

					utilisateur.setPassword_utilisateur(password);

					DBUtilisateurUtils.updatePasswordUtilisateur(utilisateur);

				}
			}

			return utilisateur;

		} catch (Exception e) {

			throw new Exception(e.getMessage());
		}

	}

	public boolean isPasswordOK(Utilisateur utilisateur, String password, String newPassword1, String newPassword2)
			throws Exception {

		if (testIfPasswordMatch(password, utilisateur)) {
			if (newPassword1.equals(newPassword2)) {
				return true;
			} else {
				throw new Exception("Les 2 nouveaux mot de passe ne correspondent pas.");
			}
		} else {
			throw new Exception("L'ancien mot de passe ne correspond pas.");
		}
	}

	public Utilisateur getUtilisateurByEmail(HashMap<String, String> parametersList) throws Exception {
		try {

			String parameterFromForm = parametersList.get("email");

			Utilisateur utilisateurFromForm = new Utilisateur();
	
			utilisateurFromForm.setMail_utilisateur(parameterFromForm);

			Utilisateur utilisateurFromDB = DBUtilisateurUtils.selectUtilisateurByEmail(utilisateurFromForm);

			return utilisateurFromDB;

		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}
	}
	
	public Utilisateur getUtilisateurById(HashMap<String, String> parametersList) throws Exception {
		try {

			int parameterFromForm = Integer.parseInt(parametersList.get("id"));

			Utilisateur utilisateurFromDB = DBUtilisateurUtils.selectUtilisateurById(parameterFromForm);

			return utilisateurFromDB;

		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}
	}

	public void deleteUtilisateur(HashMap<String, String> parametersList) throws Exception {

		int idUtilisateur = Integer.parseInt(parametersList.get("idToDelete"));

		try {

			DBPanierUtils.deletePanier(idUtilisateur);
			
			DBUtilisateurUtils.deleteUtilisateur(idUtilisateur);

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

	public void adminUtilisateur(HashMap<String, String> parametersList) throws Exception {

		Utilisateur utilisateur = getUtilisateurById(parametersList);

		if (utilisateur.isAdmin()) {
			utilisateur.setAdmin(false);
		} else {
			utilisateur.setAdmin(true);
		}
		try {

			DBUtilisateurUtils.adminUtilisateur(utilisateur);
		} catch (Exception e) {
			throw new Exception(e.getMessage());

		}

	}

	public boolean testIfPasswordMatch(String password, Utilisateur utilisateur) {

		boolean testIfPasswordMatch = BCrypt.checkpw(password, utilisateur.getPassword_utilisateur());

		if (testIfPasswordMatch) {
			return true;
		} else {
			return false;
		}
	}

	public boolean verifLoginForm(HashMap<String, String> parametersList) throws Exception {

		Set<String> keysList = parametersList.keySet();

		for (String parameterName : keysList) {

			if ("".equals(parametersList.get(parameterName))) {

				throw new Exception("Le champ " + parameterName + " ne peut pas être nul.");

			}

		}

		return true;

	}

	public boolean verifRegisterForm(HashMap<String, String> parametersList) throws Exception {

		Set<String> keysList = parametersList.keySet();

		for (String parameterName : keysList) {

			if (parametersList.get(parameterName).isEmpty()) {

				throw new Exception("Le champ " + parameterName + " ne peut pas être nul.");

			} else if (!parameterName.equals("email") && vérifCaracSpéciaux(parametersList.get(parameterName))) {

				throw new Exception("N'utilisez pas de caractères spéciaux pour le champ : " + parameterName);

			}

		}

		return true;

	}

	public boolean verifEditForm(HashMap<String, String> parametersList) throws Exception {

		Set<String> keysList = parametersList.keySet();

		for (String parameterName : keysList) {

			if (parametersList.get(parameterName).isEmpty() && !parameterName.contains("password")) {

				throw new Exception("Le champ " + parameterName + " ne peut pas être nul.");

			} else if (!parameterName.equals("mailUtilisateur") && vérifCaracSpéciaux(parametersList.get(parameterName))
					&& !parameterName.contains("password")) {

				throw new Exception("N'utilisez pas de caractères spéciaux pour le champ : " + parameterName);

			}

		}

		return true;

	}

	public boolean verifIfEmailAlreadyUsed(HashMap<String, String> parametersList) throws Exception {

		// recup email
		String emailForm = parametersList.get("email");

		List<String> listEmail = new ArrayList<>();
		try {
			listEmail.addAll(DBUtilisateurUtils.requestSelectAllEmailUtilisateur());

			// on boucle sur la liste de tout les emails de la base ppur voir si
			// il existe deja
			for (String email : listEmail) {

				if (email.equals(emailForm)) {

					return true;

				}
			}

			return false;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

	public boolean verifIfPanierIsEmpty(Utilisateur utilisateur) {

		Panier panier = utilisateur.getPanier();

		List<LignePanier> listLignePanier = panier.getListProduit();

		if (listLignePanier.isEmpty()) {
			return true;
		}
		return false;
	}

	public boolean vérifCaracSpéciaux(String string) {
		Pattern p = Pattern.compile("^(\\p{Alnum})+$");
		Matcher m = p.matcher(string);
		if (m.find()) {
			return false;
		}

		else {
			return true;
		}
	}

}
