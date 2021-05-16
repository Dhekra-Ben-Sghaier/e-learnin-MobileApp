/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package gui;

import com.codename1.components.FloatingHint;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.InfiniteContainer;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import services.ServiceUtilisateur;

/**
 * Sign in UI
 *
 * @author Shai Almog
 */
public class Inscription extends BaseForm {

    public Inscription(Resources res) {
        super(new BorderLayout());
          Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        if(!Display.getInstance().isTablet()) {
            BorderLayout bl = (BorderLayout)getLayout();
            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
        }
        tb.setUIID("Container");
       // setUIID("SignIn");
        
      
        //imageviewer
//Form hi = new Form("ImageViewer", new BorderLayout());
//
//Image red = Image.createImage(100, 100, 0xffff0000);
//Image green = Image.createImage(100, 100, 0xff00ff00);
//Image blue = Image.createImage(100, 100, 0xff0000ff);
//Image gray = Image.createImage(100, 100, 0xffcccccc);
//
//ImageViewer iv = new ImageViewer(red);
//iv.setImageList(new DefaultListModel<>(red, green, blue, gray));
//hi.add(BorderLayout.CENTER, iv);

Form hi = new Form("Layout Animations", new BoxLayout(BoxLayout.Y_AXIS));


        Image imge,imgf,imges;
        EncodedImage enc;
           String url = "http://localhost/img/app.jpg";
            String url1 = "http://localhost/img/form.jpg";
             String url2 = "http://localhost/img/soc.jpg";
        enc = EncodedImage.createFromImage(res.getImage("arrow.png"), false);
        imge = URLImage.createToStorage(enc, url, url);
         imgf = URLImage.createToStorage(enc, url1, url1);
          imges = URLImage.createToStorage(enc, url2, url2);



MultiButton Apprenant = new MultiButton("Apprenant");
Apprenant.setIcon(imge);
Apprenant.addActionListener(e->{
    new SignUpApp(res).show();
});


MultiButton formateur = new MultiButton("Formateur");
formateur.setIcon(imgf);
formateur.addActionListener(e->{
    new SignUpForm(res).show();
});
MultiButton societe = new MultiButton("Société");
societe.setIcon(imges);
societe.addActionListener(e->{
    new SignUpSoc(res).show();
});


hi. 
        add(Apprenant).
        add(formateur).add(societe);
        Container content = BoxLayout.encloseY(
               
               
        );
      
           

        content.setScrollableY(true);
        add(BorderLayout.NORTH, content);
        add(BorderLayout.CENTER, hi);
     
      //signIn.requestFocus();
      
        
      
         
    
    }
       
}
