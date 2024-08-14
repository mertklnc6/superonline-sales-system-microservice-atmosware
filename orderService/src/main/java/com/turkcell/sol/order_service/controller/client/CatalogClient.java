package com.turkcell.sol.order_service.controller.client;

import com.turkcell.sol.order_service.dto.responses.GetProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "catalog-service", url = "http://localhost:7008/catalog-service/api/v1/products")
public interface CatalogClient {

    @GetMapping("/{id}")
    GetProductResponse getById(@PathVariable String id);

    @GetMapping("/getStockInfoById/{id}")
    boolean getStockInfoById(@PathVariable String id);

    @GetMapping("/getAllByIds")
    List<GetProductResponse> getAllByIds(@RequestParam List<String> ids);

}
