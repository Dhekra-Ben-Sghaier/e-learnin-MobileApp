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
public class Postuler_stage {
    private int Id_Stage;
    private int Id_user;

    public Postuler_stage() {
    }

    public Postuler_stage(int Id_Stage, int Id_user) {
        this.Id_Stage = Id_Stage;
        this.Id_user = Id_user;
    }

    public int getId_Stage() {
        return Id_Stage;
    }

    public int getId_user() {
        return Id_user;
    }

    public void setId_Stage(int Id_Stage) {
        this.Id_Stage = Id_Stage;
    }

    public void setId_user(int Id_user) {
        this.Id_user = Id_user;
    }
    
    
}

