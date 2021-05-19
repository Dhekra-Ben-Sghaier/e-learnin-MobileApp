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
import entity.Formation;
import entity.Personnes;
import gui.SessionManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utils.Statics;

/**
 *
 * @author Asus
 */
public class ServiceAchat {
      int Id_User=SessionManager.getId();
     public ArrayList<Formation> formation;
      public ArrayList<Formation> achats;
     public boolean resultOK;
    private ConnectionRequest req;
    public static ServiceAchat instance = null;
    public ServiceAchat() {
        req = new ConnectionRequest();
    }

      public static ServiceAchat getInstance() {
        if (instance == null) {
            instance = new ServiceAchat();
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
    
    public ArrayList<Formation> getAllAchats() {
//yyyy 
Personnes p=new Personnes();
p.setId(Id_User);
        String url = Statics.BASE_URL + "formation/mesFormationsAchatsM/"+p.getId();
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
      public boolean Acheter(int id) {
//yyyy
Personnes p=new Personnes();
p.setId(Id_User);
        String url = Statics.BASE_URL + "achat/newM/"+id+"/"+p.getId();
        req.setUrl(url);
      
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              //formation = parseTasks(new String(req.getResponseData()));
                System.out.println("heeeeey : "+new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
      
         System.out.println("formation "+formation);

        return resultOK;
    }
     
    
}
