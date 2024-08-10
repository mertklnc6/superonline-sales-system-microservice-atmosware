package com.turkcell.sol.stock_service.util;

public interface MessageService {
    String getMessage(String key);

    String getMessage(String key, Object[] args);
}
