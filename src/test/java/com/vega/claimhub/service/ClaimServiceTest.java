package com.vega.claimhub.service;

import com.vega.claimhub.dto.ClaimRequest;
import com.vega.claimhub.dto.ClaimResponse;
import com.vega.claimhub.entity.Claim;
import com.vega.claimhub.entity.ClaimStatus;
import com.vega.claimhub.exception.ClaimNotFoundException;
import com.vega.claimhub.mapper.ClaimMapper;
import com.vega.claimhub.repository.ClaimRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClaimServiceTest {
    @Mock private ClaimRepository repository;
    private ClaimService service;
    private Claim claim;

    @BeforeEach
    void setUp() {
        service = new ClaimService(repository, new ClaimMapper());
        claim = claim(1L, "CLM-1001");
    }

    @Test
    void createsClaim() {
        when(repository.existsByClaimNumber("CLM-1001")).thenReturn(false);
        when(repository.save(any(Claim.class))).thenAnswer(invocation -> {
            Claim saved = invocation.getArgument(0); saved.setId(1L); saved.setCreatedAt(Instant.now()); saved.setUpdatedAt(Instant.now()); return saved;
        });

        ClaimResponse result = service.create(request("CLM-1001"));

        assertThat(result.id()).isEqualTo(1L);
        assertThat(result.claimNumber()).isEqualTo("CLM-1001");
        verify(repository).save(any(Claim.class));
    }

    @Test
    void getsClaimById() {
        when(repository.findById(1L)).thenReturn(Optional.of(claim));
        assertThat(service.getById(1L).dealerCode()).isEqualTo("DLR-01");
    }

    @Test
    void updatesClaim() {
        when(repository.findById(1L)).thenReturn(Optional.of(claim));
        when(repository.save(claim)).thenReturn(claim);

        ClaimResponse result = service.update(1L, request("CLM-1001"));

        assertThat(result.claimAmount()).isEqualByComparingTo("125.50");
        verify(repository).save(claim);
    }

    @Test
    void deletesClaim() {
        when(repository.findById(1L)).thenReturn(Optional.of(claim));
        service.delete(1L);
        verify(repository).delete(claim);
    }

    @Test
    void throwsWhenClaimDoesNotExist() {
        when(repository.findById(999L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.getById(999L)).isInstanceOf(ClaimNotFoundException.class)
                .hasMessageContaining("999");
    }

    private ClaimRequest request(String number) {
        return new ClaimRequest(number, "DLR-01", "1HGCM82633A004352", "PART-99", "Component failed under load", new BigDecimal("125.50"), ClaimStatus.DRAFT);
    }
    private Claim claim(Long id, String number) {
        Claim value = new Claim(); value.setId(id); value.setClaimNumber(number); value.setDealerCode("DLR-01");
        value.setVehicleVin("1HGCM82633A004352"); value.setPartNumber("PART-99"); value.setFailureDescription("Component failed under load");
        value.setClaimAmount(new BigDecimal("100.00")); value.setStatus(ClaimStatus.DRAFT); value.setCreatedAt(Instant.now()); value.setUpdatedAt(Instant.now());
        return value;
    }
}
