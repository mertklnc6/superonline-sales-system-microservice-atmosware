package com.turkcell.sol.order_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "order_items")
@SQLRestriction(value = "deleted_date is null")
public class OrderItem extends BaseEntity<Integer> {

    @Column(name = "product_id", nullable = false)
    private String productId;

    @Column(name="product_name",nullable = false)
    private String productName;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name="quantity", nullable = false)
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
}
