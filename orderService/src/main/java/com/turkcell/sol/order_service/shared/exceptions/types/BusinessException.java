package com.turkcell.sol.order_service.shared.exceptions.types;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
