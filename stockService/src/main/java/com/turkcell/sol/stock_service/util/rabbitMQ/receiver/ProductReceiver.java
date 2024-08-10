package com.turkcell.sol.stock_service.util.rabbitMQ.receiver;


import com.turkcell.sol.stock_service.service.StockService;
import com.turkcell.sol.stock_service.shared.dto.rabbitMQ.Product.ProductCreatedEvent;
import com.turkcell.sol.stock_service.shared.dto.rabbitMQ.Product.ProductDeletedEvent;
import com.turkcell.sol.stock_service.shared.dto.rabbitMQ.Product.ProductUpdatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductReceiver {

    private final StockService stockService;

    @RabbitListener(queues = "product-created", group = "stock.group")
    public void consume(ProductCreatedEvent productCreatedEvent) {
        stockService.add(productCreatedEvent);
    }

    @RabbitListener(queues = "product-updated", group = "stock.group")
    public void consume(ProductUpdatedEvent productUpdatedEvent) {
        stockService.update(productUpdatedEvent);
    }

    @RabbitListener(queues = "product-deleted", group = "stock.group")
    public void consume(ProductDeletedEvent productDeletedEvent) {
        stockService.delete(productDeletedEvent);
    }
}
