package com.turkcell.sol.order_service.repository;

import com.turkcell.sol.order_service.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
