package com.turkcell.sol.order_service.service;

import com.turkcell.sol.order_service.dto.OrderItemDto;
import com.turkcell.sol.order_service.model.Order;
import com.turkcell.sol.order_service.model.OrderItem;

import java.util.List;

public interface OrderItemService {
    void add(List<OrderItem> orderItemList);
    void update(List<OrderItem> orderItemList);
}
