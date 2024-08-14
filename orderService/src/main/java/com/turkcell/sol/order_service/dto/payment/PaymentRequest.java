package com.turkcell.sol.order_service.dto.payment;

import org.springframework.beans.factory.annotation.Value;

public record PaymentRequest(
    String orderId,

    double totalPrice,

    @Value("CREDIT_CARD")
    String paymentMethod,

    Object paymentDetails,

    boolean success
) {
}
