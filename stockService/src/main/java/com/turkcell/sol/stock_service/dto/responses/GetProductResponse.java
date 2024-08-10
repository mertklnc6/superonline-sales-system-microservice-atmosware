package com.turkcell.sol.stock_service.dto.responses;

import java.util.UUID;

public record GetProductResponse(
        UUID id,
        String name,
        double price,
        int stock

) {
}
