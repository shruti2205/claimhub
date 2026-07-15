package com.vega.claimhub.mapper;

import com.vega.claimhub.dto.ClaimRequest;
import com.vega.claimhub.dto.ClaimResponse;
import com.vega.claimhub.entity.Claim;
import org.springframework.stereotype.Component;

@Component
public class ClaimMapper {
    public Claim toEntity(ClaimRequest request) {
        Claim claim = new Claim();
        updateEntity(request, claim);
        return claim;
    }
    public void updateEntity(ClaimRequest r, Claim c) {
        c.setClaimNumber(r.claimNumber()); c.setDealerCode(r.dealerCode()); c.setVehicleVin(r.vehicleVin());
        c.setPartNumber(r.partNumber()); c.setFailureDescription(r.failureDescription());
        c.setClaimAmount(r.claimAmount()); c.setStatus(r.status());
    }
    public ClaimResponse toResponse(Claim c) {
        return new ClaimResponse(c.getId(), c.getClaimNumber(), c.getDealerCode(), c.getVehicleVin(),
                c.getPartNumber(), c.getFailureDescription(), c.getClaimAmount(), c.getStatus(), c.getCreatedAt(), c.getUpdatedAt());
    }
}
