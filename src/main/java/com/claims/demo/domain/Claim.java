package com.claims.demo.domain;

import java.time.Instant;

public class Claim {

    private String id;
    private String policyNumber;
    private String lossType;
    private Instant dateOfLoss;
    private Instant reportedAtDate;
    private String state;
    private String county;
    private String zipCode;
    private String description;
    private String sourceChannel;
    private String sourceReportedBy;
    private String actorType;
    private String actorId;
    private String correlationRequestId;
    private String correlationTraceId;
    private Instant metadataCreatedAt;

    public Claim(String claimId, String policyNumber, String lossType, Instant dateOfLoss, Instant reportedAtDate, String state, String county, String zipCode, String description,
                 String sourceChannel, String sourceReportedBy, String actorType, String actorId,
                 String correlationRequestId, String correlationTraceId, Instant metadataCreatedAt) {
        this.id = claimId;
        this.policyNumber = policyNumber;
        this.lossType = lossType;
        this.dateOfLoss = dateOfLoss;
        this.reportedAtDate = reportedAtDate;
        this.state = state;
        this.county = county;
        this.zipCode = zipCode;
        this.description = description;
        this.sourceChannel = sourceChannel;
        this.sourceReportedBy = sourceReportedBy;
        this.actorType = actorType;
        this.actorId = actorId;
        this.correlationRequestId = correlationRequestId;
        this.correlationTraceId = correlationTraceId;
        this.metadataCreatedAt = metadataCreatedAt;
    }

    public Claim() { }

    public String getId() {
        return id;
    }

    public void setId(String claimId) {
        this.id = claimId;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getLossType() {
        return lossType;
    }

    public void setLossType(String lossType) {
        this.lossType = lossType;
    }

    public Instant getDateOfLoss() {
        return dateOfLoss;
    }

    public void setDateOfLoss(Instant dateOfLoss) {
        this.dateOfLoss = dateOfLoss;
    }

    public Instant getReportedAtDate() {
        return reportedAtDate;
    }

    public void setReportedAtDate(Instant reportedAtDate) {
        this.reportedAtDate = reportedAtDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSourceChannel() {
        return sourceChannel;
    }

    public void setSourceChannel(String sourceChannel) {
        this.sourceChannel = sourceChannel;
    }

    public String getSourceReportedBy() {
        return sourceReportedBy;
    }

    public void setSourceReportedBy(String sourceReportedBy) {
        this.sourceReportedBy = sourceReportedBy;
    }

    public String getActorType() {
        return actorType;
    }

    public void setActorType(String actorType) {
        this.actorType = actorType;
    }

    public String getActorId() {
        return actorId;
    }

    public void setActorId(String actorId) {
        this.actorId = actorId;
    }

    public String getCorrelationRequestId() {
        return correlationRequestId;
    }

    public void setCorrelationRequestId(String correlationRequestId) {
        this.correlationRequestId = correlationRequestId;
    }

    public String getCorrelationTraceId() {
        return correlationTraceId;
    }

    public void setCorrelationTraceId(String correlationTraceId) {
        this.correlationTraceId = correlationTraceId;
    }

    public Instant getMetadataCreatedAt() {
        return metadataCreatedAt;
    }

    public void setMetadataCreatedAt(Instant metadataCreatedAt) {
        this.metadataCreatedAt = metadataCreatedAt;
    }
}
