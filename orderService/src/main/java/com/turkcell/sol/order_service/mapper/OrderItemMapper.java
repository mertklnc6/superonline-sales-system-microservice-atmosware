package com.turkcell.sol.order_service.mapper;

import com.turkcell.sol.order_service.dto.OrderItemDto;
import com.turkcell.sol.order_service.dto.responses.GetProductResponse;
import com.turkcell.sol.order_service.model.OrderItem;
import com.turkcell.sol.order_service.shared.mapping.MapstructService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = MapstructService.class)
public interface OrderItemMapper {
    OrderItem toOrderItem(OrderItemDto orderItemDto);

//    @Mapping(target = "productName", source = "GetProductResponse.name")
//    OrderItem toOrderItem(GetProductResponse getProductResponse);
//
//    @Mapping(target = "productName", source = "GetProductResponse.name")
//    List<OrderItem> toOrderItem(List<GetProductResponse> getProductResponseList);
}

