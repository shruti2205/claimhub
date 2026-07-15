package com.vega.claimhub.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "claims", uniqueConstraints = @UniqueConstraint(name = "uk_claims_claim_number", columnNames = "claim_number"))
public class Claim {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "claim_number", nullable = false, length = 30)
    private String claimNumber;
    @Column(name = "dealer_code", nullable = false, length = 20)
    private String dealerCode;
    @Column(name = "vehicle_vin", nullable = false, length = 17)
    private String vehicleVin;
    @Column(name = "part_number", nullable = false, length = 50)
    private String partNumber;
    @Column(name = "failure_description", nullable = false, length = 2000)
    private String failureDescription;
    @Column(name = "claim_amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal claimAmount;
    @Enumerated(EnumType.STRING) @Column(nullable = false, length = 20)
    private ClaimStatus status;
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @PrePersist void onCreate() { createdAt = Instant.now(); updatedAt = createdAt; }
    @PreUpdate void onUpdate() { updatedAt = Instant.now(); }

    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getClaimNumber() { return claimNumber; } public void setClaimNumber(String value) { claimNumber = value; }
    public String getDealerCode() { return dealerCode; } public void setDealerCode(String value) { dealerCode = value; }
    public String getVehicleVin() { return vehicleVin; } public void setVehicleVin(String value) { vehicleVin = value; }
    public String getPartNumber() { return partNumber; } public void setPartNumber(String value) { partNumber = value; }
    public String getFailureDescription() { return failureDescription; } public void setFailureDescription(String value) { failureDescription = value; }
    public BigDecimal getClaimAmount() { return claimAmount; } public void setClaimAmount(BigDecimal value) { claimAmount = value; }
    public ClaimStatus getStatus() { return status; } public void setStatus(ClaimStatus value) { status = value; }
    public Instant getCreatedAt() { return createdAt; } public void setCreatedAt(Instant value) { createdAt = value; }
    public Instant getUpdatedAt() { return updatedAt; } public void setUpdatedAt(Instant value) { updatedAt = value; }
}
