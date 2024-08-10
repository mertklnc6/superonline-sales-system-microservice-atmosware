package com.turkcell.sol.order_service.dto.requests;

import com.turkcell.sol.order_service.dto.OrderItemDTO;

import java.util.List;
import java.util.UUID;

public record CreateOrderRequest(
        String customerName,
        String address,
        List<OrderItemDTO> items
) {
}
