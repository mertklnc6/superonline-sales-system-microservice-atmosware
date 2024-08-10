package com.turkcell.sol.catalog_service.dto.requests;

public record CreateProductRequest(
        String name,
        String description,
        boolean hasStock,
        double price
) {
}
