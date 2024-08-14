package com.turkcell.sol.catalog_service.rabbitMQ.receiver;

import com.turkcell.sol.catalog_service.model.Product;
import com.turkcell.sol.catalog_service.service.CatalogService;
import com.turkcell.sol.catalog_service.service.ProductService;
import com.turkcell.sol.catalog_service.shared.dto.rabbitMQ.Product.ProductCreatedEvent;
import com.turkcell.sol.catalog_service.shared.dto.rabbitMQ.Stock.OutOfStockEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockReceiver {

    private final CatalogService catalogService;

    @RabbitListener(queues = "out-of-stock", group = "product.group")
    public void consume(OutOfStockEvent outOfStockEvent) {
        catalogService.delete(outOfStockEvent.getId());
    }
}