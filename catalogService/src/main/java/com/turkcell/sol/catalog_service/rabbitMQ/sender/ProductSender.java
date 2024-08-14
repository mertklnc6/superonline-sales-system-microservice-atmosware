package com.turkcell.sol.catalog_service.rabbitMQ.sender;


import com.turkcell.sol.catalog_service.shared.dto.rabbitMQ.Product.ProductCreatedEvent;
import com.turkcell.sol.catalog_service.shared.dto.rabbitMQ.Product.ProductDeletedEvent;
import com.turkcell.sol.catalog_service.shared.dto.rabbitMQ.Product.ProductUpdatedEvent;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductSender {

    private final RabbitTemplate rabbitTemplate;
    private static final Logger logger = LoggerFactory.getLogger(ProductSender.class);

    public void send(ProductCreatedEvent productCreatedEvent) {

        logger.info("Product created event sent to RabbitMQ: {}", productCreatedEvent);
        rabbitTemplate.convertAndSend("product-created", productCreatedEvent);
    }

    public void send(ProductUpdatedEvent productUpdatedEvent) {

        logger.info("Product updated event sent to RabbitMQ: {}", productUpdatedEvent);
        rabbitTemplate.convertAndSend("product-updated", productUpdatedEvent);
    }

    public void send(ProductDeletedEvent productDeletedEvent) {

        logger.info("Product deleted event sent to RabbitMQ: {}", productDeletedEvent);
        rabbitTemplate.convertAndSend("product-deleted", productDeletedEvent);
    }
}
