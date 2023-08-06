package com.clinica.emailservice.service.service;

import com.clinica.emailservice.publisher.EmailPublisher;
import com.clinica.emailservice.service.dto.EmailDto;
import com.clinica.emailservice.service.error.ESException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class EmailService {

    private final Environment env;
    private final JavaMailSender mailSender;

    @Autowired
    public EmailService(Environment env, JavaMailSender mailSender) {
        this.env = env;
        this.mailSender = mailSender;
    }

    public void enqueueEmail(EmailDto emailDTO) throws ESException {
        verifyEmail(emailDTO);
        EmailPublisher.send(emailDTO);
        log.info("Email enviado a la cola.");
    }

    public void sendEmail(EmailDto emailDTO) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom(env.getProperty("email.props.gmail.user"));
        email.setTo(emailDTO.getToUser());
        email.setSubject(emailDTO.getSubject());
        email.setText(emailDTO.getMessage());
        mailSender.send(email);
        log.info("Correo enviado.");
    }

    private void verifyEmail(EmailDto email) throws ESException {
        if (email.getToUser() == null | email.getSubject().isBlank() | email.getMessage().isBlank()) {
            throw new ESException("Datos inválidos");
        }
    }

}
