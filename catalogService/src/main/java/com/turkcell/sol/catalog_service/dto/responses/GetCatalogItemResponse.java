package com.turkcell.sol.catalog_service.dto.responses;

import java.util.UUID;

public record GetCatalogItemResponse (
        String id,
        String name,
        String description,
        String price
){
}
