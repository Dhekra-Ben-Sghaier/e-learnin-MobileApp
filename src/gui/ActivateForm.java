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
import com.sun.mail.smtp.SMTPTransport;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import services.ServiceUtilisateur;

/**
 * Account activation UI
 *
 * @author Shai Almog
 */
public class ActivateForm extends BaseForm {
TextField email;
    public ActivateForm(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("IMGLogin");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("Activate");
        
        add(BorderLayout.NORTH, 
                BoxLayout.encloseY(
                        new Label(res.getImage("oublier.png"), "LogoLabel")
                        
                )
        );
        
//        TextField code = new TextField("", "Enter Code", 20, TextField.PASSWORD);
//        code.setSingleLineTextArea(false);
//        
//        Button signUp = new Button("Sign Up");
//        Button resend = new Button("Resend");
//        resend.setUIID("CenterLink");
//        Label alreadHaveAnAccount = new Label("Already have an account?");
//        Button signIn = new Button("Sign In");
//        signIn.addActionListener(e -> previous.showBack());
//        signIn.setUIID("CenterLink");
//        
//        Container content = BoxLayout.encloseY(
//                new FloatingHint(code),
//                createLineSeparator(),
//                new SpanLabel("We've sent the confirmation code to your email. Please check your inbox", "CenterLabel"),
//                resend,
//                signUp,
//                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
//        );
//        content.setScrollableY(true);
//        add(BorderLayout.SOUTH, content);
//        signUp.requestFocus();
//        signUp.addActionListener(e -> new NewsfeedForm(res).show());
email= new TextField("","Saisir votre email",20,TextField.ANY);
email.setSingleLineTextArea(false);

Button valider=new Button("Valider");
Label haveAnAccount= new Label("se connecter");



Container content=BoxLayout.encloseY(
//new Label(res.getImage("oublier.png"),"CenterLabel"),
        new FloatingHint(email),
        createLineSeparator(),
        valider,
        FlowLayout.encloseCenter(haveAnAccount)
);
content.setScrollableY(true);
add(BorderLayout.CENTER,content);
valider.requestFocus();
valider.addActionListener(e->{
    InfiniteProgress ip=new InfiniteProgress();
    final Dialog ipDialog=ip.showInfiniteBlocking();
            try {
                sendMail(email.getText().toString(),res);
            } catch (MessagingException ex) {
               ex.printStackTrace();
            }
    ipDialog.dispose();
    Dialog.show("Mot de passe","Nous avons envoyé le code à votre email.Veuillez vérifier votre boite de réception",new Command("ok"));
new CodeForm(res,email.getText()).show();

});

refreshTheme();

    }
    
   public static void sendMail(String recepient ,Resources res ) throws MessagingException{
        Properties properties= new Properties();
        
        
//        mail.smtp.auth
properties.put("mail.smtp.auth", "true");
//        mail.smtp.starttls.enable
 properties.put("mail.smtp.starttls.enable", "true");
//        mail.smtp.host=smtp.gmail.com
properties.put("mail.smtp.host", "smtp.gmail.com");
//        mail.smtp.port =587
 properties.put("mail.smtp.port", "587");
  //Your gmail address
        String myAccountEmail = "brainovation21@gmail.com";
        //Your gmail password
        String password = "brainovation$$$";
 //Create a session with account credentials
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });
        //Prepare email message
        Message message = prepareMessage(res,session, myAccountEmail, recepient );

        //Send mail
       Transport.send(message);
        System.out.println("Message sent successfully");
    }

    private static Message prepareMessage(Resources res,Session session, String myAccountEmail, String recepient ) {
try {      //Operation o= new Operation();
           String code;
           code=ServiceUtilisateur.envoyerCode();
          ServiceUtilisateur.getInstance().code(recepient, code, res);
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Réinitialiser le mot de passe ");
            String htmlCode = "<p> Bonjour, </p> <br/> <p> " +
"<p>Nous avons reçu une demande de réinitialisation de votre mot de passe .</p> <br>" +
"<p>Entrez le code de réinitialisation du mot de passe suivant :</p>"+"<br/>"+"<h2>"+code+"</h2>";
            message.setContent(htmlCode, "text/html");
            return message;
        } catch (Exception ex) {
           ex.printStackTrace();
        }
        return null;
    }
    
}
