/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.FloatingHint;
import entity.OffreStage;
import com.codename1.components.ImageViewer;
import com.codename1.components.ShareButton;
import com.codename1.components.SpanLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import entity.Personnes;
import entity.Postuler_stage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import services.OS_Services;
import javax.mail.Message;
import javax.mail.Transport;


/**
 *
 * @author skander
 */
public class AffichageOffreStage {
     Form f;
    SpanLabel lb;
     private Resources theme;
     int Id_User=SessionManager.getId();
  
    public AffichageOffreStage() {
        
        theme=UIManager.initFirstTheme("/theme_1");
        f = new Form();
        lb = new SpanLabel("");
        Button home=new Button();
        System.out.println("email"+SessionManager.getEmail() );
        String emaill=SessionManager.getEmail();
        home.addActionListener(e->{
            new Brainovationuser(theme,emaill);
        });
        f.add(lb);
        
        f.add(home);
      
        //OS_Services serviceTask =new OS_Services();
        ArrayList<OffreStage> ListOffres = OS_Services.getinstance().AffichageOffre();
        //lb.setText(ListOffres.toString());
       
        Container Articles = new Container(BoxLayout.y());
             
             for (OffreStage l : ListOffres) {                 
             f.add(addItem(l,f));                     
             }                   
          //f.getToolbar().addCommandToRightBar("back", null, (ev)->{
             // HomeForm h=new HomeForm();
          //h.getF().show();
          //});
    }
    
    public Container addItem(OffreStage l,Form h)
    {
        Postuler_stage post = new Postuler_stage();
        Form f1 = new Form("Detail",BoxLayout.y());
            
             Label lbl = new Label(l.getTitre());
             Label lbl_1 = new Label(l.getNom_soc());
             Button btn = new Button("afficher");
             Label lbl2= new Label("Titre");
             Label lbl3= new Label("Nom société");
             Container ctn2 = new   Container(BoxLayout.x());
             ctn2.add(lbl2);
             ctn2.add(lbl3);
             Container ctn = new Container(BoxLayout.x());
             ctn.add(lbl);
             ctn.add(lbl_1);            
             ctn.add(btn);             
             Container ctn1 = new Container(BoxLayout.y());
             ctn1.add(ctn2);
             ctn1.add(ctn);
             
        
        ctn1.setLeadComponent(btn);
        
        btn.addActionListener(e->{
      
            System.out.println(l.getTitre());
            f1.add(new Label(l.getTitre()));
             
             f1.add(new Label("nom  :"+l.getNom_soc()));
             f1.add(new Label("Adresse  :"+l.getAdr_soc()));
             f1.add(new Label("Adresse mail  :"+l.getAdr_mail_soc()));
             f1.add(new Label("Date publication :"+l.getDate_pub()));
             f1.add(new Label("Date debut   :"+l.getDate_debut()));
             f1.add(new Label("Date fin   :"+l.getDate_fin()));
             f1.add(new Label("Niveau d'etude :"+l.getNiv_etude()));
             f1.add(new Label("Certificat     :"+l.getCertificat()));
             f1.add(new Label("Description    :"+l.getDescription()));
             TextField email = new TextField("","Inserez votre adresse mail",20,TextField.ANY);
             email.setSingleLineTextArea(false);
             Button Send = new Button("Postuler");
             TextField body = new TextField("","Ecrivez une lettre de motivation bref",20,TextField.ANY);
             f1.add(email);
             f1.add(body);
             f1.add(Send);
                 Personnes p=new Personnes();
       p.setEmail(SessionManager.getEmail());
         
        Button btnretour= new Button("retour");
       
      
       f1.add(btnretour);
             String Titre = l.getTitre();
             f1.getToolbar().addCommandToLeftBar("Back", null, tl -> h.showBack());
             f1.show();
            btnretour.addActionListener(z->{
            new Brainovationuser(theme, p.getEmail()).show();
        });
             Send.addActionListener(a->{
                try {
                    if(email.getText()=="" || body.getText()==""){
                        Dialog.show("Veuillez remplir tout les champs","", "Annuler", "Ok");
                    }else{
                    post.setId_Stage(l.getId_Stage());
                    post.setId_user(Id_User);
                    OS_Services.getinstance().Postuler(post);
                    sendMail(l.getAdr_mail_soc(), email.getText(), l.getTitre(), body.getText());
                    Dialog.show("Votre demande est envoyé avec succes","", "", "Ok");
                    }
                    } catch (Exception ex) {}
               email.setText("");
               body.setText("");
                
             });
             
             
             
        });
        
        
        return ctn1;
    }
    public  void sendMail(String recepient, String email, String titre,String body) throws Exception {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();

        //Enable authentication
        properties.put("mail.smtp.auth", "true");
        //Set TLS encryption enabled
        properties.put("mail.smtp.starttls.enable", "true");
        //Set SMTP host
        properties.put("mail.smtp.host", "smtp.gmail.com");
        //Set smtp port
        properties.put("mail.smtp.port", "587");

        //Your gmail address
        String myAccountEmail = "brainovation21@gmail.com";
        //Your gmail password
        String password = "brainovation$$$";

        //Create a session with account credentials
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
               return new javax.mail.PasswordAuthentication(myAccountEmail, password);
            }
            
            
});
           

        //Prepare email message
        Message message = prepareMessage(session, myAccountEmail, recepient, email, titre, body);

        //Send mail
        Transport.send(message);
        System.out.println("Message sent successfully");
    }
private  Message prepareMessage(Session session, String myAccountEmail, String recepient, String email, String titre,String body) {
        try {
           //OffreStage ol = list.getItems().get(list.getSelectionModel().getSelectedIndex());
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Recrutment pour l'offre"+titre);
            message.setText("L'adresse :"+email+"-- Lettre de motivation :"+body);
            return message;
        } catch (Exception ex) {
            //Logger.getLogger(controller.Affichage_StageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    
}
