/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package fr.lapepite.services;

import fr.lapepite.controller.RegisteringServlet;
import fr.lapepite.db.utils.DBPanierUtils;
import fr.lapepite.db.utils.DBUtilisateurUtils;
import fr.lapepite.javabean.LignePanier;
import fr.lapepite.javabean.Panier;
import fr.lapepite.javabean.Utilisateur;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;

import com.sun.xml.internal.ws.api.pipe.NextAction;


public class UtilisateurServices {
    
    public static void addUtilisateur(Utilisateur utilisateur) throws Exception{
    	
        DBUtilisateurUtils.insertUtilisateur(utilisateur);
        
    }
    
    public void logUtilisateur(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception{
        try {
            
            if (verifLoginForm(request)) {
                
                Utilisateur utilisateur = getUtilisateur(request);
                
                String passwordFromForm = request.getParameter("password");
                
                if (testIfPasswordMatch(passwordFromForm, utilisateur)) {
                	
                	Panier panier = new Panier();
                	
                	ArrayList<LignePanier> list = new ArrayList<>();
                	
                	panier.setListProduit(list);
                	
                	utilisateur.setPanier(panier);
                    
                    setSessionUtilisateur(request, utilisateur);
                    
                    response.sendRedirect("/LaPepite/home");
                    
                } else {
                    
                    response.sendRedirect("/LaPepite/login");
                    
                }
                
                
            }
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(UtilisateurServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public boolean registerUser(HttpServletRequest request) throws ServletException, IOException{
        
        try {
            
            if (verifRegisterForm(request)) {
             
                if (!verifIfEmailAlreadyUsed(request)) {

                //Recup des infos
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                String nom = request.getParameter("nom");
                String prenom = request.getParameter("prenom");
                String adresse = request.getParameter("adresse");
                
                //création user
                Utilisateur utilisateur = new Utilisateur();
                
                //Hachage password
                password = BCrypt.hashpw(password, BCrypt.gensalt());
                
                //set de l'utilisateur
                utilisateur
                        .setNom_utilisateur(nom)
                        .setPrenom_utilisateur(prenom)
                        .setMail_utilisateur(email)
                        .setAdresse_utilisateur(adresse)
                        .setAdmin(false)
                        .setPassword_utilisateur(password);
                
                
                //enregistrement en base
                addUtilisateur(utilisateur);
                
                //récupération utilisateur en BDD
                utilisateur = DBUtilisateurUtils.selectUtilisateurByEmail(utilisateur);
                
                //Après récupértion de l'id de l'utilisateur on enregistre son panier
                PanierServices panierServices = new PanierServices();
                
                Panier panier = new Panier();
                
                panier.setId_panier(utilisateur.getId_utilisateur());
                
                panierServices.insertPanier(panier);
                
                utilisateur.setPanier(panier);
               
                //enregistrement uilisateur en session
                setSessionUtilisateur(request, utilisateur);
                
                return true;
   
                 } else {
  
                	return false;
                	 
                }
                
            } else {
            	
            	return false;
            	
            }
            
            
        } catch (Exception ex) {
            Logger.getLogger(RegisteringServlet.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    
    public Utilisateur getUtilisateur(HttpServletRequest request) throws ServletException, IOException{
        try {
            
            String emailFromForm = request.getParameter("email");
            
            Utilisateur utilisateurFromForm = new Utilisateur();
            
            utilisateurFromForm.setMail_utilisateur(emailFromForm);
            
            Utilisateur utilisateurFromDB = DBUtilisateurUtils.selectUtilisateurByEmail(utilisateurFromForm);
            
            return utilisateurFromDB;
            
            
        } catch (Exception ex) {
            Logger.getLogger(UtilisateurServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    public void setSessionUtilisateur(HttpServletRequest request, Utilisateur utilisateur){
        
        HttpSession session = request.getSession();
        
        session.setAttribute("utilisateur", utilisateur);
        
    }
    
    
    public boolean testIfPasswordMatch(String password, Utilisateur utilisateur){
        
        boolean testIfPasswordMatch = BCrypt.checkpw(password, utilisateur.getPassword_utilisateur());
        
        if (testIfPasswordMatch) {
            return true;
        } else{
            return false;
        }
    }
    
    
    public boolean testIfUtilisateurIsConnected(HttpServletRequest request){
        
        Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
        
        if (utilisateur != null) {
            return false;
        } else {
            return true;
        }
    }
    
    
    public void setSession(HttpServletRequest request, Utilisateur utilisateur){
        
        HttpSession session = request.getSession();
        
        session.setAttribute("utilisateur", utilisateur);
        
    }
    
    public boolean verifLoginForm(HttpServletRequest request) throws Exception{
        
        String email = request.getParameter("email");
        
        String password = request.getParameter("password");
        
        if (email.isEmpty()) {
            throw new Exception("L'email est vide.");
        }
        
        if (password.isEmpty()){
            throw new Exception("Le mot de passe est vide.");
        }
        
        return true;
        
    }
    
    public boolean verifRegisterForm(HttpServletRequest request) throws Exception{
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String adresse = request.getParameter("adresse");
        
        if (email.isEmpty()) {
            throw new Exception("L'email est vide");
        }
        
        if (password.isEmpty()) {
            throw new Exception("Le mot de passe est vide");
        }
        
        if (nom.isEmpty()) {
            throw new Exception("Le nom est vide");
        }
        
        if (prenom.isEmpty()) {
            throw new Exception("Le prenom est vide");
        }
        
        if (adresse.isEmpty()) {
            throw new Exception("L'adresse est vide");
        }
        
        return true;
        
    }
    
    public boolean verifIfEmailAlreadyUsed(HttpServletRequest request){
        
        //recup email
        String emailForm = request.getParameter("email");
        
        List<String> listEmail = new ArrayList<>();
        
        listEmail.addAll(DBUtilisateurUtils.requestSelectAllEmailUtilisateur());
        
        //on boucle sur la liste de tout les emails de la base ppur voir si 
        // il existe deja 
        for (String email : listEmail) {
        	
            if (email.equals(emailForm)) {
            	
                return true;
                
            }
        }
        
        return false;
        
    }
    
}
