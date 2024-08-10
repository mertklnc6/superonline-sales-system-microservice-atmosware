package com.turkcell.sol.catalog_service.mapper;

import com.turkcell.sol.catalog_service.dto.responses.GetCatalogItemResponse;
import com.turkcell.sol.catalog_service.model.CatalogItem;
import com.turkcell.sol.catalog_service.shared.mapping.MapstructService;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = MapstructService.class)
public interface CatalogMapper {

    GetCatalogItemResponse toGetCatalogItemResponse(CatalogItem catalogItem);
    List<GetCatalogItemResponse> toGetCatalogItemResponse(List<CatalogItem> catalogItemList);
}
