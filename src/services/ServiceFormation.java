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
import com.codename1.ui.events.ActionListener;
import java.util.List;
import entity.Formation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import utils.Statics;

/**
 *
 * @author Asus
 */
public class ServiceFormation {
     public ArrayList<Formation> formation;
      public ArrayList<Formation> achats;
     public boolean resultOK;
    private ConnectionRequest req;
    public static ServiceFormation instance = null;
    public ServiceFormation() {
        req = new ConnectionRequest();
    }

      public static ServiceFormation getInstance() {
        if (instance == null) {
            instance = new ServiceFormation();
        }
        return instance;
    }
      
    public ArrayList<Formation> parseTasks(String jsonText) {
        System.out.println("parseTask = "+jsonText);
        
            try {
                formation = new ArrayList<>();
                JSONParser j = new JSONParser();
              //parseJSON(new CharArrayReader(jsonText.toCharArray())
                Map<String, Object> t;
                t= j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
               System.out.println("hello1   "+t);
                List<Map<String, Object>> lst = (List<Map<String, Object>>) t.get("root");
                 System.out.println("list avant boucle"+lst);
                for (Map<String, Object> o : lst) {
                    System.out.println("afterrr");

                    Formation f = new Formation();

                    float id = Float.parseFloat(o.get("id").toString());
                     float prix = Float.parseFloat(o.get("prix").toString());
                f.setId((int) id);
                f.setTitre(o.get("titre").toString());
                f.setDescription(o.get("description").toString()) ;
                f.setPrix(prix);
                 f.setDifficulte(o.get("difficulte").toString()) ;
                f.setImage(o.get("image").toString());
               
                  
                    formation.add(f);
                    System.out.println("ffffff"+ f);
                }

            } catch (Exception ex) {

            }
            return formation;
        }
    
     public ArrayList<Formation> getAllFormations() {

        String url = Statics.BASE_URL + "/formation/M";
        req.setUrl(url);
        req.setPost(false);
        req.addArgument("format", "json");
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              formation = parseTasks(new String(req.getResponseData()));
                System.out.println("heeeeey : "+new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
         System.out.println("formation "+formation);

        return formation;
    }
     
      public ArrayList<Formation> getDetailFormation(int id) {

        String url = Statics.BASE_URL + "/formation/detailsM/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addArgument("format", "json");
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              formation = parseTasks(new String(req.getResponseData()));
                System.out.println("heeeeey : "+new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
         System.out.println("formation "+formation);

        return formation;
    }
     
     
    
         public boolean ajoutArticle(Formation f) {
       // création d'une nouvelle demande de connexion
        String url = "http://127.0.0.1:8000/formation/newM?id=" + f.getId()+ "&titre=" + f.getTitre()+"&description=" + f.getDescription()+"&prix=" + f.getPrix()+"&difficulte=" + f.getDifficulte()+"&cours="+f.getCours()+"&image="+f.getImage();// création de l'URL
       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
         }
         
         
          public boolean addFormation(Formation f) {
              //String url = Statics.BASE_URL + "/formation/newM";
       String url = "http://127.0.0.1:8000/formation/newM?id=" + f.getId()+ "&titre=" + f.getTitre()+"&description=" + f.getDescription()+"&prix=" + f.getPrix()+"&difficulte=" + f.getDifficulte()+"&cours="+f.getCours()+"&image="+f.getImage();// création de l'URL
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this); 
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
              System.out.println("herrrrreee"+resultOK);
        return resultOK;
    }

 
    
}
