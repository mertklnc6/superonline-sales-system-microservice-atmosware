package com.turkcell.sol.order_service.shared.exceptions.types;

public class AuthorizationException extends RuntimeException {
    public AuthorizationException(String message) {
        super(message);
    }
}
