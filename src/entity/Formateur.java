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
public class Formateur extends Personnes{
    
     private String domaine;

    public Formateur() {
    }

    public Formateur(String domaine, int id, String cin, String nom, String prenom, String email, String nomUtilisateur, String password, String role, boolean etat) {
        super(id, cin, nom, prenom, email, nomUtilisateur, password, role, etat);
        this.domaine = domaine;
    }

    public Formateur(String domaine, String cin, String nom, String prenom, String email, String nomUtilisateur, String password, String role, boolean etat) {
        super(cin, nom, prenom, email, nomUtilisateur, password, role, etat);
        this.domaine = domaine;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }
     
    
     
}
