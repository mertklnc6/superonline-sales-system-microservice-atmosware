package com.turkcell.sol.catalog_service.service.impl;

import com.turkcell.sol.catalog_service.dto.requests.CreateProductRequest;
import com.turkcell.sol.catalog_service.dto.requests.UpdateProductRequest;
import com.turkcell.sol.catalog_service.dto.responses.CreatedProductResponse;
import com.turkcell.sol.catalog_service.dto.responses.DeletedProductResponse;
import com.turkcell.sol.catalog_service.dto.responses.GetProductResponse;
import com.turkcell.sol.catalog_service.dto.responses.UpdatedProductResponse;
import com.turkcell.sol.catalog_service.mapper.ProductMapper;
import com.turkcell.sol.catalog_service.model.Product;
import com.turkcell.sol.catalog_service.repository.ProductCacheRepository;
import com.turkcell.sol.catalog_service.repository.ProductRepository;
import com.turkcell.sol.catalog_service.service.ProductService;
import com.turkcell.sol.catalog_service.service.rules.ProductBusinessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final ProductBusinessRules productBusinessRules;
    private final ProductCacheRepository productCacheRepository;

    @Override
    public CreatedProductResponse add(CreateProductRequest createProductRequest) {

        Product product = productMapper.toProduct(createProductRequest);
        productRepository.save(product);

        productCacheRepository.addOrUpdate(product);

        return productMapper.toCreatedProductResponse(product);
    }

    @Override
    public List<GetProductResponse> getAll() {

        List<Product> productList = productCacheRepository.getAll();
        return productMapper.toGetProductResponse(productList);
    }

    @Override
    public GetProductResponse getById(String id) {

        Optional<Product> productOptional = productCacheRepository.getById(id);

        productBusinessRules.productShouldBeExist(productOptional);

        return productMapper.toGetProductResponse(productOptional.get());
    }

    @Override
    public UpdatedProductResponse update(UpdateProductRequest updateProductRequest) {

        Product product = productMapper.toProduct(updateProductRequest);

        productRepository.save(product);

        productCacheRepository.addOrUpdate(product);

        return productMapper.toUpdatedProductResponse(product);
    }

    @Override
    public DeletedProductResponse delete(UUID id) {

        Optional<Product> productOptional = productRepository.findById(id);

        productBusinessRules.productShouldBeExist(productOptional);

        Product product = productOptional.get();

        product.setDeletedDate(LocalDateTime.now());

        productRepository.save(product);

        productCacheRepository.delete(id.toString());

        return productMapper.toDeletedProductResponse(product);
    }
}
