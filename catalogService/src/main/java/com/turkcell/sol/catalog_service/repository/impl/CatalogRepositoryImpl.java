package com.turkcell.sol.catalog_service.repository.impl;

import com.turkcell.sol.catalog_service.model.CatalogItem;
import com.turkcell.sol.catalog_service.repository.CatalogRepository;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CatalogRepositoryImpl implements CatalogRepository {

    private static final String KEY = "PRODUCT_CACHE";
    private final HashOperations<String, String, CatalogItem> hashOperations;

    public CatalogRepositoryImpl(RedisTemplate<String, Object> redisTemplate){
        this.hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public List<CatalogItem> getAll() {
        Map<String, CatalogItem> entries = hashOperations.entries(KEY);
        return entries.values().stream().collect(Collectors.toList());
    }

    @Override
    public Optional<CatalogItem> getById(String id) {
        CatalogItem catalogItem = hashOperations.get(KEY,id);
        if(catalogItem == null){
            return Optional.empty();
        }
        return Optional.of(catalogItem);
    }

    @Override
    public void addOrUpdate(CatalogItem catalogItem) {
        hashOperations.put(KEY, catalogItem.getId(), catalogItem);
    }

    @Override
    public void delete(String id) {
        hashOperations.delete(KEY, id);
    }
}
