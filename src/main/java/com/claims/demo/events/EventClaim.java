package com.claims.demo.events;

import java.time.Instant;

public record EventClaim(
        String claimId,
        String policyId,
        String lossType,
        Instant dateOfLoss,
        Instant reportedAt,
        String state
) {}

