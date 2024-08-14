package com.tukcell.sol.notification_service.shared.exceptions.types;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
