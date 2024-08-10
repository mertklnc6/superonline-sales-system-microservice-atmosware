package com.turkcell.sol.catalog_service.service.rules;

import com.turkcell.sol.catalog_service.constant.Messages;
import com.turkcell.sol.catalog_service.model.Product;
import com.turkcell.sol.catalog_service.repository.ProductRepository;
import com.turkcell.sol.catalog_service.util.MessageService;
import com.turkcell.sol.core.shared.exceptions.types.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductBusinessRules {

    private final ProductRepository productRepository;
    private final MessageService messageService;

    public void productShouldBeExist(Optional<Product> product){
        if(product.isEmpty()){
            throw new BusinessException(messageService.getMessage(Messages.ProductMessages.NOT_FOUND));
        }
    }
}
