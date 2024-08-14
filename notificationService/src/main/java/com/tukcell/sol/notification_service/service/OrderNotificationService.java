package com.tukcell.sol.notification_service.service;

import com.tukcell.sol.notification_service.dto.GetOrderNotificationResponse;
import com.tukcell.sol.notification_service.model.OrderNotification;

import java.util.List;

public interface OrderNotificationService {
    void add(OrderNotification orderNotification);
    List<GetOrderNotificationResponse> getByOrderId(String id);
}
