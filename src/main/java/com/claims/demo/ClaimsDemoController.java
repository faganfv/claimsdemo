package com.claims.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ClaimsDemoController {

    private final List<Claim> claims = new ArrayList<>();

    @GetMapping("/")
    public String Welcome() {
        return "Claims Demo is up an running!";
    }

    @GetMapping("/getClaims")
    public List<Claim> GetClaims() {
        return claims;
    }

    @PostMapping("/createClaim")
    public String CreateClaim(@RequestBody Claim claim) {
        claims.add(claim);
        return "Claim created successfully!";
    }
}
