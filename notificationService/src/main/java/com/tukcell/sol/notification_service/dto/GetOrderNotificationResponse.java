package com.tukcell.sol.notification_service.dto;

import java.time.LocalDateTime;

public record GetOrderNotificationResponse(
        String id,
        String orderId,
        String message,
        LocalDateTime createdDate
) {
}
