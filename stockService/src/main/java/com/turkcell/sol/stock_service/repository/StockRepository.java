package com.turkcell.sol.stock_service.repository;

import com.turkcell.sol.stock_service.model.ProductStock;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StockRepository extends MongoRepository<ProductStock, Integer> {
    Optional<ProductStock> findByProductId(String id);
}
