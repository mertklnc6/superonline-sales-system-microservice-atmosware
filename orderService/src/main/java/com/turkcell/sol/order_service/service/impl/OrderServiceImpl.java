package com.turkcell.sol.order_service.service.impl;

import com.turkcell.sol.order_service.dto.requests.CreateOrderRequest;
import com.turkcell.sol.order_service.dto.requests.UpdateOrderRequest;
import com.turkcell.sol.order_service.dto.responses.CreatedOrderResponse;
import com.turkcell.sol.order_service.dto.responses.DeletedOrderResponse;
import com.turkcell.sol.order_service.dto.responses.GetOrderResponse;
import com.turkcell.sol.order_service.dto.responses.UpdatedOrderResponse;
import com.turkcell.sol.order_service.service.OrderService;

import java.util.List;
import java.util.UUID;

public class OrderServiceImpl implements OrderService {
    @Override
    public CreatedOrderResponse create(CreateOrderRequest createOrderRequest) {
        return null;
    }

    @Override
    public GetOrderResponse getById(UUID id) {
        return null;
    }

    @Override
    public List<GetOrderResponse> getAll() {
        return List.of();
    }

    @Override
    public UpdatedOrderResponse update(UpdateOrderRequest updateOrderRequest) {
        return null;
    }

    @Override
    public DeletedOrderResponse delete(UUID id) {
        return null;
    }
}
