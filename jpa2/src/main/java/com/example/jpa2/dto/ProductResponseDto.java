package com.example.jpa2.dto;



import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@EqualsAndHashCode
@Getter
@Setter
public class ProductResponseDto {
    private UUID id;
    private String name;
    private String description;
    private double price;
}
