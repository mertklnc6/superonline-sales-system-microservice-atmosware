package com.turkcell.sol.payment_service.dto;

public record CreditCardPaymentDetails(
        String cardNumber,
        String cardHolderName,
        String expiryDate,
        String cvv) {
}
