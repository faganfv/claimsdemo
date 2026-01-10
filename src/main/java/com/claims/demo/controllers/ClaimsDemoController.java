package com.claims.demo.controllers;

import com.claims.demo.domain.Claim;
import com.claims.demo.ClaimsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/createClaim")
    public String createClaim(@RequestBody ClaimCreatedRequest claimRequest) {
        Boolean created = claimsService.createClaim(claimRequest);
        return created ? "Claim created successfully!" : "Failed to create claim.";
    }
}
