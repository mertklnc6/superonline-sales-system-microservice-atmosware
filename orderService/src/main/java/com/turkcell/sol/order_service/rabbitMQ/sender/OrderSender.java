package com.turkcell.sol.order_service.rabbitMQ.sender;

import com.turkcell.sol.order_service.shared.dto.rabbitMQ.Order.OrderNotificationEvent;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderSender {

    private final RabbitTemplate rabbitTemplate;
    private static final Logger logger = LoggerFactory.getLogger(OrderSender.class);

    public void send(OrderNotificationEvent orderNotificationEvent) {

        logger.info("Product created event sent to RabbitMQ: {}", orderNotificationEvent);
        rabbitTemplate.convertAndSend("order-notification", orderNotificationEvent);
    }
}
