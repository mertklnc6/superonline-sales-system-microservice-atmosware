package com.turkcell.sol.stock_service.dto.responses;

public record GetStockResponse(
        String id,
        String productId,
        int stock
) {
}
