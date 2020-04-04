package com.chibana.springframework.sfgjms.receiver;

import com.chibana.springframework.sfgjms.config.JmsConfig;
import com.chibana.springframework.sfgjms.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import java.util.UUID;

/**
 * Created by Rodrigo Chibana
 * Date: 04/04/2020
 **/
@Log4j2
@RequiredArgsConstructor
@Component
public class MessageReceiver {

    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.MY_HELLO_WORLD_QUEUE)
    public void listener(@Payload HelloWorldMessage helloWorldMessage,
                         @Headers MessageHeaders headers,
                         Message message) {

        log.info("Hey, I got a message!");
        log.info(helloWorldMessage);

    }

    @JmsListener(destination = JmsConfig.MY_SEND_RCV_QUEUE)
    public void listenerAndSender(@Payload HelloWorldMessage helloWorldMessage,
                                  @Headers MessageHeaders headers,
                                  Message message) throws JMSException {

        HelloWorldMessage payloadMsg = HelloWorldMessage
                .builder()
                .id(UUID.randomUUID())
                .message("World!!")
                .build();

        jmsTemplate.convertAndSend(message.getJMSReplyTo(), payloadMsg);

    }

}
