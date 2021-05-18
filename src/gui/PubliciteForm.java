/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
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
import entity.Publicite;
import services.ServicePublicite;

/**
 *
 * @author hp
 */
public class PubliciteForm extends Form {
   Resources theme = UIManager.initFirstTheme("/theme");
    public PubliciteForm(Form previous)
    {
           super("Publicite",BoxLayout.y());
             this.add(new InfiniteProgress());
        Display.getInstance().scheduleBackgroundTask(() -> {
            // this will take a while...

            Display.getInstance().callSerially(() -> {
                this.removeAll();
             for (Publicite c : new ServicePublicite().findAll()) {

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
        
             
               this.getToolbar().addCommandToOverflowMenu("back", null, ev -> {
           new MyApplication().start();
        });
                   this.getToolbar().addCommandToOverflowMenu("Ajouter", null, ev -> {
           new AjoutPubliciteForm(this).show();
        });
                     this.getToolbar().addCommandToOverflowMenu("Stat", null, ev -> {
     new StatForm().createPieChartForm("Pubs", new ServicePublicite().findAllStat());
        });
    }
    
     public MultiButton addItem_Publicite(Publicite c) {

 MultiButton m = new MultiButton();
   String url = "http://localhost/image/"+c.getImagee();
   
     m.setTextLine1(c.getDomaine());
        m.setTextLine2(c.getEmail());
       m.setTextLine3(String.valueOf(c.getPrix()));
         m.setEmblem(theme.getImage("arrow.png"));
        Image imge;
        EncodedImage enc;
        enc = EncodedImage.createFromImage(theme.getImage("arrow.png"), false);
        imge = URLImage.createToStorage(enc, url, url);
        m.setIcon(imge);
      
        m.addActionListener(e -> {

            Form f2 = new Form("Detail",BoxLayout.y());
           
         
        
            f2.add("Domaine : "+c.getDomaine()).add("Email : "+c.getEmail()).add("Lien : "+c.getLien()).add("Nom : "+c.getNom()).add("Prenom : "+c.getPrenom()).add("Affichage : "+c.getAffichage()).add("Prix : "+c.getPrix());
          String url2 = "http://localhost/image/"+c.getImagee();

  Image imge2;
        EncodedImage enc2;
        enc2 = EncodedImage.createFromImage(theme.getImage("image.jpg"), false);
        imge2 = URLImage.createToStorage(enc2, url2, url2);
        f2.add(imge2);
           
             f2.getToolbar().addCommandToOverflowMenu("back", null, ev -> {
           new MyApplication().start();
        });      
         
 f2.show();
        });
      
     

     
        return m;

    }

}
