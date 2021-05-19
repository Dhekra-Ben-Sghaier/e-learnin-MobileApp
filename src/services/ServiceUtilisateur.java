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
import com.codename1.ui.Label;
import com.codename1.ui.List;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import entity.Personnes;
import gui.Brainovation;
import gui.Brainovationuser;
import gui.NewsfeedForm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import gui.SessionManager;

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
     //signin
     public void signin(TextField email ,TextField password ,Resources res){
            
             String url = Statics.BASE_URL +  "user/signin?email="+email.getText().toString()+"&password="+password.getText().toString();
             req=new ConnectionRequest(url,false);// false cad url n'a pas encore envoyé au seveur 
             req.setUrl(url);
             
              req.addResponseListener((NetworkEvent e)->{
            
     JSONParser j = new JSONParser();
     String json= new String(req.getResponseData())+"";
    try{
     if(json.equals("failed") ||json.equals("passowrd not found")){
         Dialog.show("Echec d'authentification", "Email ou mot de passe incorrect","OK",null );
     }else {
         System.out.println("data==>"+json);
         String pers=new String(req.getResponseData());
  Map<String, Object> user = j.parseJSON(new CharArrayReader(pers.toCharArray()));
////         Map<String,Object> user=j.parseJSON(new CharArrayReader(json.toCharArray()));
//       if(user.size() > 0)
           
//add session user
         float id = Float.parseFloat(user.get("idUser").toString());
                SessionManager.setId((int)id);//jibt id ta3 user ly3ml login w sajltha fi session ta3i
                
                SessionManager.setPassowrd(user.get("password").toString());
                SessionManager.setUserName(user.get("nomutilisateur").toString());
                SessionManager.setEmail(user.get("email").toString());
                
                //photo 
                
                if(user.get("photo") != null)
                    SessionManager.setPhoto(user.get("photo").toString());
                
                System.out.println("current user"+SessionManager.getEmail()+""+SessionManager.getUserName());
            new Brainovationuser(res,email.getText()).show();
            
         
     }
            
    }catch(Exception ex){
        ex.printStackTrace();
    }  
       
        });
               //apres l execution de requéte url    
          NetworkManager.getInstance().addToQueueAndWait(req);
            
        }
        
     // enregistrer code dans la bd
      public void code(String email ,String code ,Resources res){
            
             String url = Statics.BASE_URL +  "user/code?email="+email+"&code="+code;
             req=new ConnectionRequest(url,false);// false cad url n'a pas encore envoyé au seveur 
             req.setUrl(url);
             
              req.addResponseListener((e)->{
            
     JSONParser j = new JSONParser();
     String json= new String(req.getResponseData())+"";
    try{
     if(json.equals("failed") ){
         Dialog.show("Echec d'envoi", "Email ou mot de passe incorrect","OK",null );
     }else {
         System.out.println("data==>"+json);
    // Map<String,Object> user= j.parseJSON(new CharArrayReader(json.toCharArray()));
////         Map<String,Object> user=j.parseJSON(new CharArrayReader(json.toCharArray()));
//       if(user.size() > 0)
           // new NewsfeedForm(res).show();
           System.out.println("envoyé");
            
         
     }
            
    }catch(Exception ex){
        ex.printStackTrace();
    }  
       
        });
               //apres l execution de requéte url    
          NetworkManager.getInstance().addToQueueAndWait(req);
            
        }
        
     //preparer code mp
      public static  String envoyerCode(){
         Random r = new Random();
         return""+r.nextInt(100)+r.nextInt(100)+r.nextInt(100);
          
      
     }
         
      // recuperer code
       public boolean reccode(String email ,String code ,Resources res){
           
             String url = Statics.BASE_URL +  "user/getcode?email="+email+"&code="+code;
             req=new ConnectionRequest(url,false);// false cad url n'a pas encore envoyé au seveur 
             req.setUrl(url);
             
              req.addResponseListener((e)->{
            
     JSONParser j = new JSONParser();
     String json= new String(req.getResponseData())+"";
    try{
     if(json.equals("code incorrect") ){
        
         resultoK=false;
     }else {
         System.out.println("data==>"+json);
          System.out.println("code correct");
   
          
           resultoK=true;
         
     }
            
    }catch(Exception ex){
        ex.printStackTrace();
    }  
       
        });
               //apres l execution de requéte url    
          NetworkManager.getInstance().addToQueueAndWait(req);
          return resultoK;
            
        }
    
     //changer mot de passe
       public void changermdp(String email ,String mdp,String cmdp,Resources res){
            
             String url = Statics.BASE_URL +  "user/changePwd?email="+email+"&password="+mdp+"&confpassword="+cmdp;
             req=new ConnectionRequest(url,false);// false cad url n'a pas encore envoyé au seveur 
             req.setUrl(url);
             
              req.addResponseListener((e)->{
            
     JSONParser j = new JSONParser();
     String json= new String(req.getResponseData())+"";
        System.out.println("jspn="+json);
            try{
     if(json.equals("mdp invalide") ){
        
         System.out.println("incorrect");
         Dialog.show("Mot de passe", "Ces mots de passe ne correspondent pas. Veuillez réessayer.!","OK",null);
     }else if (json.equals("failed")){
          Dialog.show("Mot de passe", "utilisateur n'est pas trouvé!","OK",null );
     }else {
         System.out.println("data==>"+json);
            Dialog.show("Mot de passe", "Votre mdp a été changé avec succés!","OK",null );
     
      
          new Brainovationuser(res, email).show();
         
         
     }
            
    }catch(Exception ex){
        ex.printStackTrace();
    }
   

           });      //apres l execution de requéte url    
          NetworkManager.getInstance().addToQueueAndWait(req);
            
        
                      }
      //recinfo utilisateur
       public Personnes findAll(String email) {
        String url = Statics.BASE_URL + "user/recinfo?email="+email;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                utilisateur = parseUser(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return utilisateur;
    }
        public Personnes parseUser(String jsonText) {
        try {
            utilisateur = new Personnes();
            JSONParser j = new JSONParser();
            Map<String, Object> personne = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            System.out.println("aaaaaaaaaa"+personne);
           // List<Map<String, Object>> list = (List<Map<String, Object>>) ReclamationListJson.get("root");

            
                Personnes p = new Personnes();

           

              

                p.setNom(personne.get("nom").toString());
                p.setPrenom(personne.get("prenom").toString());
                p.setEmail(personne.get("email").toString());
                p.setNomUtilisateur(personne.get("nomutilisateur").toString());
                p.setPhoto(personne.get("photo").toString());
             

                
                
             
                utilisateur=p;
                 System.out.println("personnes ="+utilisateur);
            
        } catch (IOException ex) {
            System.out.println("Exception in parsing personnes ");
        }

        return utilisateur;
    }
       //enregistrer infos
       public void editprofil(TextField nom ,TextField prenom ,TextField email ,TextField nomutilisateur,Resources res){
    
       
        String url = Statics.BASE_URL + "user/ediUser?nom="+nom.getText().toString()+"&prenom="+prenom.getText().toString()+"&email="+email.getText().toString()+"&nomutilisateur="+nomutilisateur.getText().toString();
        req.setUrl(url);
        //execute de url
        req.addResponseListener((e)->{
            
            byte[]data= (byte[])e.getMetaData();
            String responseData=new String(data);
            System.out.println("data==>"+responseData);
       Dialog.show("Informations", "Informations Sauvegardées!","OK",null );
     
            
            
        });
           //apres l execution de requéte url    
          NetworkManager.getInstance().addToQueueAndWait(req);
    }
              
      //changerPhoto
      public void changerphoto(String email ,String photo,Resources res){
            
             String url = Statics.BASE_URL +  "user/modifierphoto?email="+email+"&photo="+photo;
             req=new ConnectionRequest(url,false);// false cad url n'a pas encore envoyé au seveur 
             req.setUrl(url);
             
              req.addResponseListener((e)->{
            
     JSONParser j = new JSONParser();
     String json= new String(req.getResponseData())+"";
        System.out.println("jspn="+json);
                  System.out.println("photo="+photo);
   

         Dialog.show("Informations", "Sauvegardées avec succés!","OK",null );
              
   
          
         
         
    
   

           });      //apres l execution de requéte url    
          NetworkManager.getInstance().addToQueueAndWait(req);
            
        
                      }
}
