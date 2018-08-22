/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.lapepite.services;

import fr.lapepite.db.utils.DBPanierUtils;
import fr.lapepite.javabean.Bijoux;
import fr.lapepite.javabean.LignePanier;
import fr.lapepite.javabean.Panier;
import fr.lapepite.javabean.Utilisateur;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class PanierServices {
    
    public void insertPanier(Panier panier){
    	
        try {
        
        //Enregistrement en BD du panier
        DBPanierUtils.insertPanier(panier);
        
        } catch (Exception ex) {
            Logger.getLogger(PanierServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
    public void addBijouxToPanier(HttpServletRequest request) throws ServletException, IOException{
      
        //récup utilisateur
        HttpSession session = request.getSession();
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
        
        //Création Bijoux
        Bijoux bijoux = new Bijoux(); 
        bijoux = BijouxServices.getOneBijoux(request);
        
        //Création LignePanier
        LignePanier lignePanier = new LignePanier();       
        lignePanier.setBijoux(bijoux);
        lignePanier.setQuantite_lignepanier(Integer.parseInt(request.getParameter("numberOfProduct")));
        
        //Ajout de la ligne au panier
        Panier panier = utilisateur.getPanier();
        
        panier.addLigneToPanier(lignePanier);
        
        System.out.println(lignePanier.getQuantite_lignepanier() +" "+lignePanier.getBijoux().getNom_bijoux()+ " Prix = "+lignePanier.getQuantite_lignepanier()*lignePanier.getBijoux().getPrix_bijoux() );
        
        UtilisateurServices utilisateurServices = new UtilisateurServices();
        
        utilisateurServices.setSession(request, utilisateur);
        
    }

}
