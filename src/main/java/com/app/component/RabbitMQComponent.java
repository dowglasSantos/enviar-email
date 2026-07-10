package com.app.component;

import com.app.dto.EmailMassegeDTO;
import com.app.service.EmailService;
import org.springframework.stereotype.Component;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@Component
public class RabbitMQComponent {
    private EmailService emailService;

    @RabbitListener(queues = "${rabbitmq.name.queue}")
    public void consumerEmail(EmailMassegeDTO email) {
        emailService.enviarEmail(
            email.to(),
            email.subject(),
            email.body()
        );

        System.out.println("Enviado com sucesso");
        System.out.println("=============="+ email.toString() +"==============");
    }
}
