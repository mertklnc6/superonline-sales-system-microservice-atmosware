package com.tukcell.sol.notification_service.shared.dto.rabbitMQ.Order;

public record OrderNotificationEvent(
        String id,
        String message
) {
}
