package com.chibana.springframework.sfgjms.sender;

import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Rodrigo Chibana
 * Date: 27/03/2020
 **/
@RequiredArgsConstructor
@Component
public class MessageSender {

//    private JmsTemplate jmsTemplate;

    @Scheduled(fixedRate = 2000)
    public void sendMessage() {
        System.out.println("hello world test!");
    }


}
