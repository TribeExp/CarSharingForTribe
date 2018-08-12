package com.basakdm.excartest.service.service_impl;

import com.basakdm.excartest.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public boolean sendRegistrationMessage(String email, String password) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Car Sharing Team");
        message.setText("Hello! Thanks for registration \n Your login: " + email +
                "\n Your password: "+ password);
        try {
            javaMailSender.send(message);
            return true;
        } catch (MailException e){
            e.printStackTrace();
            return false;
        }
    }
}
