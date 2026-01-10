package com.claims.demo.events;

import com.claims.demo.domain.Claim;

import java.time.Instant;

public record ClaimCreatedEvent(
        String eventId,
        String eventType,
        String eventVersion,
        Claim claim,
        Location location,
        Source source,
        Actor actor,
        Correlation correlation,
        Metadata metadata
) {

    public record Location(String state, String county, String zip) {}
    public record Source(String channel, String reportedBy) {}
    public record Actor(String actorType, String actorId) {}
    public record Correlation(String requestId, String traceId) {}
    public record Metadata(String schema, Instant createdAt) {}
}
