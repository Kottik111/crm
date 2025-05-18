package com.example.jpa2.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class OrderProductResponseDto {
    private UUID productId;
    private String name;
    private String description;
    private double price;
    private int quantity;
}
