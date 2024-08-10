package com.turkcell.sol.stock_service.service.impl;

import com.turkcell.sol.stock_service.model.ProductStock;
import com.turkcell.sol.stock_service.repository.StockRepository;
import com.turkcell.sol.stock_service.service.StockService;
import com.turkcell.sol.stock_service.shared.dto.rabbitMQ.Product.ProductCreatedEvent;
import com.turkcell.sol.stock_service.shared.dto.rabbitMQ.Product.ProductDeletedEvent;
import com.turkcell.sol.stock_service.shared.dto.rabbitMQ.Product.ProductUpdatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;

    @Override
    public void add(ProductCreatedEvent productCreatedEvent) {

        ProductStock productStock = new ProductStock();
        productStock.setProductId(productCreatedEvent.getId());
        productStock.setStock(productCreatedEvent.getStock());

        stockRepository.save(productStock);
    }

    @Override
    public void update(ProductUpdatedEvent productUpdatedEvent) {

        Optional<ProductStock> productStockOptional = stockRepository.findByProductId(productUpdatedEvent.getId());

    }

    @Override
    public void delete(ProductDeletedEvent productDeletedEvent) {

    }

    @Override
    public void decreaseStock() {

    }
}
