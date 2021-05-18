/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author skander
 */
public class Postuler_travail {
    private int Id_Travail;
    private int Id_user;

    public Postuler_travail() {
    }

    public Postuler_travail(int Id_Travail, int Id_user) {
        this.Id_Travail = Id_Travail;
        this.Id_user = Id_user;
    }

    public int getId_Travail() {
        return Id_Travail;
    }

    public int getId_user() {
        return Id_user;
    }

    public void setId_Travail(int Id_Travail) {
        this.Id_Travail = Id_Travail;
    }

    public void setId_user(int Id_user) {
        this.Id_user = Id_user;
    }
    
    
}
