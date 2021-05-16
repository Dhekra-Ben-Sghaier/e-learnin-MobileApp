/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author benha
 */
public class Personnes {
     private int id;
     private String cin;
     private String nom;
     private String prenom;
     private String email;
     private String nomUtilisateur;
     private String photo;
     private String password;
     private String role;
     private boolean etat;

    public Personnes() {
    }

    public Personnes(int id, String cin, String nom, String prenom, String email, String nomUtilisateur, String password, String role, boolean etat) {
        this.id = id;
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.nomUtilisateur = nomUtilisateur;
        this.password = password;
        this.role = role;
        this.etat = etat;
    }

  
//formateur ET apprenant
    public Personnes(String cin, String nom, String prenom, String email, String nomUtilisateur, String password, String role, boolean etat) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.nomUtilisateur = nomUtilisateur;
        this.password = password;
        this.role = role;
        this.etat = etat;
    }
    
   //societe
    
      public Personnes(String email, String nomUtilisateur, String password, String role, boolean etat) {
        this.email = email;
        this.nomUtilisateur = nomUtilisateur;
        this.password = password;
        this.role = role;
        this.etat = etat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }
    
    
    
    
}
