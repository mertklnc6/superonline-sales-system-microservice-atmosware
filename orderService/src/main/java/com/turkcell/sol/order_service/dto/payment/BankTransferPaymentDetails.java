package com.turkcell.sol.order_service.dto.payment;

public record BankTransferPaymentDetails(
        String customerName,
        String transactionNumber) {
}
