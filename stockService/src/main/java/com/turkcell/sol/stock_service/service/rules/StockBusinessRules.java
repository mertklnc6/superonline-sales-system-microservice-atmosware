package com.turkcell.sol.stock_service.service.rules;

import com.turkcell.sol.stock_service.constant.Messages;
import com.turkcell.sol.stock_service.model.ProductStock;
import com.turkcell.sol.stock_service.repository.StockRepository;
import com.turkcell.sol.stock_service.shared.exceptions.types.BusinessException;
import com.turkcell.sol.stock_service.util.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockBusinessRules {

    private final StockRepository productRepository;
    private final MessageService messageService;

    public void stockShouldBeExist(Optional<ProductStock> productStock){
        if(productStock.isEmpty()){
            throw new BusinessException(messageService.getMessage(Messages.StockMessages.NOT_FOUND));
        }
    }

    public void stockShouldBeInStock(ProductStock productStock){
        if(productStock.getStock() == 0){
            throw new BusinessException(messageService.getMessage(Messages.StockMessages.OUT_OF_STOCK));
        }
    }

    public void stockShouldNotBeLessThanZero(ProductStock productStock){
        if(productStock.getStock() < 0){
            throw new BusinessException(messageService.getMessage(Messages.StockMessages.NOT_ENOUGH_STOCK));
        }
    }
}
