package fr.lapepite.db.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.fabric.xmlrpc.base.Array;
import com.sun.org.apache.regexp.internal.recompile;

import fr.lapepite.javabean.Commande;
import fr.lapepite.javabean.LigneCommande;

public class DBLigneCommandeUtils {
	
	private static final String QUERY_INSERT_LIGNE_COMMANDE = "INSERT INTO ligne_commande VALUES (?,?,?)";
	private static final String QUERY_SELECT_ALL_LIGNE_FOR_ONE_COMMANDE = "SELECT * FROM ligne_commande INNER JOIN Bijoux ON Bijoux.id_bijoux = ligne_commande.id_bijoux WHERE ligne_commande.id_commande = ?";

	public static void insertLigneCommande(Commande commande, LigneCommande ligneCommande) throws Exception {

		Connection con = null;
		PreparedStatement stmtCommande = null;

		try {

			con = ConnexionJDBC.getConnection();

			stmtCommande = con.prepareStatement(QUERY_INSERT_LIGNE_COMMANDE);

			stmtCommande.setInt(1, commande.getId_commande());
			stmtCommande.setDouble(2, ligneCommande.getBijoux().getId_bijoux());
			stmtCommande.setDouble(3, ligneCommande.getQuantite_lignecommande());
			
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
	public static List<LigneCommande> selectAllLigneCommandeForOneCommande(int id_commande) throws Exception {

		Connection con = null;
		PreparedStatement stmt = null;
		LigneCommande ligneCommande;
		
		List<LigneCommande> ligneCommandesList = new ArrayList<>();

		try {

			con = ConnexionJDBC.getConnection();

			stmt = con.prepareStatement(QUERY_SELECT_ALL_LIGNE_FOR_ONE_COMMANDE);

			stmt.setInt(1, id_commande);

			ResultSet rset = stmt.executeQuery();

			while (rset.next()) {

				ligneCommande = rsetToLigneCommande(rset);
				
				ligneCommandesList.add(ligneCommande);

			}
			
			return ligneCommandesList;

		} catch(Exception e){
			
			throw new Exception("Une erreur c'est produite lors de la récupération des données");

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
	
	public static LigneCommande rsetToLigneCommande(ResultSet rSet) throws SQLException {
		
		LigneCommande ligneCommande = new LigneCommande();
		
		ligneCommande.setBijoux(DBBijouxUtils.rsetToBijoux(rSet));
		
		ligneCommande.setQuantite_lignecommande(rSet.getInt("quantite_lignecommande"));
		
		return ligneCommande;
		
	}

	
}
