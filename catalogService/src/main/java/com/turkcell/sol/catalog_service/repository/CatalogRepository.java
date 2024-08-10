package com.turkcell.sol.catalog_service.repository;

import com.turkcell.sol.catalog_service.model.CatalogItem;

import java.util.List;
import java.util.Optional;

public interface CatalogRepository {
    List<CatalogItem> getAll();

    Optional<CatalogItem> getById(String id);

    void addOrUpdate(CatalogItem catalogItem);

    void delete(String id);
}
