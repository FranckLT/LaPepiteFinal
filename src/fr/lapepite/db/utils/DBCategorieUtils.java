/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.lapepite.db.utils;

import fr.lapepite.javabean.Categorie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



public class DBCategorieUtils {

	private final static String QUERY_FIND_ALL_CATEGORIES = "SELECT * FROM Categorie";
	private final static String QUERY_FIND_ONE_CATEGORY = "SELECT * FROM Categorie WHERE id_categorie = ?";
	private static final String QUERY_INSERT_CATEGORY = "INSERT INTO Categorie VALUES (null, ?)";
	private final static String QUERY_DELETE_CATEGORY = "DELETE FROM Categorie WHERE id_categorie = ?";
	private final static String QUERY_UPDATE_CATEGORY = "UPDATE Categorie SET nom_categorie = ? WHERE id_categorie= ?";

	public static ArrayList<Categorie> selectAllCategories() throws Exception {

		ArrayList<Categorie> listCategories=new ArrayList<>();

		Connection con = null;
		Statement stmt = null;
		try {

			con = ConnexionJDBC.getConnection();

			stmt = con.createStatement();
			ResultSet rset = stmt.executeQuery(QUERY_FIND_ALL_CATEGORIES);

			while (rset.next()) {
				Categorie categorie = rsetToCategorie(rset);
				listCategories.add(categorie);
			}

			return listCategories;
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

	public static Categorie selectOneCategoryById(int id_categorie) throws Exception {

		Categorie categorie = new Categorie();

		Connection con = null;
		PreparedStatement stmt = null;
		
		try {

			con = ConnexionJDBC.getConnection();

			stmt = con.prepareStatement(QUERY_FIND_ONE_CATEGORY);

			stmt.setInt(1, id_categorie);

			ResultSet rset = stmt.executeQuery();

			while (rset.next()) {
				
				System.out.println("salut");

				categorie = rsetToCategorie(rset);

			}

			return categorie;

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



	public static void insertCategory(Categorie categorie) throws Exception {

		Connection con = null;
		PreparedStatement stmtBijoux = null;

		try {

			con = ConnexionJDBC.getConnection();

			stmtBijoux = con.prepareStatement(QUERY_INSERT_CATEGORY);

			stmtBijoux.setString(1, categorie.getNom_categorie());

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

	public static void deleteCategory(Categorie categorie) throws Exception {

		Connection con = null;
		PreparedStatement stmtBijoux = null;

		try {

			con = ConnexionJDBC.getConnection();

			stmtBijoux = con.prepareStatement(QUERY_DELETE_CATEGORY);

			stmtBijoux.setInt(1, categorie.getId_categorie());

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

	public static void updateCategory(Categorie categorie) {

		Connection con = null;
		
		PreparedStatement stmtCategory = null;

		try {

			con = ConnexionJDBC.getConnection();

			stmtCategory = con.prepareStatement(QUERY_UPDATE_CATEGORY);

			stmtCategory.setString(1, categorie.getNom_categorie());

			stmtCategory.setInt(2, categorie.getId_categorie());

			stmtCategory.executeUpdate();			

		} catch(Exception e){
			e.printStackTrace();

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



	public static Categorie rsetToCategorie( ResultSet rSet) throws SQLException{

		final Categorie categorie = new Categorie();

		categorie.setId_categorie(rSet.getInt("id_categorie"));
		categorie.setNom_categorie(rSet.getString("nom_categorie"));

		return categorie;
	}

}
