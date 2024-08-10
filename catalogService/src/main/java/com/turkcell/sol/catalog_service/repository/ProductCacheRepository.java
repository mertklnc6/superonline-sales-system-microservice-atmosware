package com.turkcell.sol.catalog_service.repository;

import com.turkcell.sol.catalog_service.model.Product;
import com.turkcell.sol.catalog_service.model.ProductCache;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductCacheRepository {
    List<ProductCache> getAll();

    Optional<ProductCache> getById(String id);

    void addOrUpdate(ProductCache productCache);

    void delete(String id);
}
