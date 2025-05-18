package com.example.jpa2.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProductWithQuantity {
    private UUID productId;
    private String productName;
    private int quantity;

}
