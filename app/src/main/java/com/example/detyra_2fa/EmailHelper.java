package com.example.detyra_2fa;

import android.os.AsyncTask;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailHelper {

    private final String email;
    private final String subject;
    private final String message;
    private final String Emailsender = "your-email";
    private final String Passwordsender = "your-password";

    public EmailHelper(String email, String subject, String message) {
        this.email = email;
        this.subject = subject;
        this.message = message;
    }

    public void send() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    Properties props = new Properties();
                    props.put("mail.smtp.host", "smtp.gmail.com");
                    props.put("mail.smtp.port", "587");
                    props.put("mail.smtp.auth", "true");
                    props.put("mail.smtp.starttls.enable", "true");

                    Session session = Session.getInstance(props, new Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(Emailsender, Passwordsender);
                        }
                    });

                    Message mimeMessage = new MimeMessage(session);
                    mimeMessage.setFrom(new InternetAddress(Emailsender));
                    mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
                    mimeMessage.setSubject(subject);
                    mimeMessage.setText(message);

                    Transport.send(mimeMessage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }
}
