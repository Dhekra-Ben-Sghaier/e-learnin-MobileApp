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

import entity.Quiz;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utils.Statics;

/**
 *
 * @author Tarek.Loussaief
 */
public class ServiceQuiz {
      public ArrayList<Quiz> Quizs;
    public static ServiceQuiz instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
     public ServiceQuiz() {
        req = new ConnectionRequest();
    }

    public static ServiceQuiz getInstance() {
        if (instance == null) {
            instance = new ServiceQuiz();
        }
        return instance;
    }
     public ArrayList<Quiz> parseQuiz(String jsonText) {
        try {
            Quizs = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ReclamationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) ReclamationListJson.get("root");

            for (Map<String, Object> obj : list) {
                Quiz q = new Quiz();
                float id = Float.parseFloat(obj.get("quizid").toString());
                q.setQuizid((int) id);
                q.setNom(obj.get("nom").toString());
                   
             
                Quizs.add(q);
            }

        } catch (IOException ex) {
            System.out.println("Exception in parsing reclamations ");
        }

        return Quizs;
    }

    public ArrayList<Quiz> findAll() {
        String url = Statics.BASE_URL + "/List_quiz";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Quizs = parseQuiz(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Quizs;
    }

}
