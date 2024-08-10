package com.turkcell.sol.catalog_service.mapper;


import com.turkcell.sol.catalog_service.dto.requests.CreateProductRequest;
import com.turkcell.sol.catalog_service.dto.requests.UpdateProductRequest;
import com.turkcell.sol.catalog_service.dto.responses.CreatedProductResponse;
import com.turkcell.sol.catalog_service.dto.responses.DeletedProductResponse;
import com.turkcell.sol.catalog_service.dto.responses.GetProductResponse;
import com.turkcell.sol.catalog_service.dto.responses.UpdatedProductResponse;
import com.turkcell.sol.catalog_service.model.CatalogItem;
import com.turkcell.sol.catalog_service.model.Product;
import com.turkcell.sol.catalog_service.shared.dto.rabbitMQ.Product.ProductCreatedEvent;
import com.turkcell.sol.catalog_service.shared.dto.rabbitMQ.Product.ProductDeletedEvent;
import com.turkcell.sol.catalog_service.shared.dto.rabbitMQ.Product.ProductUpdatedEvent;
import com.turkcell.sol.catalog_service.shared.mapping.MapstructService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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

    @Mapping(target = "price", source = "product.price", numberFormat = "#.##")
    @Mapping(target = "id", source = "product.id")
    CatalogItem toCatalogItem(Product product);

    @Mapping(target = "price", source = "catalogItem.price", numberFormat = "#.##")
    @Mapping(target = "id", source = "catalogItem.id")
    Product toProduct(CatalogItem catalogItem);

    List<Product> toProduct(List<CatalogItem> catalogItemList);
    List<CatalogItem> toCatalogItem(List<Product> productList);

    ProductCreatedEvent toProductCreatedEvent(Product product);
    ProductUpdatedEvent toProductUpdatedEvent(Product product);
    ProductDeletedEvent toProductDeletedEvent(Product product);
}
