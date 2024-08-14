package com.turkcell.sol.stock_service.controller;

import com.turkcell.sol.stock_service.dto.requests.StockRequest;
import com.turkcell.sol.stock_service.dto.responses.GetStockResponse;
import com.turkcell.sol.stock_service.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stock-service/api/v1/stocks")
public class StockController {

    private final StockService stockService;

    @GetMapping
    public List<GetStockResponse> getAll(){
        return stockService.getAll();
    }

    @GetMapping("/{id}")
    public GetStockResponse getByProductId(@PathVariable String id){
        return stockService.getById(id);
    }

    @PutMapping("/decreaseStock")
    public void decreaseStock(@RequestBody List<StockRequest> stockRequestList){
        stockService.decreaseStock(stockRequestList);
    }

    @PutMapping("/rollbackStock")
    public void rollbackStock(@RequestBody List<StockRequest> stockRequestList){
        stockService.rollbackStock(stockRequestList);
    }

    @PutMapping("/removeOutOfStockProductsFromCatalog")
    public void removeOutOfStockProductsFromCatalog(){
        stockService.removeOutOfStockProductsFromCatalog();
    }
}
