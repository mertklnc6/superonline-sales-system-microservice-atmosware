package com.turkcell.sol.stock_service.dto.requests;

import java.util.UUID;

public record UpdateProductRequest(
        String name,
        String description,
        double price,
        int stock
) {
}
