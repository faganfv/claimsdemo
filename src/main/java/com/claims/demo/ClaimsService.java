package com.claims.demo;

import com.claims.demo.controllers.ClaimCreatedRequest;
import com.claims.demo.domain.Claim;
import com.claims.demo.events.ClaimCreatedEvent;
import com.claims.demo.events.ClaimsEventPublisher;
import com.claims.demo.mappers.ClaimMapper;
import com.claims.demo.repositories.ClaimDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaimsService {

    private final ClaimsEventPublisher publisher;
    private final ClaimMapper claimMapper;
    private final ClaimDao claimDao;

    public ClaimsService(ClaimsEventPublisher publisher, ClaimMapper claimMapper, ClaimDao claimDao) {
        this.publisher = publisher;
        this.claimMapper = claimMapper;
        this.claimDao = claimDao;
    }

    public Boolean createClaim(ClaimCreatedRequest request) {
        if (request == null) return null;

        Claim claim = claimMapper.toClaim(request);

        claimDao.insertClaim(claim);

        ClaimCreatedEvent event = claimMapper.toEvent(claim, request);
        publisher.publishClaimCreated(event);

        return true;
    }

    public List<Claim> getClaims() { return claimDao.getAllClaims(); }
}
