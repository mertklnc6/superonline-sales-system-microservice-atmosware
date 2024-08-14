package com.turkcell.sol.order_service.dto.payment;

import org.springframework.beans.factory.annotation.Value;

public record PaymentDTO(

    String paymentMethod,

    Object paymentDetails,

    boolean success
) {
}
