package fr.lapepite.db.utils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import fr.lapepite.javabean.Commentaire;
import fr.lapepite.javabean.Utilisateur;

public class DBCommentairesUtils {

	private static final String QUERY_SELECT_COMMENTAIRES_BY_PRODUCTS = "SELECT * FROM Commentaire INNER JOIN Utilisateur ON Utilisateur.id_utilisateur = Commentaire.id_utilisateur WHERE Commentaire.id_bijoux = ?";
	private static final String QUERY_INSERT_COMMENTAIRE = "INSERT INTO Commentaire VALUES (null, ?, ?, ?, ?)";

	public static ArrayList<Commentaire> selectCommentairesByBijoux(int idBijoux) throws Exception, SQLException {

		Connection con = null;
		PreparedStatement stmt = null;

		ArrayList<Commentaire> commentairesList = new ArrayList<>();

		try {

			con = ConnexionJDBC.getConnection();

			stmt = con.prepareStatement(QUERY_SELECT_COMMENTAIRES_BY_PRODUCTS);

			stmt.setInt(1, idBijoux);

			ResultSet rSet = stmt.executeQuery();

			while (rSet.next()) {
				Commentaire commentaire = rsetToCommentaire(rSet);
				commentairesList.add(commentaire);
			}

			return commentairesList;

		} catch(Exception e){
			throw new Exception("Une erreur c'est produite lors de l'enregistrement en base de données");

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
	
	public static void insertCommentaire( Commentaire commentaire, int idBijoux ) throws Exception, SQLException {

		Connection con = null;
		PreparedStatement stmt = null;
		Date date;

		try {

			con = ConnexionJDBC.getConnection();

			stmt = con.prepareStatement(QUERY_INSERT_COMMENTAIRE);
			
			date = new Date(System.currentTimeMillis());

			stmt.setString(1, commentaire.getTexte_commentaire());
			stmt.setDate(2, date);
			stmt.setInt(3, commentaire.getUtilisateur().getId_utilisateur());
			stmt.setInt(4, idBijoux);
			

			stmt.executeUpdate();

		} catch(Exception e){
			
			throw new Exception("Le commentaire n'a pu être enregistré.");

		}finally {
			if (con != null) {
				try {
					con.close();
				} catch (final SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}


	public static Commentaire rsetToCommentaire(ResultSet rSet) throws SQLException {
		
		Utilisateur utilisateur = new Utilisateur();
		Commentaire commentaire = new Commentaire();
		
		utilisateur = DBUtilisateurUtils.rsetToUser(rSet);
		
		commentaire.setDate_commentaire(rSet.getDate("date_commentaire"));
		commentaire.setId_commentaire(rSet.getInt("id_commentaire"));
		commentaire.setTexte_commentaire(rSet.getString("texte_commentaire"));
		commentaire.setUtilisateur(utilisateur);
		
		return commentaire;	

	}



}
