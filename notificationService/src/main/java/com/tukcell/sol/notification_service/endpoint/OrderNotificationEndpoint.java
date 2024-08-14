package com.tukcell.sol.notification_service.endpoint;

import com.tukcell.sol.notification_service.dto.GetOrderNotificationResponse;
import com.tukcell.sol.notification_service.dto.request.GetOrderNotificationRequest;
import com.tukcell.sol.notification_service.service.OrderNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
@RequiredArgsConstructor
public class OrderNotificationEndpoint {

    private static final String NAMESPACE_URI = "http:localhost:7006/notification_service";

    private final OrderNotificationService orderNotificationService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getOrderNotificationRequest")
    @ResponsePayload
    public List<GetOrderNotificationResponse> getOrderNotification(@RequestPayload GetOrderNotificationRequest request) {
        return orderNotificationService.getByOrderId(request.orderId());
    }
}

