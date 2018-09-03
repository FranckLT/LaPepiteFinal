/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.lapepite.db.utils;

import fr.lapepite.javabean.Panier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBPanierUtils {

	private final static String QUERY_INSERT_PANIER = "INSERT INTO Panier VALUES ((?), (?), null)";

	public static void insertPanier( Panier panier ) throws Exception, SQLException {

		Connection con = null;
		PreparedStatement stmtPanier = null;

		try {

			// Relative instruction to work with Tomcat and Mysql
			con = ConnexionJDBC.getConnection();

			stmtPanier = con.prepareStatement(QUERY_INSERT_PANIER);

			stmtPanier.setInt(1, panier.getId_panier());

			stmtPanier.setInt(2, panier.getId_panier());

			stmtPanier.executeUpdate();

		} catch(Exception e){

			throw new Exception("Une erreur c'est produite lors de la MAJ des données");

			
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
