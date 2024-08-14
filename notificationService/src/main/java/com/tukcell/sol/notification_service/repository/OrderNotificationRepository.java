package com.tukcell.sol.notification_service.repository;

import com.tukcell.sol.notification_service.model.OrderNotification;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface OrderNotificationRepository extends MongoRepository<OrderNotification, String> {
    List<OrderNotification> findAllByOrderId(String id);
}
