/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.lapepite.db.utils;

import fr.lapepite.javabean.Designer;
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
public class DBDesignerUtils {

	private final static String QUERY_FIND_ALL_DESIGNER = "SELECT * FROM designer";
	private final static String QUERY_FIND_ONE_DESIGNER = "SELECT * FROM Designer WHERE id_designer = ?";
	private static final String QUERY_INSERT_DESIGNER = "INSERT INTO Designer VALUES (null, ?, ?)";
	private final static String QUERY_DELETE_DESIGNER = "DELETE FROM Designer WHERE id_designer = ?";
	private final static String QUERY_UPDATE_DESIGNER = "UPDATE Designer SET nom_designer = ?, description_designer=? WHERE id_designer= ?";

	public static ArrayList<Designer> selectAllDesigners() throws Exception {

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



	public static Designer selectOneDesignerById(int id_designer) throws Exception {
		Designer designer = new Designer();

		Connection con = null;
		PreparedStatement stmt = null;

		try {

			con = ConnexionJDBC.getConnection();

			stmt = con.prepareStatement(QUERY_FIND_ONE_DESIGNER);

			stmt.setInt(1, id_designer);

			ResultSet rset = stmt.executeQuery();

			while (rset.next()) {

				designer = rsetToDesigner(rset);

			}

			return designer;

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

	public static void updateDesigner(Designer designer) throws Exception {
		Connection con = null;

		PreparedStatement stmtCategory = null;

		try {

			con = ConnexionJDBC.getConnection();

			stmtCategory = con.prepareStatement(QUERY_UPDATE_DESIGNER);

			stmtCategory.setString(1, designer.getNom_designer());

			stmtCategory.setString(2,designer.getDescription_designer());
			
			stmtCategory.setInt(3,  designer.getId_designer());

			stmtCategory.executeUpdate();			

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

	public static void insertDesigner(Designer designer) throws Exception {
		Connection con = null;
		PreparedStatement stmtBijoux = null;

		try {

			con = ConnexionJDBC.getConnection();

			stmtBijoux = con.prepareStatement(QUERY_INSERT_DESIGNER);

			stmtBijoux.setString(1, designer.getNom_designer());
			
			stmtBijoux.setString(2, designer.getDescription_designer());

			stmtBijoux.executeUpdate();



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

	public static void deleteDesigner(Designer designer) throws Exception {
		
		Connection con = null;
		PreparedStatement stmtBijoux = null;

		try {

			con = ConnexionJDBC.getConnection();

			stmtBijoux = con.prepareStatement(QUERY_DELETE_DESIGNER);

			stmtBijoux.setInt(1, designer.getId_designer());

			stmtBijoux.executeUpdate();			

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
	
	public static Designer rsetToDesigner(final ResultSet rSet) throws SQLException{

		final Designer designer = new Designer();

		designer.setId_designer(rSet.getInt("id_designer"));
		designer.setNom_designer(rSet.getString("nom_designer"));
		designer.setDescription_designer(rSet.getString("description_designer"));

		return designer;
	}

}
