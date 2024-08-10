package com.turkcell.sol.catalog_service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class ProductCache implements Serializable {
    private String id;
    private String name;
    private String description;
    private String price;
}
