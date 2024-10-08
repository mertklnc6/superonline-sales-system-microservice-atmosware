package com.turkcell.sol.order_service.controller.client;

import com.turkcell.sol.order_service.dto.requests.StockRequest;
import com.turkcell.sol.order_service.dto.responses.GetStockResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "stock-service", url = "http://localhost:7003/stock-service/api/v1/stocks")
public interface StockClient {
    @GetMapping("/{id}")
    GetStockResponse getByProductId(@PathVariable String id);

    @PutMapping("/decreaseStock")
    void decreaseStock(@RequestBody List<StockRequest> stockRequestList);

    @PutMapping("/rollbackStock")
    void rollbackStock(@RequestBody List<StockRequest> stockRequestList);

    @PutMapping("/removeOutOfStockProductsFromCatalog")
    void removeOutOfStockProductsFromCatalog();
}
