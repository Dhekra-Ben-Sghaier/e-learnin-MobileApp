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
import entity.Question;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utils.Statics;

/**
 *
 * @author Tarek.Loussaief
 */
public class ServiceQuestion {
   public ArrayList<Question> Questions;
    public static ServiceQuestion instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
     public ServiceQuestion() {
        req = new ConnectionRequest();
    }

    public static ServiceQuestion getInstance() {
        if (instance == null) {
            instance = new ServiceQuestion();
        }
        return instance;
    }
     public ArrayList<Question> parseQuestion(String jsonText) {
        try {
            Questions = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ReclamationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) ReclamationListJson.get("root");

            for (Map<String, Object> obj : list) {
                Question q = new Question();
                float id = Float.parseFloat(obj.get("id").toString());
                q.setId((int) id);
                q.setQuestion(obj.get("question").toString());
                q.setOption1(obj.get("option1").toString());
                q.setOption2(obj.get("option2").toString());
                q.setOption3(obj.get("option3").toString());
                q.setOption4(obj.get("option4").toString());
                q.setReponse(obj.get("reponse").toString());      
             
                Questions.add(q);
            }

        } catch (IOException ex) {
            System.out.println("Exception in parsing reclamations ");
        }

        return Questions;
    }

    public ArrayList<Question> findAll(int id_quiz) {
        String url = Statics.BASE_URL + "/List_ques/"+id_quiz;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Questions = parseQuestion(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Questions;
    }

}
