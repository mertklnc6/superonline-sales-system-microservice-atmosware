package com.turkcell.sol.order_service.service;

import com.turkcell.sol.order_service.dto.requests.CreateOrderRequest;
import com.turkcell.sol.order_service.dto.requests.UpdateOrderRequest;
import com.turkcell.sol.order_service.dto.responses.CreatedOrderResponse;
import com.turkcell.sol.order_service.dto.responses.DeletedOrderResponse;
import com.turkcell.sol.order_service.dto.responses.GetOrderResponse;
import com.turkcell.sol.order_service.dto.responses.UpdatedOrderResponse;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    CreatedOrderResponse create(CreateOrderRequest createOrderRequest);
    GetOrderResponse getById(UUID id);
    List<GetOrderResponse> getAll();
    DeletedOrderResponse delete(UUID id);
}
