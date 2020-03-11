package com.aleksieienko.questionnaire.portal.web;

import com.aleksieienko.questionnaire.portal.db.entity.FieldName;
import com.aleksieienko.questionnaire.portal.db.entity.Questionnaire;
import com.aleksieienko.questionnaire.portal.db.entity.Response;
import com.aleksieienko.questionnaire.portal.db.entity.User;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BeanUtil {
    public static void setCookie(User user, int expiry){
        HttpServletRequest request = FacesContextFactory.getRequest();
        Cookie cookie = null;
        Cookie[] cookies = request.getCookies();
        for(Cookie c : cookies){
            if(c.getName()!=null && c.getName().equals("email")){
                cookie = c;
                break;
            }
        }
        if(cookie == null){
            cookie = new Cookie("email",user.getEmail());
        }else{
            if(cookie.getName().equals("email")){
                cookie.setValue(user.getEmail());
            }
        }
        cookie.setMaxAge(expiry);

        HttpServletResponse response = FacesContextFactory.getResponse();
        response.addCookie(cookie);
    }

    public static Cookie getCookie(String name) {

        HttpServletRequest request = FacesContextFactory.getRequest();
        Cookie cookie = null;

        Cookie[] userCookies = request.getCookies();
        if(userCookies != null) {
            for (Cookie c : userCookies) {
                if (c.getName().equals(name)) {
                    cookie = c;
                    return cookie;
                }
            }
        }
        return null;
    }

    public static void sendMail(String email,String token){
        try (FileInputStream fis1 = new FileInputStream("src/main/resources/mail.properties");
                FileInputStream fis2 = new FileInputStream("src/main/resources/email_sender.properties")){
            Properties properties = new Properties();
            properties.load(fis1);
            final Properties senderProperties = new Properties();
            senderProperties.load(fis2);
            Session session = Session.getInstance(properties, new Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(senderProperties.getProperty("email"), senderProperties.getProperty("password"));
                        }
                    });
        Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(senderProperties.getProperty("email")));

        message.setRecipients(
                Message.RecipientType.TO, InternetAddress.parse(email));
        message.setSubject("Email confirmation.");

        String msg = "http://"+FacesContextFactory.getRequest().getServerName()+ ":"+ FacesContextFactory.getRequest().getServerPort() + FacesContextFactory.getRequest().getContextPath()+"/confirm/verification/token/"+token;

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msg, "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);

        Transport.send(message);
        } catch (MessagingException|IOException e) {
        e.printStackTrace();
        }
    }

    public static void fillResponse(Response response, List<FieldName> fieldNames){
        List<Questionnaire> temp = new ArrayList<>(fieldNames.size());
        int j=0;
        for (int i = 0; i < fieldNames.size(); i++) {
            Questionnaire tempQ;
            if(j < response.getQuestionnaires().size() &&
                    response.getQuestionnaires().get(j).getFieldName().getId().equals(fieldNames.get(i).getId())){
                tempQ = response.getQuestionnaires().get(j);
                j++;
            }else {
                tempQ = new Questionnaire();
                tempQ.setFieldName(fieldNames.get(i));
                tempQ.setValue("N/A");
            }
            temp.add(i,tempQ);
        }
        response.setQuestionnaires(temp);
    }
}
