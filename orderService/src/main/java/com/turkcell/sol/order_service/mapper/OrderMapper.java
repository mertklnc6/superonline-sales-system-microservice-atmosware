package com.turkcell.sol.order_service.mapper;

import com.turkcell.sol.order_service.dto.requests.CreateOrderRequest;
import com.turkcell.sol.order_service.dto.responses.CreatedOrderResponse;
import com.turkcell.sol.order_service.dto.responses.DeletedOrderResponse;
import com.turkcell.sol.order_service.dto.responses.GetOrderResponse;
import com.turkcell.sol.order_service.model.Order;
import com.turkcell.sol.order_service.shared.mapping.MapstructService;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = MapstructService.class)
public interface OrderMapper {

    CreatedOrderResponse toCreatedOrderResponse(Order order);
    Order toOrder(CreateOrderRequest createOrderRequest);
    GetOrderResponse toGetOrderResponse(Order order);
    List<GetOrderResponse> toGetOrderResponse(List<Order> orderList);
    DeletedOrderResponse toDeletedOrderResponse(Order order);
}
