package com.turkcell.sol.payment_service.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record PaymentResponse(
        UUID id,
        boolean success,
        String orderId,
        LocalDateTime createdDate
) {
}
