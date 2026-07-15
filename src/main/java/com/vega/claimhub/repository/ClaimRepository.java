package com.vega.claimhub.repository;

import com.vega.claimhub.entity.Claim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimRepository extends JpaRepository<Claim, Long> {
    boolean existsByClaimNumber(String claimNumber);
}
