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
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import services.ServiceUtilisateur;

/**
 * Sign in UI
 *
 * @author Shai Almog
 */
public class SignInForm extends BaseForm {
private Resources themeimg;
    public SignInForm(Resources res) {
        super(new BorderLayout());
        
        if(!Display.getInstance().isTablet()) {
            BorderLayout bl = (BorderLayout)getLayout();
            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
        }
        getTitleArea().setUIID("Container");
       setUIID("SignIn");
 add(BorderLayout.NORTH, 
                BoxLayout.encloseY(
                        new Label(res.getImage("logoB.png"), "LogoLabel")
                        
                )
        );
        TextField login = new TextField("", "Login", 20, TextField.ANY);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        login.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        Button signIn = new Button("Sign In");
        Button signUp = new Button("Sign Up");
        //mp oublie 
        Button mp =new Button("Mot de passe oubli?? ?","CenterLabel");
        
        signUp.addActionListener(e -> new SignUpForm(res).show());
        signUp.setUIID("Link");
        Label doneHaveAnAccount = new Label("Vous n'avez pas de compte");
        
        Container content = BoxLayout.encloseY(
                
                new FloatingHint(login),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                signIn,
                FlowLayout.encloseCenter(doneHaveAnAccount, signUp),mp
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
       signIn.requestFocus();
      
        
         signIn.addActionListener(e->{
            ServiceUtilisateur.getInstance().signin(login, password, res);
        });
         
         
         //mp oublie event 
         mp.addActionListener((e)->{
             new ActivateForm(res).show();
         });
    }
    
}
