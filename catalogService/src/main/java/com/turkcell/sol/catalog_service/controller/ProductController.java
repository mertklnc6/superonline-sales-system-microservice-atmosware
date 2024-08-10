package com.turkcell.sol.catalog_service.controller;

import com.turkcell.sol.catalog_service.dto.requests.CreateProductRequest;
import com.turkcell.sol.catalog_service.dto.requests.UpdateProductRequest;
import com.turkcell.sol.catalog_service.dto.responses.CreatedProductResponse;
import com.turkcell.sol.catalog_service.dto.responses.DeletedProductResponse;
import com.turkcell.sol.catalog_service.dto.responses.GetProductResponse;
import com.turkcell.sol.catalog_service.dto.responses.UpdatedProductResponse;
import com.turkcell.sol.catalog_service.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @MutationMapping
    public CreatedProductResponse add(@Argument CreateProductRequest createProductRequest){
        return productService.add(createProductRequest);
    }

    @QueryMapping
    public List<GetProductResponse> getAll(){

        return productService.getAllProducts();
    }

    @QueryMapping
    public List<GetProductResponse> getCatalog(){
        return productService.getCatalog();
    }

    @QueryMapping
    public GetProductResponse getById(@Argument String id){
        return productService.getById(id);
    }

    @MutationMapping
    public UpdatedProductResponse update(@Argument UpdateProductRequest updateProductRequest){
        return productService.update(updateProductRequest);
    }

    @MutationMapping
    public DeletedProductResponse delete(@Argument UUID id){
        return productService.delete(id);
    }
}
