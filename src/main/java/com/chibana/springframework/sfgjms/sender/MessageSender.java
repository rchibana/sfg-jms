package com.chibana.springframework.sfgjms.sender;

import com.chibana.springframework.sfgjms.config.JmsConfig;
import com.chibana.springframework.sfgjms.model.HelloWorldMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
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
    private final ObjectMapper objectMapper;

    @Scheduled(fixedRate = 2000)
    public void sendMessage() throws JMSException {
        defaultSendMessage();
        sendAndReceiveMessage();

    }

    private void defaultSendMessage() {
        log.info("Sending default message...");

        HelloWorldMessage helloWorldMessage = HelloWorldMessage.builder()
                .id(UUID.randomUUID())
                .message("hello world from sender!")
                .build();

        jmsTemplate.convertAndSend(JmsConfig.MY_HELLO_WORLD_QUEUE, helloWorldMessage);

        log.info("Default message sent!");
    }

    private void sendAndReceiveMessage() throws JMSException {
        log.info("Sending message and receiving...");

        HelloWorldMessage helloWorldMessage = HelloWorldMessage.builder()
                .id(UUID.randomUUID())
                .message("hello world from sender version 2!")
                .build();

        final Message message = jmsTemplate.sendAndReceive(JmsConfig.MY_SEND_RCV_QUEUE, session -> {

            try {
                final Message helloMessage = session.createTextMessage(
                        objectMapper.writeValueAsString(helloWorldMessage));
                helloMessage.setStringProperty("_type", helloWorldMessage.getClass()
                        .getCanonicalName());

                return helloMessage;

            } catch (JsonProcessingException e) {
                throw new JMSException("boom");
            }
        });

        if(message != null) {
            log.info(String.format("Message back: %s", message.getBody(String.class)));
        } else {
            log.warn(String.format("No back message received. Message sent: %s", helloWorldMessage.getId()));
        }

        log.info("Message sent and received!");
    }


}
