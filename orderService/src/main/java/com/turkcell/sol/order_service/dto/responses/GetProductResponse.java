package com.turkcell.sol.order_service.dto.responses;

import java.util.UUID;

public record GetProductResponse(
        UUID productId,
        String name,
        double price

) {
}
