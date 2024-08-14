package com.turkcell.sol.order_service.controller;

import com.turkcell.sol.order_service.dto.requests.CreateOrderRequest;
import com.turkcell.sol.order_service.dto.responses.CreatedOrderResponse;
import com.turkcell.sol.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/order-service/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public CreatedOrderResponse create(CreateOrderRequest createOrderRequest){
       return orderService.create(createOrderRequest);
    }
}
