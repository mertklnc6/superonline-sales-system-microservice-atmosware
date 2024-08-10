package com.turkcell.sol.order_service.dto.requests;

import java.util.UUID;

public record UpdateOrderRequest(
        UUID id,
        String customerName,
        String address
) {
}
