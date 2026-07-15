package com.vega.claimhub.controller;

import com.vega.claimhub.dto.ClaimRequest;
import com.vega.claimhub.dto.ClaimResponse;
import com.vega.claimhub.service.ClaimService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/claims")
@Tag(name = "Claims", description = "Claim lifecycle management")
public class ClaimController {
    private final ClaimService service;
    public ClaimController(ClaimService service) { this.service = service; }

    @PostMapping
    @Operation(summary = "Create a claim")
    public ResponseEntity<ClaimResponse> create(@Valid @RequestBody ClaimRequest request) {
        ClaimResponse response = service.create(request);
        return ResponseEntity.created(URI.create("/claims/" + response.id())).body(response);
    }
    @GetMapping("/{id}") @Operation(summary = "Get a claim by ID")
    public ClaimResponse getById(@PathVariable Long id) { return service.getById(id); }
    @GetMapping @Operation(summary = "List all claims")
    public List<ClaimResponse> getAll() { return service.getAll(); }
    @PutMapping("/{id}") @Operation(summary = "Update a claim")
    public ClaimResponse update(@PathVariable Long id, @Valid @RequestBody ClaimRequest request) { return service.update(id, request); }
    @DeleteMapping("/{id}") @Operation(summary = "Delete a claim")
    @ApiResponse(responseCode = "204", description = "Claim deleted")
    public ResponseEntity<Void> delete(@PathVariable Long id) { service.delete(id); return ResponseEntity.noContent().build(); }
}
