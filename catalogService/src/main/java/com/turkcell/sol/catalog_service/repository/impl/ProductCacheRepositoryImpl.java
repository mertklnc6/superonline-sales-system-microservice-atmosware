package com.turkcell.sol.catalog_service.repository.impl;

import com.turkcell.sol.catalog_service.model.Product;
import com.turkcell.sol.catalog_service.repository.ProductCacheRepository;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ProductCacheRepositoryImpl implements ProductCacheRepository {

    private static final String KEY = "PRODUCT_CACHE";
    private final HashOperations<String, String, Product> hashOperations;

    public ProductCacheRepositoryImpl(RedisTemplate<String, Object> redisTemplate){
        this.hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public List<Product> getAll() {
        Map<String, Product> entries = hashOperations.entries(KEY);
        return entries.values().stream().collect(Collectors.toList());
    }

    @Override
    public Optional<Product> getById(String id) {
        Product product = hashOperations.get(KEY,id);
        if(product == null){
            return Optional.empty();
        }
        return Optional.of(product);
    }

    @Override
    public void addOrUpdate(Product product) {
        hashOperations.put(KEY, product.getId().toString(), product);
    }

    @Override
    public void delete(String id) {
        hashOperations.delete(KEY, id);
    }
}
