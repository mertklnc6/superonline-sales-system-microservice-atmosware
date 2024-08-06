package com.turkcell.sol.catalog_service.dto.responses;

import java.time.LocalDateTime;
import java.util.UUID;

public record DeletedProductResponse(
        UUID id,
        String name,
        LocalDateTime deletedDate
) {
}
