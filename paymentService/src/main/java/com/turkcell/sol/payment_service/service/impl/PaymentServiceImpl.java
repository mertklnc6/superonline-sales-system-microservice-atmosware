package com.turkcell.sol.payment_service.service.impl;

import com.turkcell.sol.PaymentRequest;
import com.turkcell.sol.PaymentResponse;
import com.turkcell.sol.PaymentServiceGrpc;
import com.turkcell.sol.payment_service.model.Payment;
import com.turkcell.sol.payment_service.repository.PaymentRepository;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class PaymentServiceImpl extends PaymentServiceGrpc.PaymentServiceImplBase {

    private final PaymentRepository paymentRepository;

    @Override
    public void processPayment(PaymentRequest request, StreamObserver<PaymentResponse> responseObserver) {

        boolean paymentSuccess = request.getSuccess();
        String message = paymentSuccess ? "Payment successful" : "Payment failed";

        Payment payment = new Payment( request.getOrderId(), request.getSuccess(),request.getAmount());
        paymentRepository.save(payment);

        PaymentResponse response = PaymentResponse.newBuilder()
                .setSuccess(paymentSuccess)
                .setTransactionId(payment.getId().toString())
                .setMessage(message)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
