package com.turkcell.sol.payment_service.service;

import com.turkcell.sol.payment_service.service.impl.PaymentServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GrpcServer {
    private final Server server;

    public GrpcServer(PaymentServiceImpl paymentService) {
        this.server = ServerBuilder.forPort(9090)
                .addService(paymentService)
                .build();
    }

    public void start() throws IOException {
        server.start();
        Runtime.getRuntime().addShutdownHook(new Thread(server::shutdown));
    }

    public void blockUntilShutdown() throws InterruptedException {
        server.awaitTermination();
    }
}

