package com.vega.claimhub.service;

import com.vega.claimhub.dto.ClaimRequest;
import com.vega.claimhub.dto.ClaimResponse;
import com.vega.claimhub.entity.Claim;
import com.vega.claimhub.exception.ClaimNotFoundException;
import com.vega.claimhub.exception.DuplicateClaimNumberException;
import com.vega.claimhub.mapper.ClaimMapper;
import com.vega.claimhub.repository.ClaimRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ClaimService {
    private static final Logger log = LoggerFactory.getLogger(ClaimService.class);
    private final ClaimRepository repository;
    private final ClaimMapper mapper;

    public ClaimService(ClaimRepository repository, ClaimMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public ClaimResponse create(ClaimRequest request) {
        log.info("Creating claim claimNumber={}", request.claimNumber());
        if (repository.existsByClaimNumber(request.claimNumber())) throw new DuplicateClaimNumberException(request.claimNumber());
        return mapper.toResponse(repository.save(mapper.toEntity(request)));
    }

    public ClaimResponse getById(Long id) {
        log.debug("Fetching claim id={}", id);
        return mapper.toResponse(findClaim(id));
    }

    public List<ClaimResponse> getAll() {
        log.debug("Fetching all claims");
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Transactional
    public ClaimResponse update(Long id, ClaimRequest request) {
        log.info("Updating claim id={}", id);
        Claim claim = findClaim(id);
        if (!claim.getClaimNumber().equals(request.claimNumber()) && repository.existsByClaimNumber(request.claimNumber())) {
            throw new DuplicateClaimNumberException(request.claimNumber());
        }
        mapper.updateEntity(request, claim);
        return mapper.toResponse(repository.save(claim));
    }

    @Transactional
    public void delete(Long id) {
        log.info("Deleting claim id={}", id);
        Claim claim = findClaim(id);
        repository.delete(claim);
    }

    private Claim findClaim(Long id) {
        return repository.findById(id).orElseThrow(() -> new ClaimNotFoundException(id));
    }
}

