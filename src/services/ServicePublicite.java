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
import entity.Publicite;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utils.Statics;

/**
 *
 * @author hp
 */
public class ServicePublicite {
  public ArrayList<Publicite> Publicites;
    public static ServicePublicite instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
     public ServicePublicite() {
        req = new ConnectionRequest();
    }

    public static ServicePublicite getInstance() {
        if (instance == null) {
            instance = new ServicePublicite();
        }
        return instance;
    }
     public ArrayList<Publicite> parsePublicite(String jsonText) {
        try {
            Publicites = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ReclamationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) ReclamationListJson.get("root");

            for (Map<String, Object> obj : list) {
                Publicite p = new Publicite();

                float prix = Float.parseFloat(obj.get("prix").toString());
                p.setPrix((int) prix);

              

                p.setNom(obj.get("nom").toString());
                p.setPrenom(obj.get("prenom").toString());
                p.setEmail(obj.get("email").toString());
                p.setNom(obj.get("nom").toString());
                p.setDomaine(obj.get("domaine").toString());
          p.setAffichage(obj.get("affichage").toString());
            p.setImagee(obj.get("imagee").toString());  
             p.setLien(obj.get("lien").toString());  

                
                
             
                Publicites.add(p);
            }

        } catch (IOException ex) {
            System.out.println("Exception in parsing reclamations ");
        }

        return Publicites;
    }

     
       public ArrayList<Publicite> parsePubliciteStat(String jsonText) {
        try {
            Publicites = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ReclamationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) ReclamationListJson.get("root");

            for (Map<String, Object> obj : list) {
                Publicite p = new Publicite();

                float prix = Float.parseFloat(obj.get("prix").toString());
                p.setPrix((int) prix);

              

                p.setNom(obj.get("etat").toString());
              

                
                // questionnaire q =new questionnaire();
                // q.setDescription_cat_qst((String) map.get("description_cat_qst"));
             
                Publicites.add(p);
            }

        } catch (IOException ex) {
            System.out.println("Exception in parsing reclamations ");
        }

        return Publicites;
    }
     
    public ArrayList<Publicite> findAll() {
        String url = Statics.BASE_URL + "/publicite/Mobile_Pub";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Publicites = parsePublicite(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Publicites;
    }
    
     public ArrayList<Publicite> findAllStat() {
        String url = Statics.BASE_URL + "/Mobile_stats";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Publicites = parsePubliciteStat(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Publicites;
    }
    
 public void Add(Publicite p ) {
        String url = Statics.BASE_URL + "/publicite/Add_Publicite?image="+p.getImagee()+"&lien="+p.getLien()+"&affichage="+p.getAffichage()+"&nom="+p.getNom()+"&prenom="+p.getPrenom()+"&mail="+p.getEmail()+"&domaine="+p.getDomaine();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    
    }
}

