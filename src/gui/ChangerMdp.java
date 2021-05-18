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
import com.codename1.components.InfiniteProgress;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;

import services.ServiceUtilisateur;

/**
 * Account activation UI
 *
 * @author Shai Almog
 */
public class ChangerMdp extends BaseForm {
TextField code;
    public ChangerMdp(Resources res, String email) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("IMGLogin");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        //setUIID("Activate");
        
        add(BorderLayout.NORTH, 
                BoxLayout.encloseY(
                       // new Label(res.getImage("oublier.png"), "LogoLabel")
                        
                )
        );

TextField mdp = new TextField("","Saisir mot de passe",20,TextField.PASSWORD);
TextField confmdp = new TextField("","Confirmer mot de passe",20,TextField.PASSWORD);

mdp.setSingleLineTextArea(false);
confmdp.setSingleLineTextArea(false);

Button valider=new Button("Valider");



Container content=BoxLayout.encloseY(
//new Label(res.getImage("oublier.png"),"CenterLabel"),
        new FloatingHint(mdp),
        createLineSeparator(),
        new FloatingHint(confmdp),
        createLineSeparator(),
        valider
);
content.setScrollableY(true);
add(BorderLayout.CENTER,content);
valider.requestFocus();
valider.addActionListener(e->{
    ServiceUtilisateur.getInstance().changermdp(email, mdp.getText(),confmdp.getText(), res);
});
    }
    

    
}
