package com.turkcell.sol.order_service.dto.payment;

import java.time.LocalDateTime;
import java.util.UUID;

public record PaymentResponse(
        UUID id,
        boolean success,
        String orderId,
        LocalDateTime createdDate
) {
}
