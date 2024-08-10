package com.turkcell.sol.catalog_service.controller;

import com.turkcell.sol.catalog_service.dto.requests.CreateProductRequest;
import com.turkcell.sol.catalog_service.dto.requests.UpdateProductRequest;
import com.turkcell.sol.catalog_service.dto.responses.*;
import com.turkcell.sol.catalog_service.service.CatalogService;
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
@RequestMapping("/api/v1/catalog")
public class CatalogController {

    private final CatalogService catalogService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetCatalogItemResponse> get(){
        return catalogService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetCatalogItemResponse getById(@PathVariable String id){
        return catalogService.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String id){
        catalogService.delete(id);
    }

}

