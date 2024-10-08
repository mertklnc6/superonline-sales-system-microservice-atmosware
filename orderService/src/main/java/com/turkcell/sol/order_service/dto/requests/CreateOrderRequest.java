package com.turkcell.sol.order_service.dto.requests;

import com.turkcell.sol.order_service.dto.OrderItemDto;
import com.turkcell.sol.order_service.dto.payment.PaymentDTO;

import java.util.List;

public record CreateOrderRequest(
        String customerName,
        String address,
        List<OrderItemDto> items,
        PaymentDTO paymentRequest
) {
}
