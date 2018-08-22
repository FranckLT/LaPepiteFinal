/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.lapepite.db.utils;

import fr.lapepite.javabean.Designer;
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
public class DBDesignerUtils {
    
    private final static String QUERY_FIND_ALL_DESIGNER = "SELECT * FROM designer";
    
    public static ArrayList<Designer> requestSelect() {
		
		ArrayList<Designer> listDesigner=new ArrayList<>();
		
		Connection con = null;
		Statement stmt = null;
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con = DriverManager.getConnection(ConnexionJDBC.URL, ConnexionJDBC.LOGIN, ConnexionJDBC.PASSWORD); //La connexion
			stmt = con.createStatement();
			ResultSet rset = stmt.executeQuery(QUERY_FIND_ALL_DESIGNER);
			
			while (rset.next()) {
				Designer designer = rsetToDesigner(rset);
				listDesigner.add(designer);
			}
			
			return listDesigner;
		}
		catch (final SQLException e) {
			e.printStackTrace();
			return listDesigner;
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
    
    public static Designer rsetToDesigner(final ResultSet rSet) throws SQLException{
		
		final Designer designer = new Designer();
                
                designer.setId_designer(rSet.getInt("id_designer"));
                designer.setNom_designer(rSet.getString("nom_designer"));
                designer.setDescription_designer(rSet.getString("description_designer"));
		
		return designer;
	}

}
