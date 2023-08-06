package com.clinica.emailservice.service.service;

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

    private Environment env;
    private JavaMailSender mailSender;

    @Autowired
    private void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Autowired
    private void setEnv(Environment env) {
        this.env = env;
    }

    public void sendEmail(String[] toUser, String subject, String message) throws ESException {
        verifyEmail(toUser, subject, message);

        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom(env.getProperty("email.props.gmail.user"));
        email.setTo(toUser);
        email.setSubject(subject);
        email.setText(message);
        mailSender.send(email);
        log.info("Correo enviado.");
    }

    private void verifyEmail(String[] toUser, String subject, String message) throws ESException {
        if (toUser == null | subject.isBlank() | message.isBlank()) {
            throw new ESException("Datos inválidos");
        }
    }

}
