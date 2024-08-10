package com.turkcell.sol.stock_service.dto.responses;

import java.time.LocalDateTime;
import java.util.UUID;

public record UpdatedProductResponse(
        UUID id,
        String name,
        String description,
        double price,
        int stock,
        LocalDateTime updatedDate
) {
}
