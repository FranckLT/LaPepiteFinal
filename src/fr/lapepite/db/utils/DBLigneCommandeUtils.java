package fr.lapepite.db.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.lapepite.javabean.Commande;
import fr.lapepite.javabean.LigneCommande;

public class DBLigneCommandeUtils {
	
	private static final String QUERY_INSERT_LIGNE_COMMANDE = "INSERT INTO ligne_commande VALUES (?,?,?)";

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
	
	public static LigneCommande rsetToLigneCommande(ResultSet rSet) throws SQLException {
		
		LigneCommande ligneCommande = new LigneCommande();
		
		ligneCommande.setBijoux(DBBijouxUtils.rsetToBijoux(rSet));
		
		ligneCommande.setQuantite_lignecommande(rSet.getInt("quantite_lignecommande"));
		
		return ligneCommande;
		
	}

	
}
