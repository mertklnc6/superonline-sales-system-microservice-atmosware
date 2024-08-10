package com.turkcell.sol.order_service.dto.responses;

import java.util.UUID;

public record GetOrderResponse(
        UUID id,
        String orderNumber,
        String customerName,
        String address,
        double totalPrice
) {
}
