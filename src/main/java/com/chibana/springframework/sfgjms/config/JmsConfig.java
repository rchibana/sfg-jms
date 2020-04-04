package com.chibana.springframework.sfgjms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

/**
 * Created by Rodrigo Chibana
 * Date: 27/03/2020
 **/
@Configuration
public class JmsConfig {

    public static final String MY_HELLO_WORLD_QUEUE = "my-hello-world";
    public static final String MY_SEND_RCV_QUEUE = "my-hello-world-send-receive";

    @Bean
    public MessageConverter messageConverter() {

        final MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");

        return converter;
    }

}
