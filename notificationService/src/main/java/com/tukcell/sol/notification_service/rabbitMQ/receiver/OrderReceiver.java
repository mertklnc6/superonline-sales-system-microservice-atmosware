package com.tukcell.sol.notification_service.rabbitMQ.receiver;

import com.tukcell.sol.notification_service.model.OrderNotification;
import com.tukcell.sol.notification_service.service.OrderNotificationService;
import com.tukcell.sol.notification_service.shared.dto.rabbitMQ.Order.OrderNotificationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderReceiver {

    private final OrderNotificationService orderNotificationService;

    @RabbitListener(queues = "order-notification", group = "notification.group")
    public void consume(OrderNotificationEvent orderNotificationEvent) {
        OrderNotification orderNotification = new OrderNotification();
        orderNotification.setOrderId(orderNotificationEvent.id());
        orderNotification.setMessage(orderNotificationEvent.message());
        orderNotification.setCreatedDate(LocalDateTime.now());
        orderNotificationService.add(orderNotification);
    }
}
