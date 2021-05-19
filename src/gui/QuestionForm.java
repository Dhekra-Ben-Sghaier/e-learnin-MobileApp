package gui;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.MyApplication;
import entity.Personnes;
import entity.Question;
import entity.Quiz;
import java.util.ArrayList;
import services.ServiceQuestion;


public class QuestionForm  extends Form {
String nom = SessionManager.getUserName();
    Resources theme = UIManager.initFirstTheme("/theme_1");
    int s=0;
    public ArrayList<String> bonne_reponses = new ArrayList<>();
    public ArrayList<String> reponses = new ArrayList<>();
    public QuestionForm(Form previous ,Quiz C)
    {
            super("Questions",BoxLayout.y());          
            for (Question c : new ServiceQuestion().findAll(C.getQuizid())) {
            this.add(addItem_Question(c));
        }
            Button btn = new Button("Valider");            
            this.add(btn);     
            btn.addActionListener(lm->{
                for (String bonne_reponse : bonne_reponses) {        
                    for (String reponse : reponses) {
                        if(bonne_reponse.equals(reponse))
                            {
                                s++;
                            }                   
                    }
                }                 
                if (s == bonne_reponses.size())
                {
                    Dialog.show("Félicitations", "Vous avez passez le quiz ", "OK", null);                     
                    Button btn_imprimer = new Button("imprimer");
                    btn_imprimer.addActionListener(laa->{
                    String urlab = "http://localhost/pdf/exx.php";

                        ConnectionRequest cnreq = new ConnectionRequest();
                        cnreq.setPost(false);
                       Personnes p=new Personnes();
                       p.setNomUtilisateur(nom);
                        String data = "<img src=\"/files/images/abc.jpg\">"+"Felicitations," + p.getNomUtilisateur() +"<br>"
                                + "Vous avez passer le quiz de ;" + C.getNom() ;
                        cnreq.addArgument("data", data);
                        cnreq.setUrl(urlab);

                        cnreq.addResponseListener(evx
                            -> {
                                String datap = new String(cnreq.getResponseData());    

                            });
                        NetworkManager.getInstance().addToQueueAndWait(cnreq);
                        Dialog.show("Pdf", "Pdf ", "OK", null);  
                   });
                   this.add(btn_imprimer);
                   this.refreshTheme();               
                 }
                 else
                 {
                    Dialog.show("Dommage", "Vzuillez réessayer ", "OK", null);
                    new MyApplication().start();               }
            });

            this.getToolbar().addCommandToOverflowMenu("Retour", null, ev -> {
            new MyApplication().start();
        });
    }
    
        public Container addItem_Question(Question q) {

            Container cn1 = new Container(new BorderLayout());
            Container cn2 = new Container(BoxLayout.y());
            CheckBox cb1 = new CheckBox(q.getOption1());
            CheckBox cb2 = new CheckBox(q.getOption2());
            CheckBox cb3 = new CheckBox(q.getOption3());
            CheckBox cb4 = new CheckBox(q.getOption4());
            bonne_reponses.add(q.getReponse());
            cb1.addActionListener(lll->{
            reponses.add(q.getOption1());
            });
            cb2.addActionListener(lll->{
            reponses.add(q.getOption2());
            });
            cb3.addActionListener(lll->{
            reponses.add(q.getOption3());
            });
            cb4.addActionListener(lll->{
            reponses.add(q.getOption4());
            });

            cn2.add("Question : "+q.getQuestion()).add(cb1).add(cb2).add(cb3).add(cb4).add("-------");
            cn1.add(BorderLayout.WEST, cn2);
            return cn1;
        }
        
}
