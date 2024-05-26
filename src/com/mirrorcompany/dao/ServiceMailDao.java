package com.mirrorcompany.dao;

import com.mirrorcompany.model.MessageModel;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 *
 * @author ekire
 */
public class ServiceMailDao {
    
    public MessageModel sendMain(String toEmail, String code) {
        MessageModel ms = new MessageModel(false, "");
        String from = "kkiregha@gmail.com";
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        String username = "kkiregha@gmail.com";
        String password = "gucw ykno vpge gnfq";   //  Your email password here
        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject("Travel ManageR | Verification Your OTP Code");
            message.setText(code);
            Transport.send(message);
            ms.setSuccess(true);
        } catch (MessagingException e) {
            e.printStackTrace();
            if (e.getMessage().equals("Invalid Addresses")) {
                ms.setMessage("Invalid email");
            } else {
                ms.setMessage("Failed to send | Check your connection");
            }
        }
        return ms;
    }
}