/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import entity.Formation;
import java.util.ArrayList;
import services.ServiceFormation;

/**
 *
 * @author Asus
 */
public class DetailsFormation extends BaseForm{
    
      private ArrayList<Formation> list;
        Form current;

    public DetailsFormation(Resources res, Formation f) {
        
                super("Newsfeed", BoxLayout.y());
       
       
       Toolbar tb = new Toolbar(true);
       current = this;
       
       
       
       setToolbar(tb);
       
       getTitleArea().setUIID("Container");
       setTitle("Formation");
       
       getContentPane().setScrollVisible(false);
       
       tb.addSearchCommand(e -> {
           
       });
       
       Tabs swipe = new Tabs();
       Label s1 = new Label();
       Label s2 = new Label();
       /* Image img;
        EncodedImage enc;
           String url = "http://localhost/img/c#.png";
           
        enc = EncodedImage.createFromImage(res.getImage("details.png"), false);
        img = URLImage.createToStorage(enc, url, url);
       addTab(swipe, s1,img,"","",res);*/
        Label spacer1 = new Label();
       // Label spacer2 = new Label();
        addTab(swipe,spacer1, res.getImage("details.png"),  "", "", res);
       
       // 
       
       
         swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Component.setSameSize(radioContainer, s1, s2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));

        ButtonGroup barGroup = new ButtonGroup();
        RadioButton mesListes = RadioButton.createToggle("Mes Reclamations", barGroup);
        mesListes.setUIID("SelectBar");
        RadioButton liste = RadioButton.createToggle("Autres", barGroup);
        liste.setUIID("SelectBar");
        RadioButton partage = RadioButton.createToggle("Reclamer", barGroup);
        partage.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");


        mesListes.addActionListener((e) -> {
               InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking();
        
       
          
          
          
            refreshTheme();
        });

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3, mesListes, liste, partage),
                FlowLayout.encloseBottom(arrow)
        ));

        partage.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(partage, arrow);
        });
        bindButtonSelection(mesListes, arrow);
        bindButtonSelection(liste, arrow);
        bindButtonSelection(partage, arrow);
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });

       //
       
//       
//       //
       Container c = new Container((BoxLayout.x()));
        ImageViewer imgage = new ImageViewer(); //Logger.getLogger(indexFormation.class.getName()).log(Level.SEVERE, null, ex);
       EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(this.getWidth()/3, this.getWidth()/3),false);
       System.out.println("urllll  "+  "http://localhost/webPidevv/PidevWeb/public/uploads/"+f.getImage());
         URLImage urlImage = URLImage.createToStorage(placeholder, f.getImage(), "http://localhost/webPidevv/PidevWeb/public/uploads/"+f.getImage());
        // URLImage urlImage = URLImage.createToStorage(placeholder,"http://localhost/img/c%23.png","http://localhost/img/c%23.png");
         imgage.setImage(urlImage);
         
         
         int height = Display.getInstance().convertToPixels(11.5f);
         int width = Display.getInstance().convertToPixels(14f);
       
         
     //Button image = new Button(img.fill(width, height));
      // image.setUIID("Label");
       Container cnt = BorderLayout.west(imgage);
       cnt.setLeadComponent(imgage);
         Label likes = new Label();
       ServiceFormation.getInstance().getDetailFormation(66);
       Style heartStyle = new Style(likes.getUnselectedStyle());
        heartStyle.setFgColor(0xff2d55);
        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, heartStyle);
       Button acheter ;
        acheter = new Button(FontImage.createMaterial(FontImage.MATERIAL_ADD, heartStyle));
  
         acheter.setText("Acheter");
         acheter.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {

                new Acheter(res,f).show();
                
                
             

            }

                  
                });
        Label text = new Label(ServiceFormation.getInstance().getDetailFormation(66).toString());
          Label id = new Label("Reference :"+f.getId());
          id.setUIID("LabelBlack");
          
           Label titre = new Label("Titre :"+f.getTitre());
          titre.setUIID("LabelBlack");
          
           Label description = new Label("Description :"+f.getDescription());
          description.setUIID("LabelBlack");
       
            Label prix = new Label("Prix :"+f.getPrix()+" Dt");
            prix.setUIID("LabelBlack");
            
            Label difficulte = new Label("Difficulte :"+f.getDifficulte());
            difficulte.setUIID("LabelBlack");
        
        c.add(cnt);
         
        add(c);
        addAll(id,titre,description,prix,difficulte);
        add(acheter);
        
      
        
        
        
    }
    
    
    
    private void addTab(Tabs swipe,Label spacer, Image image, String text, String string2, Resources res) {
         
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if(image.getHeight() < size){
            
            image = image.scaledHeight(size);
        }
        
        if(image.getHeight() > Display.getInstance().getDisplayHeight() /2){
            
            image = image.scaledHeight(Display.getInstance().getDisplayHeight() /2);
        }
        
        ScaleImageLabel imageScale = new ScaleImageLabel(image);
        imageScale.setUIID("Container");
        imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        Label overLay = new Label("", "ImageOverlay");
        
        Container page1 = LayeredLayout.encloseIn(imageScale,
                BorderLayout.south(
                BoxLayout.encloseY(
                new SpanLabel(text, "LabelWithWhiteText"),
               
                spacer)));
        
        swipe.addTab("", res.getImage("details.png"),page1);
        
    }
    
    public void bindButtonSelection(Button btn,Label l){
        
        btn.addActionListener(e -> {
            if(btn.isSelected()){
                updateArrowPosition(btn,l);
            }
        });
    }

    private void updateArrowPosition(Button btn, Label l) {
        
    l.getUnselectedStyle().setMargin(LEFT, btn.getX() + btn.getWidth() /2 - l.getWidth() /2);
    l.getParent().repaint();
    }
    
    
    
}
