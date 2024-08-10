package com.turkcell.sol.order_service.dto.responses;

import java.time.LocalDateTime;
import java.util.UUID;

public record UpdatedOrderResponse(
        UUID id,
        String customerName,
        String address,
        double totalPrice,
        String orderNumber,
        LocalDateTime updatedDate
) {
}
