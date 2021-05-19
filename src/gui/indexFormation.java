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
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
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
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import entity.Formation;
import java.io.IOException;
import java.util.ArrayList;

import services.ServiceFormation;


/**
 *
 * @author Asus
 */
public class indexFormation extends BaseForm{
    
     private ArrayList<Formation> list;
        Form current;

    public indexFormation(Resources res) {
        
        
       super("Newsfeed", BoxLayout.y());
       
       
       Toolbar tb = new Toolbar(true);
       current = this;
       
       
       
       setToolbar(tb);
       
       getTitleArea().setUIID("Container");
       setTitle("Aff Formation");
       
       getContentPane().setScrollVisible(false);
       
       tb.addSearchCommand(e -> {
           
       });
       
       Tabs swipe = new Tabs();
       Label s1 = new Label();
       Label s2 = new Label();
        Image img;
        EncodedImage enc;
           String url = "http://localhost/img/c#.png";
           
        enc = EncodedImage.createFromImage(res.getImage("signup-background.jpg"), false);
        img = URLImage.createToStorage(enc, url, url);
       addTab(swipe, s1,img,"","",res);
       
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
       
      //lister Formations
      
        ArrayList<Formation> list = ServiceFormation.getInstance().getAllFormations();

        for(Formation f : list){
           // addButton(f.getId(),f.getTitre(),f.getDescription(),f.getPrix(),f.getDifficulte());
//             Container c1 = new Container(BoxLayout.x());
//            
//               Label nom = new Label(""+f.getTitre());
//               //Spinner q = new Spinner();
//               nom.setUIID("LabelBlack");
//               Label prix = new Label(""+f.getPrix());
//               prix.setUIID("LabelBlack");
//               prix.getAllStyles().setPaddingLeft(8);
//               Label quantite= new Label(""+f.getDescription());
//               quantite.setUIID("LabelBlack");
//               quantite.getAllStyles().setPaddingLeft(8);
//               c1.addAll(nom,prix,quantite);
//           
//               add(c1);

           
            Button btnDetail = new Button();
            btnDetail.setText("Detail");
            btnDetail.getAllStyles().setPaddingLeft(2);
            btnDetail.addActionListener(e -> new DetailsFormation(res,f).show());

            addBtn(f);
            add(btnDetail);
           
        }
       
       
       //
        
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
        
        swipe.addTab("", res.getImage("C:\\Users\\Asus\\Desktop\\P\\images.png"),page1);
        
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

    private void addButton(int id, String titre, String description, float prix, String difficulte) {
        
        Container cnt = new Container();
        
        TextArea ta = new TextArea(titre);
        ta.setUIID("NewsTopLine");
        ta.setEditable(false);
        
        cnt.add(BorderLayout.CENTER, BoxLayout.encloseY(ta));
        
        add(cnt);
        
    
    }

     private void addBtnn(String title, Resources res) {
         ImageViewer imgage = new ImageViewer(); //Logger.getLogger(indexFormation.class.getName()).log(Level.SEVERE, null, ex);
         EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(this.getWidth()/3, this.getWidth()/3),false);
         //URLImage urlImage = URLImage.createToStorage(placeholder, f.getImage(), "http://localhost/img/"+f.getImage());
         URLImage urlImage = URLImage.createToStorage(placeholder, "c#.png", "http://localhost/img/c#.png");
         imgage.setImage(urlImage);
         int height = Display.getInstance().convertToPixels(11.5f);
         int width = Display.getInstance().convertToPixels(14f);
         
         //Button image = new Button(urlImage.fill(width, height));
        // image.setUIID("Label");
         //             //heeereee
//              EncodedImage enc = (EncodedImage) res.getImage("signup-background.jpg");
//             Image imgs;
//             ImageViewer imgv;
//             //String url = "http://127.0.0.1:8000/assets/img/shop-img/"+a.getImg();
//             String url =  "http://localhost/img/c#.png";
//             enc = EncodedImage.create("/signup-background.jpg");
//             imgs = URLImage.createToStorage(enc, url, url);
//             imgs.scaled(1500, 1200);
//             imgv = new ImageViewer(imgs);
//             add(imgv);
        Container cnt = BorderLayout.west(imgage);
        cnt.setLeadComponent(imgage);
        TextArea ta = new TextArea(title);
        ta.setUIID("NewsTopLine");
        ta.setEditable(false);
//       Label likes = new Label(likeCount + " Likes  ", "NewsBottomLine");
//       likes.setTextPosition(RIGHT);
//       if(!liked) {
//           FontImage.setMaterialIcon(likes, FontImage.MATERIAL_FAVORITE);
//       } else {
//           Style s = new Style(likes.getUnselectedStyle());
//           s.setFgColor(0xff2d55);
//           FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, s);
//           likes.setIcon(heartImage);
//       }
//       Label comments = new Label(commentCount + " Comments", "NewsBottomLine");
//       FontImage.setMaterialIcon(likes, FontImage.MATERIAL_CHAT);


cnt.add(BorderLayout.CENTER,
        BoxLayout.encloseY(
                ta));
add(cnt);
//imgage.addActionListener(e -> ToastBar.showMessage(title, FontImage.MATERIAL_INFO));
   }
     
  private void addBtn(Formation f) {
      
      
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
       TextArea ta = new TextArea(f.getTitre());
       ta.setUIID("LabelBlack");
       ta.setEditable(false);
       
//       TextArea t = new TextArea(f.getPrix()+" DT");
//       ta.setUIID("NewsTopLine");
//       ta.setEditable(false);

     
       
       cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                       ta
               ));
       add(cnt);
      // imgage.addActionListener(e -> ToastBar.showMessage(title, FontImage.MATERIAL_INFO));
   }
    

    
    
}
