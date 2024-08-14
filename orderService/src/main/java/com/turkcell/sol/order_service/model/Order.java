package com.turkcell.sol.order_service.model;

import com.turkcell.sol.order_service.model.Enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orders")
@SQLRestriction(value = "deleted_date is null")
public class Order extends BaseEntity<UUID> {

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "total_price", nullable = false)
    private double totalPrice;

    @Column(name = "order_number", unique = true)
    private String orderNumber;

    @Column(name = "status")
    private OrderStatus status;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> items;

    @PrePersist
    public void generateOrderNumber() {
        this.orderNumber = UUID.randomUUID().toString();
    }
}
