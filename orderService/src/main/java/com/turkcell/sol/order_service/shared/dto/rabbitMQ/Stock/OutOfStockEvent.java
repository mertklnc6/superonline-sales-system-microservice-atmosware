package com.turkcell.sol.order_service.shared.dto.rabbitMQ.Stock;

public class OutOfStockEvent {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
