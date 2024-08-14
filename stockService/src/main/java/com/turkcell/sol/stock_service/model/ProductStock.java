package com.turkcell.sol.stock_service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "product_stocks")
public class ProductStock {
    @Id
    @Field("_id")
    private String id;
    private String productId;
    private int stock;
    private LocalDateTime createdDate;
}

