/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.lapepite.javabean;


/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Utilisateur {
    
    private int id_utilisateur;
    
    private String nom_utilisateur;
    
    private String prenom_utilisateur;
    
    private String adresse_utilisateur;
    
    private String mail_utilisateur;
    
    private String password_utilisateur;
    
    private boolean admin;
    
    private Panier panier;

    public int getId() {
        return id_utilisateur;
    }

    public Utilisateur setId(int id) {
        this.id_utilisateur = id;
        return this;
    }

    public String getNom() {
        return nom_utilisateur;
    }

    public Utilisateur setNom(String nom) {
        this.nom_utilisateur = nom;
        return this;
    }

    public String getPrenom() {
        return prenom_utilisateur;
    }

    public Utilisateur setPrenom(String prenom) {
        this.prenom_utilisateur = prenom;
        return this;
    }

    public String getAdresse() {
        return adresse_utilisateur;
    }

    public Utilisateur setAdresse(String adresse) {
        this.adresse_utilisateur = adresse;
        return this;
    }

    public String getEmail() {
        return mail_utilisateur;
    }

    public Utilisateur setEmail(String email) {
        this.mail_utilisateur = email;
        return this;
    }

    public boolean isAdmin() {
        return admin;
    }

    public Utilisateur setAdmin(boolean admin) {
        this.admin = admin;
        return this;
    }

    public String getPassword() {
        return password_utilisateur;
    }

    public Utilisateur setPassword(String password) {
        this.password_utilisateur = password;
        return this;
    }

    public Panier getPanier() {
        return panier;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }

}
