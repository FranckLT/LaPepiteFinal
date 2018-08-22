/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.lapepite.db.utils;

import fr.lapepite.javabean.Bijoux;
import fr.lapepite.javabean.Categorie;
import fr.lapepite.javabean.Designer;
import fr.lapepite.javabean.Utilisateur;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.omg.PortableServer.ServantActivator;


/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class DBBijouxUtils {


	private final static String QUERY_FIND_BIJOUX = "SELECT * FROM Bijoux INNER JOIN Designer ON Bijoux.id_designer = Designer.id_designer INNER JOIN Categorie on Bijoux.id_categorie = Categorie.id_categorie";
	private final static String QUERY_FIND_ONE_BIJOUX = "SELECT * FROM bijoux INNER JOIN designer ON bijoux.id_designer = designer.id_designer INNER JOIN categorie on bijoux.id_categorie = categorie.id_categorie WHERE id_bijoux=(?)";
	private final static String QUERY_INSERT_BIJOUX = "INSERT INTO bijoux VALUES(null, ?,?,?,null,?,?,?,?)";
	private final static String QUERY_DELETE_BIJOUX = "DELETE FROM bijoux WHERE id_bijoux=?";
	private final static String QUERY_UPDATE_BIJOUX = "UPDATE bijoux SET nom_bijoux = ?, ref_bijoux=?, prix_bijoux = ?, id_designer=?, id_categorie=?, qteStock=?, description=?  WHERE id_bijoux = ?";

	public static ArrayList<Bijoux> requestSelectAll() throws Exception {

		ArrayList<Bijoux> listBijoux=new ArrayList<>();
		Connection con = null;
		Statement stmt = null;
		
		try {
			
			con = ConnexionJDBC.getConnection();
			
			stmt = con.createStatement();
			
			ResultSet rset = stmt.executeQuery(QUERY_FIND_BIJOUX);

			while (rset.next()) {
				Bijoux bijou = rsetToBijoux(rset);
				listBijoux.add(bijou);
			}

			return listBijoux;
		}
		catch (final SQLException e) {
			
			throw new Exception("La requete de récupération des bijoux à échoué.");
			
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

	public static ArrayList<Bijoux> requestSelectAllNomBijoux() {

		ArrayList<Bijoux> listBijou=new ArrayList<>();

		Connection con = null;
		Statement stmt = null;
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con = DriverManager.getConnection(ConnexionJDBC.URL, ConnexionJDBC.LOGIN, ConnexionJDBC.PASSWORD); //La connexion
			stmt = con.createStatement();
			ResultSet rset = stmt.executeQuery(QUERY_FIND_BIJOUX);

			while (rset.next()) {
				Bijoux bijou = rsetToBijoux(rset);
				listBijou.add(bijou);
			}

			return listBijou;
		}
		catch (final SQLException e) {
			e.printStackTrace();
			return listBijou;
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
	
	public static ArrayList<Bijoux> requestSelectAllRefBijoux() {

		ArrayList<Bijoux> listBijou=new ArrayList<>();

		Connection con = null;
		Statement stmt = null;
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con = DriverManager.getConnection(ConnexionJDBC.URL, ConnexionJDBC.LOGIN, ConnexionJDBC.PASSWORD); //La connexion
			stmt = con.createStatement();
			ResultSet rset = stmt.executeQuery(QUERY_FIND_BIJOUX);

			while (rset.next()) {
				Bijoux bijou = rsetToBijoux(rset);
				listBijou.add(bijou);
			}

			return listBijou;
		}
		catch (final SQLException e) {
			e.printStackTrace();
			return listBijou;
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



	public static Bijoux selectOneBijouxById(int id) throws Exception, SQLException {
		Connection con = null;
		PreparedStatement stmt = null;

		Bijoux bijoux = new Bijoux();

		try {

			con = ConnexionJDBC.getConnection();

			// takes the name and returns an id
			stmt = con.prepareStatement(QUERY_FIND_ONE_BIJOUX);
			stmt.setInt(1, id);
			ResultSet rSet = stmt.executeQuery();
			if (rSet.next()) {
				bijoux = rsetToBijoux(rSet);
				return bijoux;
			} else {
				throw new Exception("L'utilisateur : " + id + " n'existe pas");
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

	public static void insertBijoux(Bijoux bijoux) {

		Connection con = null;
		PreparedStatement stmtBijoux = null;

		try {

			con = ConnexionJDBC.getConnection();

			stmtBijoux = con.prepareStatement(QUERY_INSERT_BIJOUX);

			stmtBijoux.setString(1, bijoux.getNom_bijoux());
			stmtBijoux.setString(2, bijoux.getRef_bijoux());
			stmtBijoux.setInt(3, bijoux.getPrix_bijoux());
			stmtBijoux.setInt(4, bijoux.getStock_bijoux());
			stmtBijoux.setString(5, bijoux.getDescription_bijoux());
			stmtBijoux.setInt(6, bijoux.getCategorie().getId_categorie());
			stmtBijoux.setInt(7, bijoux.getDesigner().getId_designer());

			
			stmtBijoux.executeUpdate();
			
			

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
	
	public static void updateBijoux(Bijoux bijoux) {

		Connection con = null;
		PreparedStatement stmtBijoux = null;


		try {

			con = ConnexionJDBC.getConnection();

			stmtBijoux = con.prepareStatement(QUERY_UPDATE_BIJOUX);

			stmtBijoux.setString(1, bijoux.getNom_bijoux());
			stmtBijoux.setString(2, bijoux.getRef_bijoux());
			stmtBijoux.setInt(3, bijoux.getPrix_bijoux());
			stmtBijoux.setInt(4, bijoux.getDesigner().getId_designer());
			stmtBijoux.setInt(5, bijoux.getCategorie().getId_categorie());
			stmtBijoux.setInt(6, bijoux.getStock_bijoux());
			stmtBijoux.setString(7, bijoux.getDescription_bijoux());
			stmtBijoux.setInt(8, bijoux.getId_bijoux());

			stmtBijoux.executeUpdate();

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


	public static void deleteBijoux(Bijoux bijoux) {

		Connection con = null;
		PreparedStatement stmtBijoux = null;

		try {

			con = ConnexionJDBC.getConnection();

			stmtBijoux = con.prepareStatement(QUERY_DELETE_BIJOUX);

			stmtBijoux.setInt(1, bijoux.getId_bijoux());

			stmtBijoux.executeUpdate();

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






	private static Bijoux rsetToBijoux(final ResultSet rset) throws SQLException {
		
		Designer designer = DBDesignerUtils.rsetToDesigner(rset);

		Categorie categorie = DBCategorieUtils.rsetToCategorie(rset);

		Bijoux bijou = new Bijoux();

		bijou.setId_bijoux(rset.getInt("id_bijoux"));

		bijou.setRef_bijoux(rset.getString("ref_bijoux"));

		bijou.setNom_bijoux(rset.getString("nom_bijoux"));

		bijou.setPrix_bijoux(rset.getInt("prix_bijoux"));
		
		bijou.setStock_bijoux(rset.getInt("stock_bijoux"));

		bijou.setImage_bijoux(rset.getString("image_bijoux"));

		bijou.setDescription_bijoux(rset.getString("description_bijoux"));

		bijou.setDesigner(designer);

		bijou.setCategorie(categorie);

		return bijou;
	}


}

