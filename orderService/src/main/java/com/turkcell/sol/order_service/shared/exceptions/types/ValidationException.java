package com.turkcell.sol.order_service.shared.exceptions.types;

import java.util.Map;

public class ValidationException extends RuntimeException {
    public ValidationException(String message, Map<String, String> errors) {
        super(message);
        this.errors = errors;
    }

    private final Map<String, String> errors;

    public Map<String, String> getErrors() {
        return errors;
    }
}
