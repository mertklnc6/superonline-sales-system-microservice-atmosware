package com.turkcell.sol.catalog_service.mapper;


import com.turkcell.sol.catalog_service.dto.requests.CreateProductRequest;
import com.turkcell.sol.catalog_service.dto.requests.UpdateProductRequest;
import com.turkcell.sol.catalog_service.dto.responses.CreatedProductResponse;
import com.turkcell.sol.catalog_service.dto.responses.DeletedProductResponse;
import com.turkcell.sol.catalog_service.dto.responses.GetProductResponse;
import com.turkcell.sol.catalog_service.dto.responses.UpdatedProductResponse;
import com.turkcell.sol.catalog_service.model.Product;
import com.turkcell.sol.common.shared.mapping.MapstructService;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = MapstructService.class)
public interface ProductMapper {
    Product toProduct(CreateProductRequest createProductRequest);
    CreatedProductResponse toCreatedProductResponse(Product product);
    List<GetProductResponse> toGetProductResponse(List<Product> productList);
    GetProductResponse toGetProductResponse(Product product);
    Product toProduct(UpdateProductRequest updateProductRequest);
    UpdatedProductResponse toUpdatedProductResponse(Product product);
    DeletedProductResponse toDeletedProductResponse(Product product);
}
