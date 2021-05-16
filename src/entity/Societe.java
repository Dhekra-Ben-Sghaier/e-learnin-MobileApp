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
public class Societe extends Personnes{
    
    private String nomSociete;

    public Societe() {
    }

    public Societe(String nomSociete, String email, String nomUtilisateur, String password, String role, boolean etat) {
        super(email, nomUtilisateur, password, role, etat);
        this.nomSociete = nomSociete;
    }

    public String getNomSociete() {
        return nomSociete;
    }

    public void setNomSociete(String nomSociete) {
        this.nomSociete = nomSociete;
    }

   
    
}
