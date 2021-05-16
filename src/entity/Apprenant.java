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
public class Apprenant extends Personnes{
    
      private String centreInteret;

    public Apprenant() {
    }

    public Apprenant(String centreInteret, int id, String cin, String nom, String prenom, String email, String nomUtilisateur, String password, String role, boolean etat) {
        super(id, cin, nom, prenom, email, nomUtilisateur, password, role, etat);
        this.centreInteret = centreInteret;
    }

    public Apprenant(String centreInteret, String cin, String nom, String prenom, String email, String nomUtilisateur, String password, String role, boolean etat) {
        super(cin, nom, prenom, email, nomUtilisateur, password, role, etat);
        this.centreInteret = centreInteret;
    }
    
    
    public String getCentreInteret() {
        return centreInteret;
    }

    public void setCentreInteret(String centreInteret) {
        this.centreInteret = centreInteret;
    }
      
      
      
}
