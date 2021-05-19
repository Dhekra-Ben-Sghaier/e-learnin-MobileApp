/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
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
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import entity.Formation;
import java.util.ArrayList;
import services.ServiceAchat;
/**
 *
 * @author Asus
 */
public class Acheter extends BaseForm{
 Form current;

    public Acheter(Resources res, Formation f) {
        
          super("Newsfeed", BoxLayout.y());
       
       
       Toolbar tb = new Toolbar(true);
       current = this;
       
       
       
       setToolbar(tb);
       
       getTitleArea().setUIID("Container");
       setTitle("");
       
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
        addTab(swipe,spacer1, res.getImage("payer.png"),  "", "", res);
       
        
       
     
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
        
        //  ListReclamationForm a = new ListReclamationForm(res);
          //  a.show();
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
       
       TextField idFormation = new TextField("", "");
       idFormation.setUIID("TextFieldBlack");
       addStringValue("Numero de carte          :",idFormation);
       
       TextField titre = new TextField("", "", 50, TextField.PASSWORD);
       titre.setUIID("TextFieldBlack");
       addStringValue("Code               :",titre);

       
       
     
         
       
       
       
       Button btnAjout = new Button("Ajouter");
       addStringValue("", btnAjout);
       
       //listner
       
       btnAjout.addActionListener((e) ->{
         
                   
       try{
                   if(idFormation.getText() == "" ||  titre.getText() == "" ){
                       
                       Dialog.show("Verifier les données","" ,"Annuler", "Ok");
                   }
                   else
                   {
                       InfiniteProgress ip = new InfiniteProgress();;
                       final Dialog iDialog = ip.showInfiniteBlocking();
                       ServiceAchat.getInstance().Acheter(f.getId());
                       btnAjout.setEnabled(false);
                      
                       //ServiceFormation.getInstance().addFormation(f);
                       iDialog.dispose();
                       refreshTheme();
                       
//                       list = new ArrayList<>();
//                       list.addAll(ServiceAchat.getInstance().getAllAchats());
                        ToastBar.showMessage("Achat effectué avec succée!", FontImage.MATERIAL_INFO);
                   }
                   
               }catch (Exception ee){
                   
                 
                      Dialog.show("Formation deja achetée","" ,"Annuler", "Ok");
                
                            }
       } );
       
   }

    private void addStringValue(String s, Component v) {
      
        add(BorderLayout.west(new Label(s, "PaddedLabel"))
        .add(BorderLayout.CENTER,v));
       // add(createLineSeparator(0xeeeeee));
        
        
        
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
        
        swipe.addTab("", res.getImage("payer.png"),page1);
        
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
