package com.turkcell.sol.stock_service.mapper;

import com.turkcell.sol.stock_service.dto.responses.GetStockResponse;
import com.turkcell.sol.stock_service.model.ProductStock;
import com.turkcell.sol.stock_service.shared.dto.rabbitMQ.Product.ProductCreatedEvent;
import com.turkcell.sol.stock_service.shared.dto.rabbitMQ.Product.ProductDeletedEvent;
import com.turkcell.sol.stock_service.shared.dto.rabbitMQ.Product.ProductUpdatedEvent;
import com.turkcell.sol.stock_service.shared.mapping.MapstructService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = MapstructService.class)
public interface ProductStockMapper {
    @Mapping(target = "productId", source = "productCreatedEvent.id")
    @Mapping(target = "stock", source = "productCreatedEvent.stock")
    ProductStock toProductStock(ProductCreatedEvent productCreatedEvent);

    @Mapping(target = "productId", source = "productUpdatedEvent.id")
    @Mapping(target = "stock", source = "productUpdatedEvent.stock")
    ProductStock toProductStock(ProductUpdatedEvent productUpdatedEvent);

    @Mapping(target = "productId", source = "productDeletedEvent.id")
    ProductStock toProductStock(ProductDeletedEvent productDeletedEvent);

    GetStockResponse toGetStockResponse(ProductStock productStock);
    List<GetStockResponse> toGetStockResponse(List<ProductStock> productStockList);
}
