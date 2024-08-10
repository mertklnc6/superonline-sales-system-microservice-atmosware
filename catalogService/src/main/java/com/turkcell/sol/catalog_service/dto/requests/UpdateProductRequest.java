package com.turkcell.sol.catalog_service.dto.requests;

import java.util.UUID;

public record UpdateProductRequest(
        UUID id,
        String name,
        String description,
        double price,
        boolean hasStock
) {
}
