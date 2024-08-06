package com.turkcell.sol.catalog_service.dto.requests;

public record CreateProductRequest(
        String name,
        String description,
        int stock,
        double price
) {
}
