package com.turkcell.sol.payment_service.dto.request;

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
