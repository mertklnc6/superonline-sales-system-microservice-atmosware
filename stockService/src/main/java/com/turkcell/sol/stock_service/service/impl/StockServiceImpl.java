package com.turkcell.sol.stock_service.service.impl;

import com.turkcell.sol.stock_service.dto.requests.StockRequest;
import com.turkcell.sol.stock_service.dto.responses.GetStockResponse;
import com.turkcell.sol.stock_service.mapper.ProductStockMapper;
import com.turkcell.sol.stock_service.model.ProductStock;
import com.turkcell.sol.stock_service.repository.StockRepository;
import com.turkcell.sol.stock_service.service.StockService;
import com.turkcell.sol.stock_service.service.rules.StockBusinessRules;
import com.turkcell.sol.stock_service.shared.dto.rabbitMQ.Stock.OutOfStockEvent;
import com.turkcell.sol.stock_service.util.rabbitMQ.sender.StockSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;
    private final ProductStockMapper productStockMapper;
    private final StockBusinessRules stockBusinessRules;
    private final StockSender stockSender;

    @Override
    public List<GetStockResponse> getAll() {
        List<ProductStock> productStockList = stockRepository.findAll();
        return productStockMapper.toGetStockResponse(productStockList);
    }

    @Override
    public GetStockResponse getById(String id) {
        Optional<ProductStock> productStockOptional = stockRepository.findByProductId(id);

        stockBusinessRules.stockShouldBeExist(productStockOptional);
        stockBusinessRules.stockShouldBeInStock(productStockOptional.get());

        return productStockMapper.toGetStockResponse(productStockOptional.get());
    }

    @Override
    public void add(ProductStock productStock) {
        stockRepository.save(productStock);
    }

    @Override
    public void update(ProductStock productStock) {
        stockRepository.save(productStock);
    }

    @Override
    public void delete(ProductStock productStock) {
        stockRepository.delete(productStock);
    }

    @Override
    public void decreaseStock(List<StockRequest> stockRequestList) {
        for (StockRequest request : stockRequestList) {
            Optional<ProductStock> productStockOptional = stockRepository.findByProductId(request.productId());

            stockBusinessRules.stockShouldBeExist(productStockOptional);
            stockBusinessRules.stockShouldBeInStock(productStockOptional.get());

            ProductStock productStock = productStockOptional.get();
            productStock.setStock(productStock.getStock() - request.quantity());

            stockBusinessRules.stockShouldNotBeLessThanZero(productStock);

            stockRepository.save(productStock);
        }
    }

    @Override
    public void rollbackStock(List<StockRequest> stockRequestList) {
        for (StockRequest request : stockRequestList) {
            Optional<ProductStock> productStockOptional = stockRepository.findByProductId(request.productId());

            stockBusinessRules.stockShouldBeExist(productStockOptional);

            ProductStock productStock = productStockOptional.get();
            productStock.setStock(productStock.getStock() + request.quantity());

            stockRepository.save(productStock);
        }
    }

    @Override
    public void removeOutOfStockProductsFromCatalog() {
        List<ProductStock> outOfStockProducts = stockRepository.findByStock(0);

        for (ProductStock productStock : outOfStockProducts) {

            OutOfStockEvent outOfStockEvent = new OutOfStockEvent();
            outOfStockEvent.setId(productStock.getProductId());
            stockSender.send(outOfStockEvent);
        }
    }
}
