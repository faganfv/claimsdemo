package com.claims.demo.events;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ClaimEventSubscriber {
    @JmsListener(destination = "claim.created.topic", containerFactory = "topicListenerFactory")
    public void listen(String message) {
        System.out.println("Received message: " + message);
    }
}
