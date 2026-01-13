package com.claims.demo;

import com.claims.demo.controllers.ClaimCreatedRequest;
import com.claims.demo.domain.Claim;
import com.claims.demo.events.ClaimCreatedEvent;
import com.claims.demo.events.ClaimsEventPublisher;
import com.claims.demo.mappers.ClaimMapper;
import com.claims.demo.repositories.ClaimDao;
import com.claims.demo.s3.S3Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Service
public class ClaimsService {

    private static final Logger log = LoggerFactory.getLogger(ClaimsService.class);
    private final ClaimsEventPublisher publisher;
    private final ClaimMapper claimMapper;
    private final ClaimDao claimDao;
    private final S3Service s3Service;

    public ClaimsService(ClaimsEventPublisher publisher, ClaimMapper claimMapper, ClaimDao claimDao, S3Service s3Service) {
        this.publisher = publisher;
        this.claimMapper = claimMapper;
        this.claimDao = claimDao;
        this.s3Service = s3Service;
    }

    public Mono<Boolean> createClaim(ClaimCreatedRequest request, FilePart image) {

        if (request == null) { return Mono.just(false); }

        Claim claim = claimMapper.toClaim(request);
        ClaimCreatedEvent event = claimMapper.toEvent(claim, request);

        return saveClaim(claim).flatMap(savedClaim ->
                publishEvent(event, savedClaim).then(
                uploadImageAsync(savedClaim, image).thenReturn(savedClaim)))
                .thenReturn(true);
    }

    public List<Claim> getClaims() { return claimDao.getAllClaims(); }

    private Mono<Claim> saveClaim(Claim claim) {
        return Mono.fromCallable(() -> {
                    claimDao.insertClaim(claim);
                    return claim;
                })
                .subscribeOn(Schedulers.boundedElastic());
    }

    private Mono<Claim> publishEvent(ClaimCreatedEvent event, Claim claim) {
        return Mono.fromRunnable(() -> publisher.publishClaimCreated(event))
                .subscribeOn(Schedulers.boundedElastic())
                .thenReturn(claim);
    }

    private Mono<Void> uploadImageAsync(Claim claim, FilePart image) {
        if (image == null) {
            return Mono.empty();
        }

        String s3Key = "claimphotos/" + claim.getId();

        return s3Service.uploadFile(s3Key, image)
                .doOnError(err -> log.error("S3 upload failed for claim {}", claim.getId(), err));
    }
}
