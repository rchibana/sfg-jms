package com.chibana.springframework.sfgjms.sender;

import com.chibana.springframework.sfgjms.config.JmsConfig;
import com.chibana.springframework.sfgjms.model.HelloWorldMessage;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by Rodrigo Chibana
 * Date: 27/03/2020
 **/
@RequiredArgsConstructor
@Component
public class MessageSender {

    private final JmsTemplate jmsTemplate;

    @Scheduled(fixedRate = 2000)
    public void sendMessage() {
        System.out.println("Sending message...");

        HelloWorldMessage helloWorldMessage = HelloWorldMessage.builder()
                .id(UUID.randomUUID())
                .message("hello world from sender!")
                .build();

        jmsTemplate.convertAndSend(JmsConfig.MY_HELLO_WORLD_QUEUE, helloWorldMessage);

        System.out.println("Message sent!");

    }


}
