package com.claims.demo.events;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class ClaimsEventPublisher {
    public static final String CLAIM_CREATED_TOPIC = "claim.created.topic";
    private final JmsTemplate jmsTemplate;

    public ClaimsEventPublisher(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void publishClaimCreated(ClaimCreatedEvent event) {
        jmsTemplate.convertAndSend(CLAIM_CREATED_TOPIC, event);
    }
}
