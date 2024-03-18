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

    @Value("${spring.mail.username}")
    private String sender;

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
        String msg = "Hello!\n And,  Here you have that token. To access that authenticated urls...\n Thank You !";

        buildEmail(email, sub, msg);

        return "User is Authenticate Successfully!";
    }

    public String sendEmailRefreshToken(String email) throws MessagingException {
        String sub = "Creating Refresh Token";
        String msg = "Hello!\n Created refresh token Successfully \n Thank You !";

        buildEmail(email, sub, msg);

        return "User's Refresh Token Created Successfully!";
    }

    public String sentEmailToHOD(String email) throws MessagingException {

        String sub = "User Register!!";
        String msg = "Hey! \n I hope this mail get's you well, you are register as an HOD in this data base and you can Access All the Operations in Related to HOD. \n Thank You !";

        buildEmail(email, sub, msg);

        return "User Created Successfully!";
    }

    public String sentEmailToTea(String email) throws MessagingException {

        String sub = "User Register!!";
        String msg = "Hey! \n I hope this mail get's you well, you are register as an Teacher in this data base and you can Access All the Operations in Related to Teacher. \n Thank You !";

        buildEmail(email, sub, msg);

        return "User Created Successfully!";
    }
    public String sendEmailChangePassword(String email, String password) throws MessagingException {
        String sub = "Change Password Successfully!";
        String msg = "Hello!\n Change User Password Successfully! \n Now Your Current Password is : "+password+"\n Thank You !";

        buildEmail(email, sub, msg);

        return "changed Password Successfully!";
    }

    public String sendUserLogoutEmail(String userEmail) throws MessagingException {
        String sub = "logout User Successfully!";
        String msg = "Hello!\n User is Logout..\n Thank You !";

        buildEmail(userEmail, sub, msg);

        return "User Logout Successfully!";
    }
}
