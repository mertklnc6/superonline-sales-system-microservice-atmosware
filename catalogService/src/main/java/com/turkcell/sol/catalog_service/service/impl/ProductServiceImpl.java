package com.turkcell.sol.catalog_service.service.impl;

import com.turkcell.sol.catalog_service.dto.requests.CreateProductRequest;
import com.turkcell.sol.catalog_service.dto.requests.UpdateProductRequest;
import com.turkcell.sol.catalog_service.dto.responses.CreatedProductResponse;
import com.turkcell.sol.catalog_service.dto.responses.DeletedProductResponse;
import com.turkcell.sol.catalog_service.dto.responses.GetProductResponse;
import com.turkcell.sol.catalog_service.dto.responses.UpdatedProductResponse;
import com.turkcell.sol.catalog_service.mapper.ProductMapper;
import com.turkcell.sol.catalog_service.model.CatalogItem;
import com.turkcell.sol.catalog_service.model.Product;
import com.turkcell.sol.catalog_service.repository.ProductRepository;
import com.turkcell.sol.catalog_service.service.CatalogService;
import com.turkcell.sol.catalog_service.service.ProductService;
import com.turkcell.sol.catalog_service.service.rules.ProductBusinessRules;
import com.turkcell.sol.catalog_service.shared.dto.rabbitMQ.Product.ProductCreatedEvent;
import com.turkcell.sol.catalog_service.shared.dto.rabbitMQ.Product.ProductDeletedEvent;
import com.turkcell.sol.catalog_service.shared.dto.rabbitMQ.Product.ProductUpdatedEvent;
import com.turkcell.sol.catalog_service.util.rabbitMQ.sender.ProductSender;
import jakarta.transaction.Transactional;
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
    private final ProductSender productSender;
    private final CatalogService catalogService;

    @Transactional
    @Override
    public CreatedProductResponse add(CreateProductRequest createProductRequest) {

        Product product = productMapper.toProduct(createProductRequest);
        productRepository.save(product);

        ProductCreatedEvent productCreatedEvent = productMapper.toProductCreatedEvent(product);

        if (product.isHasStock()) {
            productSender.send(productCreatedEvent);
        }

        CatalogItem catalogItem = productMapper.toCatalogItem(product);
        catalogService.add(catalogItem);

        return productMapper.toCreatedProductResponse(product);
    }

    @Override
    public List<GetProductResponse> getAll() {

        List<Product> productList = productRepository.findAll();
        return productMapper.toGetProductResponse(productList);
    }

    @Override
    public GetProductResponse getById(UUID id) {

        Optional<Product> productOptional = productRepository.findById(id);

        productBusinessRules.productShouldBeExist(productOptional);

        return productMapper.toGetProductResponse(productOptional.get());
    }

    @Override
    public UpdatedProductResponse update(UpdateProductRequest updateProductRequest) {

        Product product = productMapper.toProduct(updateProductRequest);

        productRepository.save(product);

        if (product.isHasStock()) {
            ProductUpdatedEvent productUpdatedEvent = productMapper.toProductUpdatedEvent(product);
            productSender.send(productUpdatedEvent);
        }

        CatalogItem catalogItem = productMapper.toCatalogItem(product);
        catalogService.add(catalogItem);

        return productMapper.toUpdatedProductResponse(product);
    }

    @Override
    public DeletedProductResponse delete(UUID id) {

        Optional<Product> productOptional = productRepository.findById(id);

        productBusinessRules.productShouldBeExist(productOptional);

        Product product = productOptional.get();
        product.setDeletedDate(LocalDateTime.now());

        productRepository.save(product);

        catalogService.delete(id.toString());

        ProductDeletedEvent productDeletedEvent = productMapper.toProductDeletedEvent(product);
        productSender.send(productDeletedEvent);

        return productMapper.toDeletedProductResponse(product);
    }
}
