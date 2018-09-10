/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.lapepite.db.utils;

import fr.lapepite.javabean.Bijoux;
import fr.lapepite.javabean.Categorie;
import fr.lapepite.javabean.Designer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class DBBijouxUtils {

	private final static String QUERY_FIND_BIJOUX = "SELECT * FROM Bijoux INNER JOIN Designer ON Bijoux.id_designer = Designer.id_designer INNER JOIN Categorie on Bijoux.id_categorie = Categorie.id_categorie";

	private final static String QUERY_FIND_ONE_BIJOUX = "SELECT * FROM bijoux INNER JOIN designer ON bijoux.id_designer = designer.id_designer INNER JOIN categorie on bijoux.id_categorie = categorie.id_categorie WHERE id_bijoux=(?)";

	private final static String QUERY_INSERT_BIJOUX = "INSERT INTO bijoux VALUES(null, ?,?,?,?,?,?,?,?)";

	private final static String QUERY_DELETE_BIJOUX = "DELETE FROM bijoux WHERE id_bijoux=?";

	private final static String QUERY_UPDATE_BIJOUX = "UPDATE bijoux SET nom_bijoux = ?, ref_bijoux=?, prix_bijoux = ?, id_designer=?, id_categorie=?, stock_bijoux=?, description_bijoux=?  WHERE id_bijoux = ?";

	private final static String QUERY_FIND_BIJOUX_BY_DESIGNER = "SELECT * FROM Bijoux INNER JOIN Designer ON Bijoux.id_designer = Designer.id_designer INNER JOIN Categorie on Bijoux.id_categorie = Categorie.id_categorie WHERE Designer.id_designer=?";

	public static ArrayList<Bijoux> requestSelectAll() throws Exception {

		ArrayList<Bijoux> listBijoux = new ArrayList<>();
		Connection con = null;
		Statement stmt = null;

		try {

			con = ConnexionJDBC.getConnection();

			stmt = con.createStatement();

			ResultSet rset = stmt.executeQuery(QUERY_FIND_BIJOUX);

			while (rset.next()) {
				Bijoux bijou = rsetToBijouxDesignerCategorie(rset);
				listBijoux.add(bijou);
			}

			return listBijoux;
		} catch (final SQLException e) {

			throw new Exception("La requete de récupération des bijoux à échoué.");

		} finally {

			if (con != null) {
				try {
					con.close();
				} catch (final SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static ArrayList<Bijoux> requestSelectAllNomBijoux() throws Exception {

		ArrayList<Bijoux> listBijou = new ArrayList<>();

		Connection con = null;
		Statement stmt = null;
		try {

			con = ConnexionJDBC.getConnection();

			stmt = con.createStatement();
			ResultSet rset = stmt.executeQuery(QUERY_FIND_BIJOUX);

			while (rset.next()) {
				Bijoux bijou = rsetToBijouxDesignerCategorie(rset);
				listBijou.add(bijou);
			}

			return listBijou;
		} catch (final SQLException e) {
			throw new Exception("La requete de récupération des bijoux à échoué.");
		} finally {

			if (con != null) {
				try {
					con.close();
				} catch (final SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static ArrayList<Bijoux> requestSelectAllRefBijoux() throws Exception {

		ArrayList<Bijoux> listBijou = new ArrayList<>();

		Connection con = null;
		Statement stmt = null;
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con = DriverManager.getConnection(ConnexionJDBC.URL, ConnexionJDBC.LOGIN, ConnexionJDBC.PASSWORD); // La
																												// connexion
			stmt = con.createStatement();
			ResultSet rset = stmt.executeQuery(QUERY_FIND_BIJOUX);

			while (rset.next()) {
				Bijoux bijou = rsetToBijouxDesignerCategorie(rset);
				listBijou.add(bijou);
			}

			return listBijou;
		} catch (final SQLException e) {
			throw new Exception("La requete de récupération des bijoux à échoué.");
		} finally {

			if (con != null) {
				try {
					con.close();
				} catch (final SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static Bijoux selectOneBijouxById(int idBijoux) throws Exception, SQLException {

		Connection con = null;
		PreparedStatement stmt = null;
		Bijoux bijoux = new Bijoux();

		try {

			con = ConnexionJDBC.getConnection();
			stmt = con.prepareStatement(QUERY_FIND_ONE_BIJOUX);

			stmt.setInt(1, idBijoux);
			ResultSet rSet = stmt.executeQuery();
			if (rSet.next()) {
				bijoux = rsetToBijouxDesignerCategorie(rSet);
			}
			return bijoux;
		} catch (Exception e) {
			throw new Exception("Le bijoux n° " + idBijoux + " n'existe pas");
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

	public static ArrayList<Bijoux> selectBijouxByDesigner(int id) throws Exception, SQLException {

		Connection con = null;
		PreparedStatement stmt = null;

		ArrayList<Bijoux> bijouxList = new ArrayList<>();

		try {

			con = ConnexionJDBC.getConnection();

			stmt = con.prepareStatement(QUERY_FIND_BIJOUX_BY_DESIGNER);

			stmt.setInt(1, id);

			ResultSet rSet = stmt.executeQuery();

			while (rSet.next()) {
				Bijoux bijoux = rsetToBijouxDesignerCategorie(rSet);
				bijouxList.add(bijoux);
			}

			return bijouxList;

		} catch (Exception e) {
			throw new Exception("Une erreur c'est produite lors de l'enregistrement en base de données");

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

	public static void insertBijoux(Bijoux bijoux) throws Exception {

		Connection con = null;
		PreparedStatement stmtBijoux = null;

		try {

			con = ConnexionJDBC.getConnection();

			stmtBijoux = con.prepareStatement(QUERY_INSERT_BIJOUX);

			stmtBijoux.setString(1, bijoux.getNom_bijoux());
			stmtBijoux.setString(2, bijoux.getRef_bijoux());
			stmtBijoux.setInt(3, bijoux.getPrix_bijoux());
			stmtBijoux.setString(4, bijoux.getImage_bijoux());
			stmtBijoux.setInt(5, bijoux.getStock_bijoux());
			stmtBijoux.setString(6, bijoux.getDescription_bijoux());
			stmtBijoux.setInt(7, bijoux.getCategorie().getId_categorie());
			stmtBijoux.setInt(8, bijoux.getDesigner().getId_designer());

			stmtBijoux.executeUpdate();

		} catch (Exception e) {
			throw new Exception("Une erreur c'est produite lors de l'enregistrement en base de données");

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

	public static void updateBijoux(Bijoux bijoux) throws Exception {

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

		} catch (Exception e) {

			throw new Exception("Une erreur c'est produite lors de la mise à jour.");

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

	public static void deleteBijoux(Bijoux bijoux) throws Exception {

		Connection con = null;
		PreparedStatement stmtBijoux = null;

		try {

			con = ConnexionJDBC.getConnection();

			stmtBijoux = con.prepareStatement(QUERY_DELETE_BIJOUX);

			stmtBijoux.setInt(1, bijoux.getId_bijoux());

			stmtBijoux.executeUpdate();

		} catch (Exception e) {

			throw new Exception("Une erreur c'est produite lors de la tentaive de suppression du bijoux.");

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

	private static Bijoux rsetToBijouxDesignerCategorie(final ResultSet rset) throws SQLException {

		Designer designer = DBDesignerUtils.rsetToDesigner(rset);

		Categorie categorie = DBCategorieUtils.rsetToCategorie(rset);

		Bijoux bijoux = new Bijoux();

		bijoux.setId_bijoux(rset.getInt("id_bijoux"));
		bijoux.setRef_bijoux(rset.getString("ref_bijoux"));
		bijoux.setNom_bijoux(rset.getString("nom_bijoux"));
		bijoux.setPrix_bijoux(rset.getInt("prix_bijoux"));
		bijoux.setStock_bijoux(rset.getInt("stock_bijoux"));
		bijoux.setImage_bijoux(rset.getString("image_bijoux"));
		bijoux.setDescription_bijoux(rset.getString("description_bijoux"));
		bijoux.setDesigner(designer);
		bijoux.setCategorie(categorie);

		return bijoux;
	}

	public static Bijoux rsetToBijoux(ResultSet rset) throws SQLException {
		Bijoux bijou = new Bijoux();

		bijou.setId_bijoux(rset.getInt("id_bijoux"));

		bijou.setRef_bijoux(rset.getString("ref_bijoux"));

		bijou.setNom_bijoux(rset.getString("nom_bijoux"));

		bijou.setPrix_bijoux(rset.getInt("prix_bijoux"));

		bijou.setStock_bijoux(rset.getInt("stock_bijoux"));

		bijou.setImage_bijoux(rset.getString("image_bijoux"));

		bijou.setDescription_bijoux(rset.getString("description_bijoux"));

		return bijou;
	}

}
