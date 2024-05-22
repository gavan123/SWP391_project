/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import java.io.UnsupportedEncodingException;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.time.LocalDateTime;

public class Email {

    private static final String username = "noreply.pmarket@gmail.com";
    private static final String password = "ujdr bcbp ujuq yhkt";

    private static void sendMailTLS(String senderName, String toAddress, String subject, String text) throws UnsupportedEncodingException {

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username, senderName));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(toAddress)
            );
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);

            System.out.println("Message success fully sent at " + LocalDateTime.now());

        } catch (MessagingException me) {
            me.printStackTrace();
        }
    }

    private static void sendMailSSL(String senderName, String toAddress, String subject, String text) throws UnsupportedEncodingException {

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username, senderName));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(toAddress)
            );
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);

            System.out.println("Message success fully sent at " + LocalDateTime.now());

        } catch (MessagingException me) {
            me.printStackTrace();
        }
    }

    public static void sendMail(String fromAddress, String toAddress, String subject, String text) {
        try {
            sendMailTLS(fromAddress, toAddress, subject, text);
        } catch (UnsupportedEncodingException uee) {
            uee.printStackTrace();
        }
    }
    
    public static void sendMail(String fromAddress, String toAddress, String subject, String text, boolean legacyAuthMode) {
        if (legacyAuthMode) {
            try {
                sendMailSSL(fromAddress, toAddress, subject, text);
            } catch (UnsupportedEncodingException uee) {
                uee.printStackTrace();
            }
        } else {
            try {
                sendMailTLS(fromAddress, toAddress, subject, text);
            } catch (UnsupportedEncodingException uee) {
                uee.printStackTrace();
            }
        }
    }
}
