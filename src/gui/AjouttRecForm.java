/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.mycompany.myapp.MyApplication;
import entity.Publicite;
import entity.Reclamation;
import java.io.IOException;
import java.util.Date;
import services.ServicePublicite;
import services.ServiceReclamation;

/**
 *
 * @author hp
 */
public class AjouttRecForm  extends Form{
   
      String file ;
  
     
          Resources theme;
                      Resources themee = UIManager.initFirstTheme("/theme_1");

    
     public AjouttRecForm(Form previous,String email)  {
                super("Publicite", BoxLayout.y());
                System.out.println("emailllll+ "+email);
                  
                TextField examen = new TextField("", "examen", 20, TextArea.TEXT_CURSOR);
                 TextField nomf = new TextField("", "nom formateur", 20, TextArea.TEXT_CURSOR);
                  TextField desc = new TextField("", "description", 20, TextArea.TEXT_CURSOR);
                
                 TextField mail = new TextField("", "Mail", 20, TextArea.EMAILADDR);
                 
                 TextField date = new TextField("", "Date", 20, TextArea.TEXT_CURSOR);
                 
           
                 
               
                 
                  Button upload = new Button("Upload Image");
                  
                         Validator val_firstname = new Validator();
                            val_firstname.addConstraint(nomf, new LengthConstraint(8));
                            String text_saisir_des_caracteres = "^[0-9]+$";
                            val_firstname.addConstraint(examen, new RegexConstraint(text_saisir_des_caracteres, ""));
                            // val lastname   
                            Validator val_lastname = new Validator();
                            val_lastname.addConstraint(nomf, new LengthConstraint(8));
                            val_lastname.addConstraint(nomf, new RegexConstraint(text_saisir_des_caracteres, ""));
                  
                  
           String text_mail="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                            
                             // val mail   
                            Validator val_mail = new Validator();
                            val_mail.addConstraint(mail, new LengthConstraint(8));
                            val_mail.addConstraint(mail, new RegexConstraint(text_mail, ""));
        Button save = new Button("Ajouter");
        
                 
               
      
           save.addActionListener(l
                                -> {

                          
                            if (nomf.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de nom ", "OK", null);

                            } else if (val_firstname.isValid()) {
                                Dialog.show("Erreur FIRSTNAME !", "il faut saisir des caracteres  !", "OK", null);

                            } else if (examen.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide examen ", "OK", null);

                            }else if (val_lastname.isValid()) {
                                Dialog.show("Erreur LASTNAME !", "il faut saisir des caracteres  !", "OK", null);

                            } 
                            
                            
                            else if (mail.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de email ", "OK", null);

                            } else if (!val_mail.isValid()) {
                                Dialog.show("Erreur EMAIL !", "email incorrect", "OK", null);

                            }  
                              else if (desc.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de description ", "OK", null);

                            }
                                 
                            
                            else {
                                          
                                          
                                Reclamation p = new Reclamation();
                                p.setAdressem(mail.getText());
                                p.setExamen(examen.getText());
                                p.setNomformateur(nomf.getText());
                                p.setDate(date.getText());
                                p.setDescription(desc.getText());
                           
                               Form hi = new Form("Projet", BoxLayout.y());
                                ServiceReclamation sp = new ServiceReclamation();
                                sp.ajoutReclamation(p,themee);
                                 Dialog.show("Ajout", "Ajout", "OK", null);
                                
                ConnectionRequest cnreq = new ConnectionRequest();
                cnreq.setPost(false);
               // String data = "Nom : " + Nom.getText() + "<br>  Prenom : " + Prenom.getText() + " <br>  mail :" + mail.getText() + " <br> domaine : " + domaine.getText() + " <br> lien : " + lien.getText()+ " <br> Prix : " + String.valueOf( Integer.valueOf(affichage.getText())*  2000           )+" DT"+"<br> Merci pour votre confiance &#128525;";

             
                                 
                                 
                                 
                                 
                                 
                                 
                                                                    
                            }
           });
        
        
        
        
        
        
          this.add(examen).add(nomf).add(desc).add(mail).add(date).add(save);
               
        
           this.getToolbar().addCommandToOverflowMenu("back", null, ev -> {
           new MyApplication().start();
        });
        
        
        
                 
     }
     
}
