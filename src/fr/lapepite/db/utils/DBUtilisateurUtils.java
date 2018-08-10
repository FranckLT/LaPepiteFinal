/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.lapepite.db.utils;

import fr.lapepite.javabean.Panier;
import fr.lapepite.javabean.Utilisateur;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class DBUtilisateurUtils {
    
    private final static String QUERY_FIND_ID_WITH_USER_NAME = "SELECT * FROM utilisateur INNER JOIN panier on panier.id_utilisateur = utilisateur.id_utilisateur WHERE nom = (?)";
    private final static String QUERY_FIND_UTILISATEUR_BY_EMAIL = "SELECT * FROM utilisateur LEFT JOIN panier on panier.id_utilisateur = utilisateur.id_utilisateur WHERE mail_user = (?)";
    private final static String QUERY_INSERT_UTILISATEUR = "INSERT INTO utilisateur VALUES(null, (?),(?),(?),(?),(?),(?))";
    private final static String QUERY_FIND_ALL_EMAIL_UTILISATEUR = "SELECT mail_user FROM utilisateur";

    
    public static int selectIdFromUser(String nom) throws Exception, SQLException {
		Connection con = null;
		PreparedStatement stmt = null;

		try {
	
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con = DriverManager.getConnection(ConnexionJDBC.URL, ConnexionJDBC.LOGIN, ConnexionJDBC.PASSWORD);

			// takes the name and returns an id
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
	
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con = DriverManager.getConnection(ConnexionJDBC.URL, ConnexionJDBC.LOGIN, ConnexionJDBC.PASSWORD);

			// takes the name and returns an id
			stmt = con.prepareStatement(QUERY_FIND_UTILISATEUR_BY_EMAIL);
			stmt.setString(1, utilisateur.getEmail());
			ResultSet rSet = stmt.executeQuery();
			if (rSet.next()) {
                            listUtilisateur.add(rsetToUserAndPanier(rSet));
			} else {
				throw new Exception("L'email : " + utilisateur.getEmail() + " n'existe pas");
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
               // Reporter reporter = fr.france_presse.db.method.Method.isReporterExists(dispatch.getReporterName()).get(0);
	
		try {
                    
			// Relative instruction to work with Tomcat and Mysql
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con = DriverManager.getConnection(ConnexionJDBC.URL, ConnexionJDBC.LOGIN, ConnexionJDBC.PASSWORD);
                        
			stmtUtilisateur = con.prepareStatement(QUERY_INSERT_UTILISATEUR);
	
                        stmtUtilisateur.setString(1, utilisateur.getNom());
                        stmtUtilisateur.setString(2, utilisateur.getPrenom());
                        stmtUtilisateur.setString(3, utilisateur.getAdresse());
                        stmtUtilisateur.setString(4, utilisateur.getEmail());
                        stmtUtilisateur.setBoolean(5, false);
                        stmtUtilisateur.setString(6, utilisateur.getPassword());
                        
                   
                        stmtUtilisateur.executeUpdate();
			
		} catch(Exception e){
                    
                    
                }finally {
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
    
    public static List<String> requestSelectAllEmailUtilisateur() {
		
		List<String> listEmailUtilisateurs=new ArrayList<>();
		
		Connection con = null;
		Statement stmt = null;
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con = DriverManager.getConnection(ConnexionJDBC.URL, ConnexionJDBC.LOGIN, ConnexionJDBC.PASSWORD); //La connexion
			stmt = con.createStatement();
			ResultSet rset = stmt.executeQuery(QUERY_FIND_ALL_EMAIL_UTILISATEUR);
			
			while (rset.next()) {
				String email = rset.getString("mail_user");
				listEmailUtilisateurs.add(email);
			}
			
			return listEmailUtilisateurs;
		}
		catch (final SQLException e) {
			e.printStackTrace();
			return listEmailUtilisateurs;
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
		
                user.setId(rSet.getInt("id_utilisateur"));
                user.setNom(rSet.getString("nom_user"));
                user.setPrenom(rSet.getString("prenom_user"));
                user.setAdresse(rSet.getString("adresse_user"));
                user.setEmail(rSet.getString("mail_user"));
                user.setAdmin(rSet.getBoolean("admin"));
                user.setPassword(rSet.getString("password"));
                
                user.setPanier(rsetToPanier(rSet));
                
		return user;
	}
    
    private static Utilisateur rsetToUser( ResultSet rSet) throws SQLException{
		
                Utilisateur user = new Utilisateur();
		
                user.setId(rSet.getInt("id_utilisateur"));
                user.setNom(rSet.getString("nom_user"));
                user.setPrenom(rSet.getString("prenom_user"));
                user.setAdresse(rSet.getString("adresse_user"));
                user.setEmail(rSet.getString("mail_user"));
                user.setAdmin(rSet.getBoolean("admin"));
                user.setPassword(rSet.getString("password"));
                
		return user;
	}
   
   
    public static Panier rsetToPanier(ResultSet rset) throws SQLException{
        Panier panier = new Panier();
        panier.setId_panier(rset.getInt("id_panier"));
        return panier;
    }

}
