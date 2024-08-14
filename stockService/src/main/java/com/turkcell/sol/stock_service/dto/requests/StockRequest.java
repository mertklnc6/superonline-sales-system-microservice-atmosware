package com.turkcell.sol.stock_service.dto.requests;

public record StockRequest(
        String productId,
        int quantity
) {
}
