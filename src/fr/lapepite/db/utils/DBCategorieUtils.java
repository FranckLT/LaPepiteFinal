/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.lapepite.db.utils;

import fr.lapepite.javabean.Categorie;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class DBCategorieUtils {
    
        private final static String QUERY_FIND_ALL_CATEGORIES = "SELECT * FROM categorie";

    
    public static ArrayList<Categorie> requestSelect() {
		
		ArrayList<Categorie> listCategories=new ArrayList<>();
		
		Connection con = null;
		Statement stmt = null;
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con = DriverManager.getConnection(ConnexionJDBC.URL, ConnexionJDBC.LOGIN, ConnexionJDBC.PASSWORD); //La connexion
			stmt = con.createStatement();
			ResultSet rset = stmt.executeQuery(QUERY_FIND_ALL_CATEGORIES);
			
			while (rset.next()) {
				Categorie categorie = rsetToCategorie(rset);
				listCategories.add(categorie);
			}
			
			return listCategories;
		}
		catch (final SQLException e) {
			e.printStackTrace();
			return listCategories;
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
    
    private static Categorie rsetToCategorie(final ResultSet rSet) throws SQLException{
		
		final Categorie categorie = new Categorie();
                
                categorie.setId_categorie(rSet.getInt("id_categorie"));
                categorie.setNom_categorie(rSet.getString("nom_categorie"));
		
		return categorie;
	}

}
