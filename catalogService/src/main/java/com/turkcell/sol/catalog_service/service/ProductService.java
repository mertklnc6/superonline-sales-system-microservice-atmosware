package com.turkcell.sol.catalog_service.service;

import com.turkcell.sol.catalog_service.dto.requests.CreateProductRequest;
import com.turkcell.sol.catalog_service.dto.requests.UpdateProductRequest;
import com.turkcell.sol.catalog_service.dto.responses.CreatedProductResponse;
import com.turkcell.sol.catalog_service.dto.responses.DeletedProductResponse;
import com.turkcell.sol.catalog_service.dto.responses.GetProductResponse;
import com.turkcell.sol.catalog_service.dto.responses.UpdatedProductResponse;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    CreatedProductResponse add(CreateProductRequest createProductRequest);
    boolean getStockInfo(String id);
    List<GetProductResponse> getAll();
    List<GetProductResponse> getAllByIds(List<String> ids);
    GetProductResponse getById(UUID id);
    UpdatedProductResponse update(UpdateProductRequest updateProductRequest);
    DeletedProductResponse delete(UUID id);
}
