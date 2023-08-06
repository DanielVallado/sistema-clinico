package com.clinica.emailservice.consumer;

import com.clinica.emailservice.service.dto.EmailDto;
import com.clinica.emailservice.service.service.EmailService;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class EmailConsumer {

    private final EmailService emailService;

    @Autowired
    public EmailConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "emailQueue")
    public void receiveMessage(@Payload EmailDto email) {
        log.info("Email agregado a la cola.");
        emailService.sendEmail(email);
    }

}
