/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.MyApplication;
import entity.Personnes;
import entity.Publicite;
import entity.Reclamation;
import services.ServicePublicite;
import services.ServiceReclamation;

/**
 *
 * @author hp
 */
public class mesReclamations extends Form {
   Resources theme = UIManager.initFirstTheme("/theme");
   Resources themee = UIManager.initFirstTheme("/theme_1");
    public mesReclamations(Form previous)
    {
           super("Reclamations",BoxLayout.y());
             this.add(new InfiniteProgress());
        Display.getInstance().scheduleBackgroundTask(() -> {
            // this will take a while...

            Display.getInstance().callSerially(() -> {
                this.removeAll();
                Personnes pers=new Personnes();
                pers.setId(SessionManager.getId());
              
             for (Reclamation c : new ServiceReclamation().DetailReclamation()) {

            this.add(addItem_Publicite(c));

        }
             
              this.revalidate();
            });
        });

        this.getToolbar().addSearchCommand(e -> {
            String text = (String) e.getSource();
            if (text == null || text.length() == 0) {
                // clear search
                for (Component cmp : this.getContentPane()) {
                    cmp.setHidden(false);
                    cmp.setVisible(true);
                }
                this.getContentPane().animateLayout(150);
            } else {
                text = text.toLowerCase();
                for (Component cmp : this.getContentPane()) {
                    MultiButton mb = (MultiButton) cmp;
                    String line1 = mb.getTextLine1();
                    String line2 = mb.getTextLine2();
                  
                    boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1
                            || line2 != null && line2.toLowerCase().indexOf(text) > -1;
                    mb.setHidden(!show);
                    mb.setVisible(show);
                }
                this.getContentPane().animateLayout(150);
            }
        }, 4);
        Personnes pr=new Personnes();
        pr.setEmail(SessionManager.getEmail());
             
               this.getToolbar().addCommandToOverflowMenu("back", null, ev -> {
 new Brainovationuser(theme,pr.getEmail()).show();
        });
                   this.getToolbar().addCommandToOverflowMenu("Ajouter", null, ev -> {
          //new AjoutPubliciteForm(this).show();
        });
                 
    }
    
     public MultiButton addItem_Publicite(Reclamation c) {

 MultiButton m = new MultiButton();
   
   
     m.setTextLine1(c.getNomformateur()+""+ c.getExamen());
        m.setTextLine2(c.getAdressem());
       m.setTextLine3(c.getDescription());
        m.setTextLine4(c.getDate());
        
         m.setEmblem(theme.getImage("arrow.png"));
       
       Personnes p=new Personnes();
       p.setEmail(SessionManager.getEmail());
         
      Button btnretour=new Button();
      btnretour.setUIID("selectBar1");
       btnretour.addActionListener(e -> new Brainovationuser(theme,p.getEmail()).show());
        m.addActionListener(e -> {

            Form f2 = new Form("Detail",BoxLayout.y());
          
        
            f2.add("Nom formateur : "+c.getNomformateur()).add("Email : "+c.getAdressem()).add("Examen : "+c.getExamen()).add("description : "+c.getDescription()).add("date : "+c.getDate()).add(btnretour);
         
        EncodedImage enc2;
//        enc2 = EncodedImage.createFromImage(theme.getImage("image.jpg"), false);
      
           
             f2.getToolbar().addCommandToOverflowMenu("back", null, ev -> {
 new Brainovationuser(themee,p.getEmail()).show();
        });
       
 f2.show();
 
        });
      
     

     
        return m;

    }

}
