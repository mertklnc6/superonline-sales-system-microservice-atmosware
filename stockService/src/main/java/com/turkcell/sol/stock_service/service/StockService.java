package com.turkcell.sol.stock_service.service;

import com.turkcell.sol.core.shared.dto.rabbitMQ.Product.ProductCreatedEvent;
import com.turkcell.sol.core.shared.dto.rabbitMQ.Product.ProductDeletedEvent;
import com.turkcell.sol.core.shared.dto.rabbitMQ.Product.ProductUpdatedEvent;
import com.turkcell.sol.stock_service.model.ProductStock;

public interface StockService {
    void add(ProductCreatedEvent productCreatedEvent);
    void update(ProductUpdatedEvent productUpdatedEvent);
    void delete(ProductDeletedEvent productDeletedEvent);
    void decreaseStock();
}
