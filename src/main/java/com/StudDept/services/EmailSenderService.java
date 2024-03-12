package com.StudDept.services;

import jakarta.mail.*;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@RequiredArgsConstructor
public class EmailSenderService {

    private final JavaMailSender javaMailSender;

    @Value("${application.properties:3}")
    private String sender;

    @Value("${application.properties:4}")
    private String password;

    private String buildEmail(String email, String subject, String msgBody) throws MessagingException {

       try{
           MimeMessage mimeMessage = javaMailSender.createMimeMessage();
           MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

           mimeMessageHelper.setFrom(sender);
           mimeMessageHelper.addTo(email);
           mimeMessageHelper.setSubject(subject);
           mimeMessageHelper.setText(msgBody);

           String path="D:\\Workspace\\POCs\\images\\wee.png";

           File file = new File(path);
           mimeMessageHelper.addAttachment(file.getName(), file);

           javaMailSender.send(mimeMessage);
           System.out.println("Email send!");

           return "Email is Send to user";

       }catch (Exception e){
           e.printStackTrace();
           return "Getting Error!";
       }
    }

    public String sendEmailToPri(String email) throws MessagingException {

        String sub = "User Register!!";
        String msg = "Hey! \n I hope this mail get's you well, you are register as an Principle in this data base and you can Access All the Operations in Related to principle. \n Thank You !";

        buildEmail(email, sub, msg);

        return "User Created Successfully!";
    }

    public String sendEmailToAuth(String email) throws MessagingException {

        String sub = "Authenticate User";
        String msg = "Hello!\n You Have a Authority to Access! and Created tokens for you\n Thank You !";

        buildEmail(email, sub, msg);

        return "User is Authenticate Successfully!";
    }

    public String sendEmailRefreshToken(String email) throws MessagingException {
        String sub = "Creating Refresh Token";
        String msg = "Hello!\n Created refresh token Successfully \n Thank You !";

        buildEmail(email, sub, msg);

        return "User's Refresh Token Created Successfully!";
    }
}
