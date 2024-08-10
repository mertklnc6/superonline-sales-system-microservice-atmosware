package com.turkcell.sol.catalog_service.dto.responses;

import java.time.LocalDateTime;
import java.util.UUID;

public record UpdatedProductResponse(
        UUID id,
        String name,
        String description,
        double price,
        boolean hasStock,
        LocalDateTime updatedDate
) {
}
