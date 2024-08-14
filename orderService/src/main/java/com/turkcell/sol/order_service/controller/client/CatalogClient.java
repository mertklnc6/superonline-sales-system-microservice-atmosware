package com.turkcell.sol.order_service.controller.client;

import com.turkcell.sol.order_service.dto.responses.GetProductResponse;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "catalog-service", path = "catalog-service/api/v1")
public interface CatalogClient {

    @GetMapping("/products/{id}")
    void getByIdProduct(@PathVariable String id);

    @GetMapping("/products/getStockInfo/{id}")
    boolean getStockInfoById(@PathVariable String id);

    @GetMapping("/products/getAllByIds")
    List<GetProductResponse> getAllByIds(@RequestBody List<String> ids);

}
