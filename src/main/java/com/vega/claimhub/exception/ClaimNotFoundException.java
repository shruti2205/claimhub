package com.vega.claimhub.exception;

public class ClaimNotFoundException extends RuntimeException {
    public ClaimNotFoundException(Long id) { super("Claim with id " + id + " was not found"); }
}
