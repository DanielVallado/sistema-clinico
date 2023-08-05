package com.clinica.emailservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendConfirmationEmail(String email, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Confirmación de Cita");
        message.setText("¡Gracias por agendar tu cita! Haz clic en el siguiente enlace para confirmar:\n"
                + "http://tu-microservicio.com/confirmar-cita?token=" + token);
        mailSender.send(message);
    }
}
