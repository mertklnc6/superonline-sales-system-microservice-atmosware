package com.turkcell.sol.payment_service.service;

import com.turkcell.sol.payment_service.dto.request.PaymentRequest;
import com.turkcell.sol.payment_service.dto.response.PaymentResponse;

public interface PaymentStrategy {
    PaymentResponse pay(PaymentRequest paymentRequest);
}
