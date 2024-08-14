package com.turkcell.sol.order_service.dto.payment;

public record CreditCardPaymentDetails(
        String cardNumber,
        String cardHolderName,
        String expiryDate,
        String cvv) {
}
