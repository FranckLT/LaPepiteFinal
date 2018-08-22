/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.lapepite.javabean;



public class Utilisateur {
    
    private int id_utilisateur;
    
    private String nom_utilisateur;
    
    private String prenom_utilisateur;
    
    private String adresse_utilisateur;
    
    private String mail_utilisateur;
    
    private String password_utilisateur;
    
    private boolean admin;
    
    private Panier panier;

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public Utilisateur setId_utilisateur(int id) {
        this.id_utilisateur = id;
        return this;
    }

    public String getNom_utilisateur() {
        return nom_utilisateur;
    }

    public Utilisateur setNom_utilisateur(String nom) {
        this.nom_utilisateur = nom;
        return this;
    }

    public String getPrenom_utilisateur() {
        return prenom_utilisateur;
    }

    public Utilisateur setPrenom_utilisateur(String prenom) {
        this.prenom_utilisateur = prenom;
        return this;
    }

    public String getAdresse_utilisateur() {
        return adresse_utilisateur;
    }

    public Utilisateur setAdresse_utilisateur(String adresse) {
        this.adresse_utilisateur = adresse;
        return this;
    }

    public String getMail_utilisateur() {
        return mail_utilisateur;
    }

    public Utilisateur setMail_utilisateur(String email) {
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

    public String getPassword_utilisateur() {
        return password_utilisateur;
    }

    public Utilisateur setPassword_utilisateur(String password) {
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
