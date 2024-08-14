package com.turkcell.sol.catalog_service.repository;

import com.turkcell.sol.catalog_service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findAllByIdIsIn(List<UUID> ids);
}
