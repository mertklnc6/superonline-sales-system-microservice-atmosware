package com.turkcell.sol.stock_service.service;

import com.turkcell.sol.stock_service.dto.requests.StockRequest;
import com.turkcell.sol.stock_service.dto.responses.GetStockResponse;
import com.turkcell.sol.stock_service.model.ProductStock;

import java.util.List;

public interface StockService {
    List<GetStockResponse> getAll();
    GetStockResponse getById(String id);
    void add(ProductStock productStock);
    void update(ProductStock productStock);
    void delete(ProductStock productStock);
    void decreaseStock(List<StockRequest> StockRequestList);
    void rollbackStock(List<StockRequest> StockRequestList);
    void removeOutOfStockProductsFromCatalog();
}
