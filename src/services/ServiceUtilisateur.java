/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.List;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import entity.Personnes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import java.util.Vector;

import utils.Statics;

/**
 *
 * @author benha
 */
public class ServiceUtilisateur {
    String  code;
      public Personnes utilisateur;
    //singleton
    
    public static ServiceUtilisateur instance=null;
    
    public static boolean resultoK=true;
    //initialisation connection request
    private ConnectionRequest req;
    
    public static ServiceUtilisateur getInstance(){
        if(instance==null)
                instance=new ServiceUtilisateur();
                return instance;
        
    }
    public ServiceUtilisateur(){
        req=new ConnectionRequest();
    }
    
    //signup apprenant
    public void signup(TextField cin ,TextField nom ,TextField prenom ,TextField email ,TextField password ,TextField nomutilisateur,TextField centreinteret,ComboBox<String> role ,Resources res){
    
       
        String url = Statics.BASE_URL + "user/appregister?cin="+cin.getText().toString()+"&nom="+nom.getText().toString()+"&prenom="+prenom.getText().toString()+"&email="+email.getText().toString()+"&nomutilisateur="+nomutilisateur.getText().toString()+"&password="+password.getText().toString()+
                "&centreinteret="+centreinteret.getText().toString()+"&role="+role.getSelectedItem().toString();
        req.setUrl(url);
        //execute de url
        req.addResponseListener((e)->{
            
            byte[]data= (byte[])e.getMetaData();
            String responseData=new String(data);
            System.out.println("data==>"+responseData);
            
            
            
        });
           //apres l execution de requéte url    
          NetworkManager.getInstance().addToQueueAndWait(req);
    }
    //signup formateur
    public void signupf(TextField cin ,TextField nom ,TextField prenom ,TextField email ,TextField password ,TextField nomutilisateur,TextField domaine,ComboBox<String> role ,Resources res){
    
       
        String url = Statics.BASE_URL + "user/formregister?cin="+cin.getText().toString()+"&nom="+nom.getText().toString()+"&prenom="+prenom.getText().toString()+"&email="+email.getText().toString()+"&nomutilisateur="+nomutilisateur.getText().toString()+"&password="+password.getText().toString()+
                "&domaine="+domaine.getText().toString()+"&role="+role.getSelectedItem().toString();
        req.setUrl(url);
        //execute de url
        req.addResponseListener((e)->{
            
            byte[]data= (byte[])e.getMetaData();
            String responseData=new String(data);
            System.out.println("data==>"+responseData);
            
            
            
        });
           //apres l execution de requéte url    
          NetworkManager.getInstance().addToQueueAndWait(req);
    }
    //signup societe
        public void signups(TextField nomsociete  ,TextField email ,TextField password ,TextField nomutilisateur,ComboBox<String> role ,Resources res){
    
       
        String url = Statics.BASE_URL +  "user/socregister?nomsociete="+nomsociete.getText().toString()+"&email="+email.getText().toString()+"&nomutilisateur="+nomutilisateur.getText().toString()
                +"&password="+password.getText().toString()+"&role="+role.getSelectedItem().toString();
        req.setUrl(url);
        //execute de url
        req.addResponseListener((e)->{
            
            byte[]data= (byte[])e.getMetaData();
            String responseData=new String(data);
            System.out.println("data==>"+responseData);
            
            
            
        });
           //apres l execution de requéte url    
          NetworkManager.getInstance().addToQueueAndWait(req);
    }
}