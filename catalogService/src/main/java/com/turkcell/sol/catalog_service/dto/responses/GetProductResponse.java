package com.turkcell.sol.catalog_service.dto.responses;

import java.util.UUID;

public record GetProductResponse(
        UUID id,
        String name,
        double price,
        int stock

) {
}
