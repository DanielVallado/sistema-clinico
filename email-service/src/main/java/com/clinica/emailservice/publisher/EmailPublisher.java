package com.clinica.emailservice.publisher;

import com.clinica.emailservice.service.dto.EmailDto;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@EnableRabbit
public class EmailPublisher {

    private static RabbitTemplate rabbitTemplate;
    private static Queue queue;

    public EmailPublisher(RabbitTemplate rabbitTemplate, Queue queue) {
        EmailPublisher.rabbitTemplate = rabbitTemplate;
        EmailPublisher.queue = queue;
    }

    public static void send(EmailDto message) {
        rabbitTemplate.convertAndSend(queue.getName(), message);
    }

}
