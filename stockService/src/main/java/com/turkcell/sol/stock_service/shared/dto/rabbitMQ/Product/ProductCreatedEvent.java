package com.turkcell.sol.stock_service.shared.dto.rabbitMQ.Product;

public class ProductCreatedEvent {
    private String id;
    private String name;
    private int stock;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
