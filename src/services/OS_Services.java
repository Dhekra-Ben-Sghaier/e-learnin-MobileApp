/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import entity.OffreStage ;
import com.codename1.io.CharArrayReader;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.DateFormatPatterns;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import entity.Postuler_stage;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import utils.Statics;
import java.util.Calendar;




//import java.util.logging.Level;
//import java.util.logging.Logger;


/**
 *
 * @author skander
 */
public class OS_Services {
    private ConnectionRequest req;
    public static OS_Services instance =null;
    
    public static OS_Services getinstance(){
    if (instance == null)
        instance = new OS_Services();
    return instance;
    }
    public OS_Services() {
        req = new ConnectionRequest();
    }
    
   public ArrayList<OffreStage> AffichageOffre(){
        ArrayList<OffreStage> result = new ArrayList<>();
        String URL=Statics.BASE_URL+"ListeOS";
        req.setUrl(URL);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                try {
                    Map<String,Object> mapOffres = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                      List<Map<String, Object>> listofmaps = (List<Map<String, Object>>)mapOffres.get("root");
                      for (Map<String, Object> obj : listofmaps) {
                          //Création des tâches et récupération de leurs données
                OffreStage e = new OffreStage();
                
               // DateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
               // Date date = format.parse(obj.get("postedIn").toString());
                
                float id = Float.parseFloat(obj.get("idStage").toString());

                e.setId_Stage((int) id);
                e.setNom_soc(obj.get("nomSoc").toString());
                e.setAdr_mail_soc(obj.get("adrMailSoc").toString());
                e.setAdr_soc(obj.get("adrSoc").toString());
                e.setDescription(obj.get("description").toString());
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                String Dateconverter= obj.get("datePub").toString().substring(obj.get("datePub").toString().indexOf("timestamp" ) + 10,obj.get("datePub").toString().lastIndexOf("}"));                               
                Date Datepub1 = new Date(Double.valueOf(Dateconverter).longValue() *1000);                
                String Datepub = formatter.format(Datepub1);
                e.setDate_pub(Datepub);
                e.setNiv_etude(obj.get("nivEtude").toString());
                e.setCertificat(obj.get("certificat").toString());
                e.setDuree((int) Float.parseFloat(obj.get("duree").toString()));
                //Date datedebut = format.parse(obj.get("dateDebut").toString());
                //e.setDate_debut(datedebut);
                //Date datefin = format.parse(obj.get("dateFin").toString());
                //e.setDate_debut(obj.get("dateDebut").toString());
                String Dateconverter1= obj.get("dateDebut").toString().substring(obj.get("dateDebut").toString().indexOf("timestamp" ) + 10,obj.get("dateDebut").toString().lastIndexOf("}"));
                Date Datedebut1 = new Date(Double.valueOf(Dateconverter1).longValue() *1000);                
                String Datedebut = formatter.format(Datedebut1);
                e.setDate_debut(Datedebut);
                String Dateconverter2= obj.get("dateFin").toString().substring(obj.get("dateFin").toString().indexOf("timestamp" ) + 10,obj.get("dateFin").toString().lastIndexOf("}"));
                Date Datefin1 = new Date(Double.valueOf(Dateconverter2).longValue() *1000);                
                String datefin = formatter.format(Datefin1);
                e.setDate_fin(datefin);
                //e.setDate_fin(obj.get("dateFin").toString());
                e.setId_societe((int) Float.parseFloat(obj.get("idSociete").toString()));
                e.setTitre(obj.get("titre").toString());
                e.setValide((int) Float.parseFloat(obj.get("valide").toString()));               
                e.setLogo(obj.get("logo").toString());
               // e.setPosted_in((Date) format.parse(obj.get("postedIn")));
                System.out.println(e);
                
                result.add(e);

                }
                } catch (Exception e) {
                    e.printStackTrace();
                }
              
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
   }
   public void Postuler(Postuler_stage post){
   String url = Statics.BASE_URL+"AddPost?IdUser="+post.getId_user()+"&IdStage="+post.getId_Stage();
   req.setUrl(url);
   req.addResponseListener((el)-> {
       String str = new String(req.getResponseData());
       System.out.println("data =="+str);
   });
   NetworkManager.getInstance().addToQueueAndWait(req);
   
}
}
