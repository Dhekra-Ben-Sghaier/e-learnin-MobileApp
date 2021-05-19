/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author asus
 */
public class Reclamation {
     private int idReclamation;
     private String adressem;
     private String examen;
     private String date;
     private String nomformateur;
     private String description; 

    public Reclamation(int idReclamation, String adressem, String examen, String date, String nomformateur, String description) {
        this.idReclamation = idReclamation;
        this.adressem = adressem;
        this.examen = examen;
        this.date = date;
        this.nomformateur = nomformateur;
        this.description = description;
        
    }

    public Reclamation(String adressem, String examen, String date, String nomformateur, String description) {
        this.adressem = adressem;
        this.examen = examen;
        this.date = date;
        this.nomformateur = nomformateur;
        this.description = description;
    }

    public Reclamation() {
    }

    public int getIdReclamation() {
        return idReclamation;
    }

    public void setIdReclamation(int idReclamation) {
        this.idReclamation = idReclamation;
    }

    public String getAdressem() {
        return adressem;
    }

    public void setAdressem(String adressem) {
        this.adressem = adressem;
    }

    public String getExamen() {
        return examen;
    }

    public void setExamen(String examen) {
        this.examen = examen;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNomformateur() {
        return nomformateur;
    }

    public void setNomformateur(String nomformateur) {
        this.nomformateur = nomformateur;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
     
}
