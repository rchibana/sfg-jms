package com.chibana.springframework.sfgjms.sender;

import com.chibana.springframework.sfgjms.config.JmsConfig;
import com.chibana.springframework.sfgjms.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by Rodrigo Chibana
 * Date: 27/03/2020
 **/
@Log4j2
@RequiredArgsConstructor
@Component
public class MessageSender {

    private final JmsTemplate jmsTemplate;

    @Scheduled(fixedRate = 2000)
    public void sendMessage() {
        log.info("Sending message...");

        HelloWorldMessage helloWorldMessage = HelloWorldMessage.builder()
                .id(UUID.randomUUID())
                .message("hello world from sender!")
                .build();

        jmsTemplate.convertAndSend(JmsConfig.MY_HELLO_WORLD_QUEUE, helloWorldMessage);

        log.info("Message sent!");

    }


}
