/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import gui.*;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import entity.Personnes;
import entity.Publicite;
import entity.Reclamation;

import java.util.ArrayList;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import utils.Statics;

/**
 *
 * @author asus
 */
public class ServiceReclamation {
public ArrayList<Reclamation> Reclamations;
    public static ServiceReclamation instance = null;

    private ConnectionRequest req;

    public static ServiceReclamation getInstance() {
        if (instance == null) {
            instance = new ServiceReclamation();
        }
        return instance;

    }

    public ServiceReclamation() {
        req = new ConnectionRequest();
    }

    public void ajoutReclamation(Reclamation reclamation,Resources form) {
        String url = Statics.BASE_URL + "addReclamation?adressem=" + reclamation.getAdressem() + "&examen=" + reclamation.getExamen() + "&date=" + reclamation.getDate() + "&nomformateur=" + reclamation.getNomformateur() + "&description=" + reclamation.getDescription();

        req.setUrl(url);
        //execute de url
        Personnes p=new Personnes();
        p.setEmail(SessionManager.getEmail());
        req.addResponseListener((e) -> {

            String str = new String(req.getResponseData());
            System.out.println("data==>" + str);

        });
        new Brainovationuser(form, p.getEmail()).show();
        //apres l execution de requéte url    
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
         ///detais reclamation
     public ArrayList<Reclamation> parseRec(String jsonText) {
        try {
            Reclamations = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ReclamationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) ReclamationListJson.get("root");

            for (Map<String, Object> obj : list) {
                Reclamation p = new Reclamation();

                float idReclamation = Float.parseFloat(obj.get("idReclamation").toString());
                p.setIdReclamation((int) idReclamation);

              

                p.setAdressem(obj.get("adressem").toString());
                p.setExamen(obj.get("examen").toString());
                p.setDate(obj.get("date").toString());
                p.setNomformateur(obj.get("nomFormateur").toString());
          p.setDescription(obj.get("description").toString());
            

                
                
             
                Reclamations.add(p);
            }

        } catch (IOException ex) {
            System.out.println("Exception in parsing reclamations ");
        }

        return Reclamations;
    }
     
     

   
      public ArrayList<Reclamation> DetailReclamation() {
        String url = Statics.BASE_URL + "detailReclamation";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                 Reclamations = parseRec(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  Reclamations;
    }
    
    
    
    
    
}
   

  