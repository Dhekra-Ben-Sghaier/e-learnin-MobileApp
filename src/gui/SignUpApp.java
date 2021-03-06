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
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import java.util.Vector;
import services.ServiceUtilisateur;

/**
 * Signup UI
 *
 * @author Shai Almog
 */
public class SignUpApp extends BaseForm {

    public SignUpApp(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
//        setUIID("SignIn");
          setUIID("appSignUp");
 
         
         TextField cin = new TextField("", "Cin", 20, TextField.ANY);
         TextField nom = new TextField("", "Nom", 20, TextField.ANY);
         TextField prenom = new TextField("", "Prenom", 20, TextField.ANY); 
        TextField nomUtilisateur = new TextField("", "Nom d'utilisateur", 20, TextField.ANY);
        TextField email = new TextField("", "E-Mail", 20, TextField.EMAILADDR);
        TextField password = new TextField("", "Mot de passe", 20, TextField.PASSWORD);
        TextField confirmPassword = new TextField("", "Confirmer Mot de passe", 20, TextField.PASSWORD);
        TextField cd = new TextField("", "Centre d'int??ret", 20, TextField.ANY);
        
           Vector<String> rolee;
       rolee=new Vector();
       
       rolee.add("apprenant");
       ComboBox<String> role= new ComboBox<>(rolee);
         cin.setSingleLineTextArea(false);
         nom.setSingleLineTextArea(false);
         prenom.setSingleLineTextArea(false); 
       nomUtilisateur.setSingleLineTextArea(false);
        email.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        confirmPassword.setSingleLineTextArea(false);
        cd.setSingleLineTextArea(false);
        Button next = new Button("S'inscrire");
        Button signIn = new Button("Sign In");
           signIn.addActionListener(e -> new SignInForm(res).show());
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Avez vous d??ja un compte?");
         Button AutreInscri =new Button("S'inscrire en tant qu'formateur/soci??t??","CenterLabel");
        AutreInscri.addActionListener(e -> new Inscription(res).show());
        
        Container content = BoxLayout.encloseY(
                new Label("S'inscrire", "LogoLabel"),
                 new FloatingHint(cin),
                createLineSeparator(),
                 new FloatingHint(nom),
                createLineSeparator(),
                 new FloatingHint(prenom),
                createLineSeparator(),
                new FloatingHint(nomUtilisateur),
                createLineSeparator(),
                new FloatingHint(email),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                new FloatingHint(confirmPassword),
                createLineSeparator(),
                 new FloatingHint(cd),
                createLineSeparator(),
                role
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                next,
                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn),AutreInscri
        ));
        next.requestFocus();
        next.addActionListener((e) -> {
            ServiceUtilisateur.getInstance().signup(cin, nom, prenom, email, password,nomUtilisateur,cd, role, res);
        });
    }

   
   
    
}
