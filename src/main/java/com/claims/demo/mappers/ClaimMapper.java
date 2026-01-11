package com.claims.demo.mappers;

import com.claims.demo.controllers.ClaimCreatedRequest;
import com.claims.demo.domain.Claim;
import com.claims.demo.events.ClaimCreatedEvent;
import com.claims.demo.events.EventClaim;
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

        String sourceChannel = req.source() != null ? req.source().channel() : null;
        String sourceReportedBy = req.source() != null ? req.source().reportedBy() : null;
        String actorType = req.actor() != null ? req.actor().actorType() : null;
        String actorId = req.actor() != null ? req.actor().actorId() : null;
        String correlationRequestId = req.correlation() != null ? req.correlation().requestId() : null;
        String correlationTraceId = req.correlation() != null ? req.correlation().traceId() : null;
        Instant metadataCreatedAt = req.metadata() != null ? req.metadata().createdAt() : null;

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
                sourceChannel,
                sourceReportedBy,
                actorType,
                actorId,
                correlationRequestId,
                correlationTraceId,
                metadataCreatedAt
        );
    }

    public ClaimCreatedEvent toEvent(Claim claim, ClaimCreatedRequest req) {
        if (claim == null) return null;

        EventClaim eventClaim = new EventClaim(
                claim.getId(),
                claim.getPolicyNumber(),
                claim.getLossType(),
                claim.getDateOfLoss(),
                claim.getReportedAtDate(),
                claim.getState()
        );

        return new ClaimCreatedEvent(
                "evt-" + UUID.randomUUID().toString().substring(0, 8),
                CLAIM_CREATED_EVENT_TYPE,
                CLAIM_CREATED_EVENT_VERSION,
                eventClaim,
                new ClaimCreatedEvent.Location(
                        claim.getState(),
                        claim.getCounty(),
                        claim.getZipCode()
                ),
                new ClaimCreatedEvent.Source(claim.getSourceChannel(), claim.getSourceReportedBy()),
                new ClaimCreatedEvent.Actor(claim.getActorType(), claim.getActorId()),
                new ClaimCreatedEvent.Correlation(req.correlation().requestId(), req.correlation().traceId()),
                new ClaimCreatedEvent.Metadata(CLAIM_CREATED_EVENT_SCHEMA, Instant.now())
        );
    }
}
