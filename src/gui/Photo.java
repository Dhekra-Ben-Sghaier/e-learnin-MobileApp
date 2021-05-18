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

import com.codename1.capture.Capture;
import com.codename1.components.FloatingHint;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;

import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import entity.Personnes;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;




import services.ServiceUtilisateur;

/**
 * The user profile form
 *
 * @author Shai Almog
 */
public class Photo extends BaseForm {
private String file;
private Image im;
    public Photo(Resources res ,String email) {
      super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Profile");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res,email);
        
        tb.addSearchCommand(e -> {});
        
        
        Image img = res.getImage("profile-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

//        Label facebook = new Label("786 followers", res.getImage("facebook-logo.png"), "BottomPad");
//        Label twitter = new Label("486 followers", res.getImage("twitter-logo.png"), "BottomPad");
//        facebook.setTextPosition(BOTTOM);
//        twitter.setTextPosition(BOTTOM);
        //recImage
        Personnes p=ServiceUtilisateur.getInstance().findAll(email);
         String url = "http://localhost/image/"+p.getPhoto();
         Image imge;
        System.out.println("p phoo"+url);
        EncodedImage enc;
        enc = EncodedImage.createFromImage(res.getImage("arrow.png"), false);
        imge = URLImage.createToStorage(enc, url, url);
       
           System.out.println("umg "+imge);
           Label imfile= new Label(); 
          
        imfile.setIcon(imge);
        imfile.setUIID("PictureWhiteBackgrond");
         
          add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                    GridLayout.encloseIn(3, 
//                            facebook,
                            imfile
//                            twitter
                    )
                )
        ));
   
        //capture image 
        Form hi = new Form("Photo", new BoxLayout(BoxLayout.Y_AXIS));
        Button btncapture=new Button("Télecharger Image");
        Label lblImage=new Label();
        hi.add(btncapture);
        hi.add(lblImage);
        System.out.println("label"+lblImage );
       
        btncapture.addActionListener((ActionEvent e)->{
          
            try {
                    String fileNameInServer = "";
                    MultipartRequest cr = new MultipartRequest();
                    String filepath = Capture.capturePhoto(-1, -1);
                    cr.setUrl("http://localhost/image/uploadimage.php");
                    cr.setPost(true);
                    String mime = "image/jpeg";
                    cr.addData("file", filepath, mime);
                    String out = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
                    cr.setFilename("file", out + ".jpg");//any unique name you want
                    
                    fileNameInServer += out + ".jpg";
                    System.err.println("path2 =" + fileNameInServer);
                    file=fileNameInServer;
                    InfiniteProgress prog = new InfiniteProgress();
                    Dialog dlg = prog.showInifiniteBlocking();
                    cr.setDisposeOnCompletion(dlg);
                    NetworkManager.getInstance().addToQueueAndWait(cr);
                      Image imgprofil = Image.createImage(filepath);
           lblImage.setIcon(imgprofil);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
        });
        
      Button sauv=new Button("Valider");
sauv.addActionListener((e)->{
         ServiceUtilisateur.getInstance().changerphoto(email,file, res);
        });
        Container content = BoxLayout.encloseY(
                
           hi,sauv
        );
         content.setScrollableY(true);
        add(content);
    }
    
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
}
