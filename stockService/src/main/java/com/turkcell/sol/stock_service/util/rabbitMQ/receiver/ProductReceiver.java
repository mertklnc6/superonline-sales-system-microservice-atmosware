package com.turkcell.sol.stock_service.util.rabbitMQ.receiver;


import com.turkcell.sol.stock_service.model.ProductStock;
import com.turkcell.sol.stock_service.service.StockService;
import com.turkcell.sol.stock_service.shared.dto.rabbitMQ.Product.ProductCreatedEvent;
import com.turkcell.sol.stock_service.shared.dto.rabbitMQ.Product.ProductDeletedEvent;
import com.turkcell.sol.stock_service.shared.dto.rabbitMQ.Product.ProductUpdatedEvent;
import com.turkcell.sol.stock_service.mapper.ProductStockMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductReceiver {

    private final StockService stockService;
    private final ProductStockMapper productStockMapper;

    @RabbitListener(queues = "product-created", group = "stock.group")
    public void consume(ProductCreatedEvent productCreatedEvent) {
        ProductStock productStock = new ProductStock();
        productStock.setProductId(productCreatedEvent.getId());
        productStock.setStock(productCreatedEvent.getStock());
        stockService.add(productStock);
    }

    @RabbitListener(queues = "product-updated", group = "stock.group")
    public void consume(ProductUpdatedEvent productUpdatedEvent) {
        ProductStock productStock = new ProductStock();
        productStock.setProductId(productUpdatedEvent.getId());
        productStock.setStock(productUpdatedEvent.getStock());
        stockService.update(productStock);
    }

    @RabbitListener(queues = "product-deleted", group = "stock.group")
    public void consume(ProductDeletedEvent productDeletedEvent) {
        ProductStock productStock = productStockMapper.toProductStock(productDeletedEvent);
        stockService.delete(productStock);
    }
}
