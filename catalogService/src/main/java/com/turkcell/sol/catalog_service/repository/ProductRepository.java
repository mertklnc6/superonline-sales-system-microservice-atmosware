package com.turkcell.sol.catalog_service.repository;

import com.turkcell.sol.catalog_service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
