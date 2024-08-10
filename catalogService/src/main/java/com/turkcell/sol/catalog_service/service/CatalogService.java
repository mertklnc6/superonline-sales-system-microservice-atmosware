package com.turkcell.sol.catalog_service.service;

import com.turkcell.sol.catalog_service.dto.responses.GetCatalogItemResponse;
import com.turkcell.sol.catalog_service.dto.responses.GetProductResponse;
import com.turkcell.sol.catalog_service.model.CatalogItem;
import com.turkcell.sol.catalog_service.model.Product;

import java.util.List;

public interface CatalogService {
    List<GetCatalogItemResponse> getAll();
    GetCatalogItemResponse getById(String id);
    void delete(String id);
    void add(CatalogItem catalogItem);
}
