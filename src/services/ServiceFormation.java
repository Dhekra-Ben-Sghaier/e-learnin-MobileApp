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
import java.util.Map;
import utils.Statics;

/**
 *
 * @author Asus
 */
public class ServiceFormation {
     public ArrayList<Formation> formation;
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
                List<Map<String, Object>> list = (List<Map<String, Object>>) t.get("root");
                 
                for (Map<String, Object> o : list) {
                    System.out.println("afterrr");

                    Formation f = new Formation();

                    float id = Float.parseFloat(o.get("id").toString());
                f.setId((int) id);
                f.settitre(o.get("titre").toString());
                f.setDescription(o.get("description").toString()) ;
                f.setPrix((float) o.get("prix"));
               
                   

                    formation.add(f);
                    System.out.println("ffffff"+ f);
                }

            } catch (Exception ex) {

            }
            return formation;
        }
    
     public ArrayList<Formation> getAllTasks() {

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
    
}
