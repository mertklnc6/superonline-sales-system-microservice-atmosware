package com.tukcell.sol.notification_service.service.impl;

import com.tukcell.sol.notification_service.dto.GetOrderNotificationResponse;
import com.tukcell.sol.notification_service.model.OrderNotification;
import com.tukcell.sol.notification_service.repository.OrderNotificationRepository;
import com.tukcell.sol.notification_service.service.OrderNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderNotificationServiceImpl implements OrderNotificationService {

    private final OrderNotificationRepository orderNotificationRepository;

    @Override
    public void add(OrderNotification orderNotification) {
        orderNotificationRepository.save(orderNotification);
    }

    @Override
    public List<GetOrderNotificationResponse> getByOrderId(String id) {
        List<OrderNotification> orderNotificationList = orderNotificationRepository.findAllByOrderId(id);

        return orderNotificationList.stream()
                .map(orderNotification -> new GetOrderNotificationResponse(
                        orderNotification.getId(),
                        orderNotification.getOrderId(),
                        orderNotification.getMessage(),
                        orderNotification.getCreatedDate()
                ))
                .collect(Collectors.toList());
    }

}
