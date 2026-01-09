package com.claims.demo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class Claim {

    @JsonProperty("claimId")
    private String claimId;

    @JsonProperty("policyId")
    private String policyId;

    @JsonProperty("lossType")
    private String lossType;

    @JsonProperty("timestamp")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Instant timestamp;

    @JsonProperty("description")
    private String description;

    public Claim(String claimId, String policyId, String lossType, Instant timestamp, String description) {
        this.claimId = claimId;
        this.policyId = policyId;
        this.lossType = lossType;
        this.timestamp = timestamp;
        this.description = description;
    }

    public String getClaimId() {
        return claimId;
    }

    public void setClaimId(String claimId) {
        this.claimId = claimId;
    }

    public String getPolicyId() {
        return policyId;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }

    public String getLossType() {
        return lossType;
    }

    public void setLossType(String lossType) {
        this.lossType = lossType;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
