package com.turkcell.sol.order_service.service.rules;

import com.turkcell.sol.order_service.model.Order;
import com.turkcell.sol.order_service.repository.OrderRepository;
import com.turkcell.sol.order_service.shared.exceptions.types.BusinessException;
import com.turkcell.sol.order_service.util.MessageService;
import com.turkcell.sol.stock_service.constant.Messages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderBusinessRules {

    private final OrderRepository orderRepository;
    private final MessageService messageService;

    public void productShouldBeExist(Optional<Order> Order){
        if(Order.isEmpty()){
            throw new BusinessException(messageService.getMessage(Messages.OrderMessages.NOT_FOUND));
        }
    }
}