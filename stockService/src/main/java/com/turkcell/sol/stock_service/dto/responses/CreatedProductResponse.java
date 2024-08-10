package com.turkcell.sol.stock_service.dto.responses;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreatedProductResponse(
        UUID id,
        String name,
        String description,
        double price,
        int stock,
        LocalDateTime createdDate
) {
}
