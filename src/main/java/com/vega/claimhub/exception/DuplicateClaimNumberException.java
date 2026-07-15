package com.vega.claimhub.exception;

public class DuplicateClaimNumberException extends RuntimeException {
    public DuplicateClaimNumberException(String claimNumber) { super("Claim number '" + claimNumber + "' already exists"); }
}
