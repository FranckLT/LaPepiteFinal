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

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class DBPanierUtils {
    
    private final static String QUERY_INSERT_PANIER = "INSERT INTO panier VALUES (null, (?))";
    
     public static void insertPanier( Utilisateur utilisateur ) throws Exception, SQLException {

		Connection con = null;
		PreparedStatement stmtPanier = null;
	
		try {
                    
			// Relative instruction to work with Tomcat and Mysql
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con = DriverManager.getConnection(ConnexionJDBC.URL, ConnexionJDBC.LOGIN, ConnexionJDBC.PASSWORD);
                        
			stmtPanier = con.prepareStatement(QUERY_INSERT_PANIER);
	
                        stmtPanier.setInt(1, utilisateur.getId());
                        
                        stmtPanier.executeUpdate();
			
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
     
     public static Panier rsetToPanier(ResultSet rSet) throws SQLException{
         
         Panier panier = new Panier();
         
         panier.setId_panier(rSet.getInt("id_panier"));
         
         return panier;
         
     }

}
