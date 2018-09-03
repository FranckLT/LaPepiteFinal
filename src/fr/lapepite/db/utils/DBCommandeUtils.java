/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.lapepite.db.utils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import fr.lapepite.javabean.Commande;
import fr.lapepite.javabean.Utilisateur;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class DBCommandeUtils {
	
	private static final String QUERY_INSERT_COMMANDE = "INSERT INTO Commande VALUES (null, ?, ?, ?, ?, ?, ?)";
	private static final String QUERY_SELECT_ALL_COMMANDE = "SELECT * FROM Commande INNER JOIN Utilisateur ON Utilisateur.id_utilisateur = Commande.id_utilisateur INNER JOIN ligne_commande ON ligne_commande.id_commande = Commande.id_commande INNER JOIN Bijoux ON Bijoux.id_bijoux = ligne_commande.id_bijoux";
	private static final String QUERY_SELECT_LAST_COMMANDE = "SELECT * FROM Commande WHERE id_utilisateur=? ORDER BY id_commande DESC LIMIT 1";
	
	public static void insertCommande(Commande commande, Utilisateur utilisateur) throws Exception {

		Connection con = null;
		PreparedStatement stmtCommande = null;

		try {

			con = ConnexionJDBC.getConnection();

			stmtCommande = con.prepareStatement(QUERY_INSERT_COMMANDE);
			
			Date date = new Date(System.currentTimeMillis());

			stmtCommande.setDate(1, date);
			stmtCommande.setDouble(2, commande.getTotalHT_commande());
			stmtCommande.setDouble(3, commande.getTVA_commande());
			stmtCommande.setDouble(4, commande.getTotalTTC_commande());
			stmtCommande.setBoolean(5, false);
			stmtCommande.setInt(6, utilisateur.getId_utilisateur());
			
			stmtCommande.executeUpdate();
			
			

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
	

	public static ArrayList<Commande> selectAllCommandes() throws Exception {

		ArrayList<Commande> listCommandes = new ArrayList<>();
		Connection con = null;
		Statement stmt = null;
		
		try {
			
			con = ConnexionJDBC.getConnection();
			
			stmt = con.createStatement();
			
			ResultSet rset = stmt.executeQuery(QUERY_SELECT_ALL_COMMANDE);

			while (rset.next()) {
				Commande commande = rsetToCommande(rset);
				listCommandes.add(commande);
			}

			return listCommandes;
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

	public static Commande selectLastCommande(Utilisateur utilisateur) throws Exception {

		Commande commande = new Commande();

		Connection con = null;
		PreparedStatement stmt = null;
		
		try {

			con = ConnexionJDBC.getConnection();

			stmt = con.prepareStatement(QUERY_SELECT_LAST_COMMANDE);

			stmt.setInt(1, utilisateur.getId_utilisateur());

			ResultSet rset = stmt.executeQuery();

			while (rset.next()) {

				commande = rsetToCommande(rset);

			}

			return commande;

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
	
	
	public static Commande rsetToCommande(ResultSet resultSet) throws SQLException {
		Commande commande = new Commande();	
		
		commande.setId_commande(resultSet.getInt("id_commande"));
		commande.setDate_commande(resultSet.getDate("date_commande"));
		commande.setTotalHT_commande(resultSet.getDouble("totalHT_commande"));
		commande.setTVA_commande(resultSet.getDouble("TVA_commande"));
		commande.setTotalTTC_commande(resultSet.getDouble("totalTTC_commande"));
		commande.setCheked(resultSet.getBoolean("cheked"));
		
		return commande;
		
	}


}
