package com.turkcell.sol.payment_service.model;

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
@Table(name = "payments")
@SQLRestriction(value = "deleted_date is null")
public class Payment extends BaseEntity<UUID> {

    @Column(name = "order_number", nullable = false)
    private String orderNumber;

    @Column(name = "is_success", nullable = false)
    private boolean isSuccess;

    @Column(name = "total_price", nullable = false)
    private double totalPrice;
}
