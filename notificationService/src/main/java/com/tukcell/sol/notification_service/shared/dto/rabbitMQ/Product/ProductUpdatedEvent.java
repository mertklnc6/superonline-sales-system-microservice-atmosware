package com.tukcell.sol.notification_service.shared.dto.rabbitMQ.Product;

public class ProductUpdatedEvent {
    private String id;
    private String name;
    private int stock;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
