package com.turkcell.sol.payment_service.dto;

public record BankTransferPaymentDetails(
        String customerName,
        String transactionNumber) {
}
