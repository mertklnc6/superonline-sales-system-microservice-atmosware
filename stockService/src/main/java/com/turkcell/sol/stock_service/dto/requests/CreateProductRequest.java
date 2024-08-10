package com.turkcell.sol.stock_service.dto.requests;

public record CreateProductRequest(
        String name,
        String description,
        int stock,
        double price
) {
}
