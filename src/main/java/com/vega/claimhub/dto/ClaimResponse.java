package com.vega.claimhub.dto;

import com.vega.claimhub.entity.ClaimStatus;
import java.math.BigDecimal;
import java.time.Instant;

public record ClaimResponse(Long id, String claimNumber, String dealerCode, String vehicleVin,
                            String partNumber, String failureDescription, BigDecimal claimAmount,
                            ClaimStatus status, Instant createdAt, Instant updatedAt) { }
