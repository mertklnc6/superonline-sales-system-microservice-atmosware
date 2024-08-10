package com.turkcell.sol.catalog_service.service.impl;

import com.turkcell.sol.catalog_service.dto.requests.CreateProductRequest;
import com.turkcell.sol.catalog_service.dto.requests.UpdateProductRequest;
import com.turkcell.sol.catalog_service.dto.responses.CreatedProductResponse;
import com.turkcell.sol.catalog_service.dto.responses.DeletedProductResponse;
import com.turkcell.sol.catalog_service.dto.responses.GetProductResponse;
import com.turkcell.sol.catalog_service.dto.responses.UpdatedProductResponse;
import com.turkcell.sol.catalog_service.mapper.ProductMapper;
import com.turkcell.sol.catalog_service.model.Product;
import com.turkcell.sol.catalog_service.model.ProductCache;
import com.turkcell.sol.catalog_service.repository.ProductCacheRepository;
import com.turkcell.sol.catalog_service.repository.ProductRepository;
import com.turkcell.sol.catalog_service.service.ProductService;
import com.turkcell.sol.catalog_service.service.rules.ProductBusinessRules;
import com.turkcell.sol.catalog_service.util.rabbitMQ.sender.ProductSender;
import com.turkcell.sol.core.shared.dto.rabbitMQ.Product.ProductCreatedEvent;
import com.turkcell.sol.core.shared.dto.rabbitMQ.Product.ProductDeletedEvent;
import com.turkcell.sol.core.shared.dto.rabbitMQ.Product.ProductUpdatedEvent;
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
    private final ProductCacheRepository productCacheRepository;
    private final ProductSender productSender;

    @Transactional
    @Override
    public CreatedProductResponse add(CreateProductRequest createProductRequest) {

        Product product = productMapper.toProduct(createProductRequest);
        productRepository.save(product);

        ProductCache productCache =  productMapper.toProductCache(product);
        productCacheRepository.addOrUpdate(productCache);

        ProductCreatedEvent productCreatedEvent = productMapper.toProductCreatedEvent(product);

        if(product.isHaveStock()){
            productCreatedEvent.setStock(createProductRequest.stock());
            productSender.send(productCreatedEvent);
        }

        return productMapper.toCreatedProductResponse(product);
    }

    @Override
    public List<GetProductResponse> getAllProducts() {

        List<Product> productList = productRepository.findAll();
        return productMapper.toGetProductResponse(productList);
    }

    @Override
    public List<GetProductResponse> getCatalog() {

        List<ProductCache> productCacheList = productCacheRepository.getAll();

        List<Product> productList = productMapper.toProduct(productCacheList);

        return productMapper.toGetProductResponse(productList);
    }


    @Override
    public GetProductResponse getById(String id) {

        Optional<ProductCache> productCacheOptional = productCacheRepository.getById(id);

        Product product = productMapper.toProduct(productCacheOptional.get());

        productBusinessRules.productShouldBeExist(Optional.of(product));

        return productMapper.toGetProductResponse(product);
    }

    @Override
    public UpdatedProductResponse update(UpdateProductRequest updateProductRequest) {

        Product product = productMapper.toProduct(updateProductRequest);

        productRepository.save(product);

        ProductCache productCache = productMapper.toProductCache(product);
        productCacheRepository.addOrUpdate(productCache);

        ProductUpdatedEvent productUpdatedEvent = productMapper.toProductUpdatedEvent(product);
        productSender.send(productUpdatedEvent);

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

        ProductDeletedEvent productDeletedEvent = productMapper.toProductDeletedEvent(product);
        productSender.send(productDeletedEvent);

        return productMapper.toDeletedProductResponse(product);
    }

    @Override
    public void deleteFromCatalog(UUID id) {
        productCacheRepository.delete(id.toString());
    }
}
