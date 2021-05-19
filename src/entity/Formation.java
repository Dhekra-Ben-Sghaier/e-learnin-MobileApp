/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Asus
 */
public class Formation {
    private int id;
    private String titre;
    private String description;
    private float prix;
    private String difficulte;
 

    private String cours;
    private String image;
    
    
    public Formation() {
    }

    public Formation(String titre, String description, float prix, String difficulte, String cours, String image) {
        this.titre = titre;
        this.description = description;
        this.prix = prix;
        this.difficulte = difficulte;
        this.cours = cours;
        this.image = image;
    }
    
  
    public Formation(int id, String titre, String description, float prix, String difficulte, String cours) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.prix = prix;
        this.difficulte = difficulte;
        this.cours = cours;
    }

    public Formation(int id, String titre, String description, float prix, String difficulte, String cours, String image) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.prix = prix;
        this.difficulte = difficulte;
        this.cours = cours;
        this.image = image;
    }
    


  

    public Formation(int id, String titre, String image, String description, float prix) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.prix = prix;
        this.image = image;
       
    }
    

    public String getDifficulte() {
        return difficulte;
    }

    public Formation(int id, String titre, String description, float prix) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.prix = prix;
    }

    public Formation(int id, String titre, String description, float prix, String difficulte) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.prix = prix;
        this.difficulte = difficulte;
 
    }

 

    public String getCours() {
        return cours;
    }

    public void setCours(String cours) {
        this.cours = cours;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

   



    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }


    public String getDescription() {
        return description;
    }

    public float getPrix() {
        return prix;
    }




    public void setId(int id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setDifficulte(String difficulte) {
        this.difficulte = difficulte;
    }

    @Override
    public String toString() {
        return "Formation{" + "id=" + id + ", titre=" + titre + ", description=" + description + ", prix=" + prix + ", difficulte=" + difficulte + ", cours=" + cours + ", image=" + image + '}';
    }



    
    
}
