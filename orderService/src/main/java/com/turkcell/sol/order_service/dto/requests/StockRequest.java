package com.turkcell.sol.order_service.dto.requests;

public record StockRequest(
        String productId,
        int quantity
) {
}
