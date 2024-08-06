package com.turkcell.sol.catalog_service.repository;

import com.turkcell.sol.catalog_service.model.Product;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductCacheRepository {
    List<Product> getAll();

    Optional<Product> getById(String id);

    void addOrUpdate(Product product);

    void delete(String id);
}
