package com.turkcell.sol.order_service.service.impl;

import com.turkcell.sol.order_service.controller.client.CatalogClient;
import com.turkcell.sol.order_service.controller.client.StockClient;
import com.turkcell.sol.order_service.model.Order;
import com.turkcell.sol.order_service.model.OrderItem;
import com.turkcell.sol.order_service.repository.OrderItemRepository;
import com.turkcell.sol.order_service.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final CatalogClient catalogClient;
    private final StockClient stockClient;

    @Override
    public void add(List<OrderItem> orderItemList, Order order) {

        for(OrderItem orderItem : orderItemList){
            catalogClient.getByIdProduct(orderItem.getProductId());
            if(catalogClient.getStockInfoById(orderItem.getProductId())){
                stockClient.getByProductId(orderItem.getProductId());
            }
            orderItem.setOrder(order);
            orderItemRepository.save(orderItem);
        }
    }

    @Override
    public void update(List<OrderItem> orderItemList) {
        orderItemRepository.saveAll(orderItemList);
    }
}
