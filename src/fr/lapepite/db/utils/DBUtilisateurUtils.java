/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.lapepite.db.utils;

import fr.lapepite.javabean.Panier;
import fr.lapepite.javabean.Utilisateur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DBUtilisateurUtils {

	private final static String QUERY_FIND_ID_WITH_USER_NAME = "SELECT * FROM Utilisateur INNER JOIN panier on panier.id_utilisateur = utilisateur.id_utilisateur WHERE nom_utilisateur = (?)";
	
	private final static String QUERY_FIND_UTILISATEUR_BY_EMAIL = "SELECT * FROM Utilisateur WHERE mail_utilisateur = (?)";
	
	private final static String QUERY_INSERT_UTILISATEUR = "INSERT INTO Utilisateur VALUES (null, ?,?,?,?,?,?, null)";
	
	private final static String QUERY_FIND_ALL_EMAIL_UTILISATEUR = "SELECT mail_utilisateur FROM utilisateur";


	public static int selectIdFromUser(String nom) throws Exception, SQLException {

		Connection con = null;
		PreparedStatement stmt = null;

		try {

			con = ConnexionJDBC.getConnection();

			stmt = con.prepareStatement(QUERY_FIND_ID_WITH_USER_NAME);

			stmt.setString(1, nom);

			ResultSet rSet = stmt.executeQuery();

			if (rSet.next()) {
				int id = rSet.getInt("id");
				return id;
			} else {
				throw new Exception("L'utilisateur : " + nom + " n'existe pas");
			}
		} finally {
			// Close the connection
			if (con != null) {
				try {
					con.close();
				} catch (final SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static Utilisateur selectUtilisateurByEmail(Utilisateur utilisateur) throws Exception, SQLException {

		Connection con = null;
		PreparedStatement stmt = null;

		List<Utilisateur> listUtilisateur = new ArrayList<Utilisateur>();

		try {

			con = ConnexionJDBC.getConnection();

			stmt = con.prepareStatement(QUERY_FIND_UTILISATEUR_BY_EMAIL);
			stmt.setString(1, utilisateur.getMail_utilisateur());
			ResultSet rSet = stmt.executeQuery();
			if (rSet.next()) {
				listUtilisateur.add(rsetToUser(rSet));
			} else {
				throw new Exception("L'email : " + utilisateur.getMail_utilisateur() + " n'existe pas");
			}
			return listUtilisateur.get(0);
		} finally {
			// Close the connection
			if (con != null) {
				try {
					con.close();
				} catch (final SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void insertUtilisateur( Utilisateur utilisateur ) throws Exception, SQLException {

		Connection con = null;
		PreparedStatement stmtUtilisateur = null;

		try {

			con = ConnexionJDBC.getConnection();

			stmtUtilisateur = con.prepareStatement(QUERY_INSERT_UTILISATEUR);

			stmtUtilisateur.setString(1, utilisateur.getNom_utilisateur());
			stmtUtilisateur.setString(2, utilisateur.getPrenom_utilisateur());
			
			stmtUtilisateur.setString(3, utilisateur.getAdresse_utilisateur());
			
			stmtUtilisateur.setString(4, utilisateur.getMail_utilisateur());
			
			stmtUtilisateur.setBoolean(5, utilisateur.isAdmin());
			stmtUtilisateur.setString(6, utilisateur.getPassword_utilisateur());

			stmtUtilisateur.executeUpdate();

		} catch(Exception e){
			
			throw new Exception("L'utilisateur n'a pu être enregistré.");

		}finally {
			if (con != null) {
				try {
					con.close();
				} catch (final SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static List<String> requestSelectAllEmailUtilisateur() throws Exception {

		List<String> listEmailUtilisateurs=new ArrayList<>();

		Connection con = null;
		Statement stmt = null;
		try {

			con = ConnexionJDBC.getConnection();

			stmt = con.createStatement();

			ResultSet rset = stmt.executeQuery(QUERY_FIND_ALL_EMAIL_UTILISATEUR);

			while (rset.next()) {
				String email = rset.getString("mail_utilisateur");
				listEmailUtilisateurs.add(email);
			}

			return listEmailUtilisateurs;
		}
		catch (final SQLException e) {
			throw new Exception("Une erreur c'est produite lors de la récupération des données");

		}
		finally {

			if (con != null) {
				try {
					con.close();
				} catch (final SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}


	private static Utilisateur rsetToUserAndPanier(final ResultSet rSet) throws SQLException{

		Utilisateur user = new Utilisateur();

		user.setId_utilisateur(rSet.getInt("id_utilisateur"));
		user.setNom_utilisateur(rSet.getString("nom_user"));
		user.setPrenom_utilisateur(rSet.getString("prenom_user"));
		user.setAdresse_utilisateur(rSet.getString("adresse_user"));
		user.setMail_utilisateur(rSet.getString("mail_user"));
		user.setAdmin(rSet.getBoolean("admin"));
		user.setPassword_utilisateur(rSet.getString("password"));

		user.setPanier(rsetToPanier(rSet));

		return user;
	}

	public static Utilisateur rsetToUser( ResultSet rSet) throws SQLException{

		Utilisateur user = new Utilisateur();

		user.setId_utilisateur(rSet.getInt("id_utilisateur"));
		user.setNom_utilisateur(rSet.getString("nom_utilisateur"));
		user.setPrenom_utilisateur(rSet.getString("prenom_utilisateur"));
		user.setAdresse_utilisateur(rSet.getString("adresse_utilisateur"));
		user.setMail_utilisateur(rSet.getString("mail_utilisateur"));
		user.setAdmin(rSet.getBoolean("admin"));
		user.setPassword_utilisateur(rSet.getString("password_utilisateur"));

		return user;

	}


	public static Panier rsetToPanier(ResultSet rset) throws SQLException{
		Panier panier = new Panier();
		panier.setId_panier(rset.getInt("id_panier"));
		return panier;
	}

}
