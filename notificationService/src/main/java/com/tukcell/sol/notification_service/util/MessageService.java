package com.tukcell.sol.notification_service.util;

public interface MessageService {
    String getMessage(String key);

    String getMessage(String key, Object[] args);
}
