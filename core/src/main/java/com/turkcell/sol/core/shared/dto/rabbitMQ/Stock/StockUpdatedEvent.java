package com.turkcell.sol.core.shared.dto.rabbitMQ.Stock;

public class StockUpdatedEvent {
    private String id;
    private int stock;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
