package com.turkcell.sol.order_service.shared.dto.rabbitMQ.Order;

public record OrderNotificationEvent(
        String id,
        String message
) {
}
