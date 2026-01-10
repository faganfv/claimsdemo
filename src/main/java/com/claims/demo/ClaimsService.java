package com.claims.demo;

import com.claims.demo.controllers.ClaimCreatedRequest;
import com.claims.demo.domain.Claim;
import com.claims.demo.events.ClaimCreatedEvent;
import com.claims.demo.events.ClaimsEventPublisher;
import com.claims.demo.mappers.ClaimMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClaimsService {

    private final List<Claim> claims = new ArrayList<>();

    private final ClaimsEventPublisher publisher;
    private final ClaimMapper claimMapper;

    public ClaimsService(ClaimsEventPublisher publisher, ClaimMapper claimMapper) {
        this.publisher = publisher;
        this.claimMapper = claimMapper;
    }

    public Boolean createClaim(ClaimCreatedRequest request) {
        if (request == null) return null;

        Claim claim = claimMapper.toClaim(request);
        claims.add(claim);

        // TODO: persist claim to DB here when ready

        ClaimCreatedEvent event = claimMapper.toEvent(claim);
        publisher.publishClaimCreated(event);

        return true;
    }

    public List<Claim> getClaims() {
        // TODO: get claims from DB when ready
        return claims;
    }
}
