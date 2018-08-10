/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.lapepite.db.utils;

import fr.lapepite.javabean.Bijoux;
import fr.lapepite.javabean.LignePanier;
import fr.lapepite.javabean.Utilisateur;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class DBLignePanierUtils {
    
     private final static String QUERY_INSERT_LIGNE_PANIER = "INSERT INTO ligne_panier VALUES (null, (?), (?), (?))";
     private final static String QUERY_SELECT_LIGNE_PANIER = "SELECT * from ligne_panier INNER JOIN bijoux ON bijoux.id_bijoux = ligne_panier.id_bijoux WHERE id_panier=(?)";
     private final static String TEST = "SELECT * from ligne_panier WHERE id_panier=(?)";

    
     public static void insertPanier( LignePanier lignePanier, int idPanier ) throws Exception, SQLException {

		Connection con = null;
		PreparedStatement stmtLignePanier = null;
	
		try {
                    
			// Relative instruction to work with Tomcat and Mysql
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con = DriverManager.getConnection(ConnexionJDBC.URL, ConnexionJDBC.LOGIN, ConnexionJDBC.PASSWORD);
                        
			stmtLignePanier = con.prepareStatement(QUERY_INSERT_LIGNE_PANIER);
	
                        stmtLignePanier.setInt(1, lignePanier.getBijoux().getId());
                        stmtLignePanier.setInt(2, idPanier);
                        stmtLignePanier.setInt(3, lignePanier.getQuantite());
                        
                        stmtLignePanier.executeUpdate();
			
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

     
     public static ArrayList<LignePanier> requestSelect() throws Exception {
		
		ArrayList<LignePanier> listLignePanier=new ArrayList<>();
		
		Connection con = null;
		Statement stmt = null;
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con = DriverManager.getConnection(ConnexionJDBC.URL, ConnexionJDBC.LOGIN, ConnexionJDBC.PASSWORD); //La connexion
			stmt = con.createStatement();
			ResultSet rset = stmt.executeQuery(TEST);
			
                        while (rset.next()) {
                            Bijoux bijoux = DBBijouxUtils.selectOneBijouxById(rset.getInt("id_bijoux"));
                            listLignePanier.add(rsetToLignePanier(rset, bijoux));
                        }
			
			return listLignePanier;
		}
		catch (final SQLException e) {
			e.printStackTrace();
			return listLignePanier;
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
	
     public static LignePanier rsetToLignePanier(ResultSet rset, Bijoux bijoux) throws SQLException{
         
         LignePanier lignePanier = new LignePanier();
         
         lignePanier.setBijoux(bijoux);
         
         lignePanier.setQuantite(rset.getInt("quantite"));
         
         return lignePanier;
         
     }
}
