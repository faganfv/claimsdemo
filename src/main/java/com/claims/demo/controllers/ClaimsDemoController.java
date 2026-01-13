package com.claims.demo.controllers;

import com.claims.demo.domain.Claim;
import com.claims.demo.ClaimsService;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class ClaimsDemoController {

    private final ClaimsService claimsService;

    public ClaimsDemoController(ClaimsService claimsService) {
        this.claimsService = claimsService;
    }

    @GetMapping("/")
    public String welcome() { return "Claims Demo is up an running!"; }

    @GetMapping("/getClaims")
    public List<Claim> getClaims() { return claimsService.getClaims(); }

    @PostMapping(
        value = "/createClaim",
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<String> createClaim(
            @RequestPart("requestJson") ClaimCreatedRequest claimRequest,
            @RequestPart("image") FilePart image) {
        return claimsService.createClaim(claimRequest, image)
                .map(created -> created
                        ? "Claim created successfully!"
                        : "Failed to create claim.");
    }
}
