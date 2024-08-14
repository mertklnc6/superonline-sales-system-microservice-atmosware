package com.turkcell.sol.order_service.client;

import com.turkcell.sol.PaymentRequest;
import com.turkcell.sol.PaymentResponse;
import com.turkcell.sol.PaymentServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceClient {

    private final PaymentServiceGrpc.PaymentServiceBlockingStub blockingStub;

    public PaymentServiceClient() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();

        blockingStub = PaymentServiceGrpc.newBlockingStub(channel);
    }

    public PaymentResponse processPayment(PaymentRequest request) {
        return blockingStub.processPayment(request);
    }
}


