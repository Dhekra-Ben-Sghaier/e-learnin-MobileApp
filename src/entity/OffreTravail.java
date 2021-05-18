/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;

/**
 *
 * @author skander
 */
public class OffreTravail {
    private int Id_Travail;
    private String Nom_soc;
    private String Adr_mail_soc;
    private String Adr_soc;
    private String Description;
    private Date Date_pub;
    private String Niv_etude;
    private String Certificat;
    private String Type_contrat;
    private int Id_societe;
    private String Titre;

    public OffreTravail() {
    }

    public OffreTravail(int Id_Travail, String Nom_soc, String Adr_mail_soc, String Adr_soc, String Description, Date Date_pub, String Niv_etude, String Certificat, String Type_contrat,int Id_societe, String Titre) {
        this.Id_Travail = Id_Travail;
        this.Nom_soc = Nom_soc;
        this.Adr_mail_soc = Adr_mail_soc;
        this.Adr_soc = Adr_soc;
        this.Description = Description;
        this.Date_pub = Date_pub;
        this.Niv_etude = Niv_etude;
        this.Certificat = Certificat;
        this.Type_contrat= Type_contrat;
        this.Id_societe=Id_societe;
        this.Titre=Titre;
    }

    public OffreTravail(String Nom_soc, String Adr_mail_soc, String Adr_soc, String Description, Date Date_pub, String Niv_etude, String Certificat, String Type_contrat,int Id_societe, String Titre) {
        this.Nom_soc = Nom_soc;
        this.Adr_mail_soc = Adr_mail_soc;
        this.Adr_soc = Adr_soc;
        this.Description = Description;
        this.Date_pub = Date_pub;
        this.Niv_etude = Niv_etude;
        this.Certificat = Certificat;
        this.Type_contrat= Type_contrat;
        this.Id_societe=Id_societe;
        this.Titre=Titre;
        
    }

    public int getId_Travail() {
        return Id_Travail;
    }

    public String getNom_soc() {
        return Nom_soc;
    }

    public String getAdr_mail_soc() {
        return Adr_mail_soc;
    }

    public String getAdr_soc() {
        return Adr_soc;
    }

    public String getDescription() {
        return Description;
    }

    public Date getDate_pub() {
        return Date_pub;
    }

    public String getNiv_etude() {
        return Niv_etude;
    }

    public String getCertificat() {
        return Certificat;
    }

    public String getType_contrat() {
        return Type_contrat;
    }

    public String getTitre() {
        return Titre;
    }

    public int getId_societe() {
        return Id_societe;
    }

    public void setId_societe(int Id_societe) {
        this.Id_societe = Id_societe;
    }
    

    public void setTitre(String Titre) {
        this.Titre = Titre;
    }
    
    public void setId_Travail(int Id_Travail) {
        this.Id_Travail = Id_Travail;
    }

    public void setNom_soc(String Nom_soc) {
        this.Nom_soc = Nom_soc;
    }

    public void setAdr_mail_soc(String Adr_mail_soc) {
        this.Adr_mail_soc = Adr_mail_soc;
    }

    public void setAdr_soc(String Adr_soc) {
        this.Adr_soc = Adr_soc;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setDate_pub(Date Date_pub) {
        this.Date_pub = Date_pub;
    }

    public void setNiv_etude(String Niv_etude) {
        this.Niv_etude = Niv_etude;
    }

    public void setCertificat(String Certificat) {
        this.Certificat = Certificat;
    }

    public void setType_contrat(String Type_contrat) {
        this.Type_contrat = Type_contrat;
    }

    @Override
    public String toString() {
        return "OffreTravail{" + "Id_Stage=" + Id_Travail + ", Nom_soc=" + Nom_soc + ", Adr_mail_soc=" + Adr_mail_soc + ", Adr_soc=" + Adr_soc + ", Description=" + Description + ", Date_pub=" + Date_pub + ", Niv_etude=" + Niv_etude + ", Certificat=" + Certificat + ", Type_contrat=" + Type_contrat + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OffreTravail other = (OffreTravail) obj;
        if (this.Id_Travail != other.Id_Travail) {
            return false;
        }
        return true;
    }
    
    
}
