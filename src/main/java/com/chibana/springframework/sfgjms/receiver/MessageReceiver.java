package com.chibana.springframework.sfgjms.receiver;

import com.chibana.springframework.sfgjms.config.JmsConfig;
import com.chibana.springframework.sfgjms.model.HelloWorldMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * Created by Rodrigo Chibana
 * Date: 04/04/2020
 **/
@Log4j2
@Component
public class MessageReceiver {

    @JmsListener(destination = JmsConfig.MY_HELLO_WORLD_QUEUE)
    public void listener(@Payload HelloWorldMessage helloWorldMessage,
                         @Headers MessageHeaders headers,
                         Message message) {

        log.info("Hey, I got a message!");
        log.info(helloWorldMessage);

    }

}
