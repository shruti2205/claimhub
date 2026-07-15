package com.vega.claimhub.dto;

import com.vega.claimhub.entity.ClaimStatus;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record ClaimRequest(
        @NotBlank @Size(max = 30) @Pattern(regexp = "^[A-Za-z0-9-]+$", message = "must contain only letters, numbers, and hyphens") String claimNumber,
        @NotBlank @Size(max = 20) @Pattern(regexp = "^[A-Za-z0-9-]+$", message = "must contain only letters, numbers, and hyphens") String dealerCode,
        @NotBlank @Pattern(regexp = "^[A-HJ-NPR-Z0-9]{17}$", message = "must be a valid 17-character VIN") String vehicleVin,
        @NotBlank @Size(max = 50) String partNumber,
        @NotBlank @Size(max = 2000) String failureDescription,
        @NotNull @DecimalMin(value = "0.01") @Digits(integer = 13, fraction = 2) BigDecimal claimAmount,
        @NotNull ClaimStatus status) { }
