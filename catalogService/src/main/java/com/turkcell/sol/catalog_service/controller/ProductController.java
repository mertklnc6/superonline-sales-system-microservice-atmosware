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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/catalog-service/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedProductResponse add(@Valid @RequestBody CreateProductRequest createProductRequest){

        return productService.add(createProductRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetProductResponse> getAll(){

        return productService.getAll();
    }

    @GetMapping("/getAllByIds")
    @ResponseStatus(HttpStatus.OK)
    public List<GetProductResponse> getAllByIds(List<String> ids){
        return productService.getAllByIds(ids);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetProductResponse getById(@PathVariable UUID id){

        return productService.getById(id);
    }

    @GetMapping("/getStockInfo/{id}")
    @ResponseStatus(HttpStatus.OK)
    public boolean getStockInfo(@PathVariable String id){
        return productService.getStockInfo(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UpdatedProductResponse update(@Valid @RequestBody UpdateProductRequest updateProductRequest){

        return productService.update(updateProductRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DeletedProductResponse delete(@PathVariable UUID id){
        return productService.delete(id);
    }
}
