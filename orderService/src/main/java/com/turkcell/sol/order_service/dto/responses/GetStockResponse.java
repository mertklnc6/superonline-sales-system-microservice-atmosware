package com.turkcell.sol.order_service.dto.responses;

public record GetStockResponse(
        String id,
        String productId,
        int stock
) {
}
