package com.apiPetshop.apiPetshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    

    public String enviarEmail(String destinatario, String titulo, String msg) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom("amicaopetong@gmail.com");
            simpleMailMessage.setTo(destinatario);
            simpleMailMessage.setSubject(titulo);
            simpleMailMessage.setText(msg);
            javaMailSender.send(simpleMailMessage);
            return "email enviado";
        } catch (Exception ex) {
            return "erro no envio";

        }
    }

}
