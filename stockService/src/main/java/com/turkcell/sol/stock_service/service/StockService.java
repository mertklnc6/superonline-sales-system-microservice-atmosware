package com.turkcell.sol.stock_service.service;

import com.turkcell.sol.stock_service.model.ProductStock;
import com.turkcell.sol.stock_service.shared.dto.rabbitMQ.Product.ProductCreatedEvent;
import com.turkcell.sol.stock_service.shared.dto.rabbitMQ.Product.ProductDeletedEvent;
import com.turkcell.sol.stock_service.shared.dto.rabbitMQ.Product.ProductUpdatedEvent;

public interface StockService {
    void add(ProductCreatedEvent productCreatedEvent);
    void update(ProductUpdatedEvent productUpdatedEvent);
    void delete(ProductDeletedEvent productDeletedEvent);
    void decreaseStock();
}
