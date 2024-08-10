package com.turkcell.sol.stock_service.util.rabbitMQ.sender;

import com.turkcell.sol.core.shared.dto.rabbitMQ.Stock.StockUpdatedEvent;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockSender {

    private final RabbitTemplate rabbitTemplate;
    private static final Logger logger = LoggerFactory.getLogger(StockSender.class);

    public void send(StockUpdatedEvent stockUpdatedEvent) {

        logger.info("Stock updated event sent to RabbitMQ: {}", stockUpdatedEvent);
        rabbitTemplate.convertAndSend("stock-updated", stockUpdatedEvent);
    }
}
