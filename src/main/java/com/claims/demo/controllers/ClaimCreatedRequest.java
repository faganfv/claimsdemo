package com.claims.demo.controllers;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record ClaimCreatedRequest(

    @JsonProperty("policyId") String policyId,
    @JsonProperty("lossType") String lossType,
    @JsonProperty("dateOfLoss") @JsonFormat(shape = JsonFormat.Shape.STRING) Instant dateOfLoss,
    @JsonProperty("reportedAt") @JsonFormat(shape = JsonFormat.Shape.STRING) Instant reportedAt,
    @JsonProperty("state") String state,
    @JsonProperty("county") String county,
    @JsonProperty("zipCode") String zipCode,
    @JsonProperty("description") String description,
    @JsonProperty("source") Source source,
    @JsonProperty("actor") Actor actor,
    @JsonProperty("correlation") Correlation correlation,
    @JsonProperty("metadata") Metadata metadata

) {
    public record Source(
            @JsonProperty("channel") String channel,
            @JsonProperty("reportedBy") String reportedBy) {}
    public record Actor(
            @JsonProperty("actorType") String actorType,
            @JsonProperty("actorId") String actorId) {}
    public record Correlation(
            @JsonProperty("requestId") String requestId,
            @JsonProperty("traceId") String traceId) {}
    public record Metadata(
            @JsonProperty("schema") String schema,
            @JsonProperty("createdAt") @JsonFormat(shape = JsonFormat.Shape.STRING) Instant createdAt) {}
}
