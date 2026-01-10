package com.claims.demo.mappers;

import com.claims.demo.controllers.ClaimCreatedRequest;
import com.claims.demo.domain.Claim;
import com.claims.demo.events.ClaimCreatedEvent;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
public class ClaimMapper {

    private static final String CLAIM_CREATED_EVENT_TYPE = "ClaimCreated";
    private static final String CLAIM_CREATED_EVENT_VERSION = "v1";
    public static final String CLAIM_CREATED_EVENT_SCHEMA = "claims.events.ClaimCreated";

    public ClaimMapper() {}

    public Claim toClaim(ClaimCreatedRequest req) {
        if (req == null) return null;
        String claimId = "CLM-" + UUID.randomUUID().toString().substring(0, 8);

        return new Claim(
                claimId,
                req.policyId(),
                req.lossType(),
                req.dateOfLoss(),
                req.reportedAt(),
                req.state(),
                req.county(),
                req.zipCode(),
                req.description(),
                req.source().channel(),
                req.source().reportedBy(),
                req.actor().actorType(),
                req.actor().actorId(),
                req.correlation().requestId(),
                req.correlation().traceId()
        );
    }

    public ClaimCreatedEvent toEvent(Claim claim) {
        if (claim == null) return null;

        String eventId = "evt-" + UUID.randomUUID().toString().substring(0, 8);
        ClaimCreatedEvent.Metadata metadata = new ClaimCreatedEvent.Metadata(
                CLAIM_CREATED_EVENT_SCHEMA,
                Instant.now());

        return new ClaimCreatedEvent(
                eventId,
                CLAIM_CREATED_EVENT_TYPE,
                CLAIM_CREATED_EVENT_VERSION,
                claim,
                new ClaimCreatedEvent.Location(
                        claim.getState(),
                        claim.getCounty(),
                        claim.getZipCode()
                ),
                new ClaimCreatedEvent.Source(claim.getSourceChannel(), claim.getSourceReportedBy()),
                new ClaimCreatedEvent.Actor(claim.getActorType(), claim.getActorId()),
                new ClaimCreatedEvent.Correlation(claim.getCorrelationRequestId(), claim.getCorrelationTraceId()),
                metadata
        );
    }
}
