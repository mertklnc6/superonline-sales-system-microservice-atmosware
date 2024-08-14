package com.tukcell.sol.notification_service.service.rules;

import com.tukcell.sol.notification_service.constant.Messages;
import com.tukcell.sol.notification_service.model.OrderNotification;
import com.tukcell.sol.notification_service.repository.OrderNotificationRepository;
import com.tukcell.sol.notification_service.shared.exceptions.types.BusinessException;
import com.tukcell.sol.notification_service.util.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderNotificationBusinessRules {

    private final OrderNotificationRepository orderNotificationRepository;
    private final MessageService messageService;

    public void productShouldBeExist(Optional<OrderNotification> orderNotification){
        if(orderNotification.isEmpty()){
            throw new BusinessException(messageService.getMessage(Messages.OrderNotificationMessages.NOT_FOUND));
        }
    }
}
