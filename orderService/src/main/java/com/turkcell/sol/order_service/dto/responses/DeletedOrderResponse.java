package com.turkcell.sol.order_service.dto.responses;

import java.time.LocalDateTime;
import java.util.UUID;

public record DeletedOrderResponse(
        UUID id,
        String orderNumber,
        String customerName,
        LocalDateTime deletedDate
) {
}
