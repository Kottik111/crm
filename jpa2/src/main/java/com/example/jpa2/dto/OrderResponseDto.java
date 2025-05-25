package com.example.jpa2.dto;

import com.example.jpa2.entity.Status;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class OrderResponseDto {
    private UUID id;
    private Status status;
    private LocalDate date;
    private String clientInfo;
    private List<ProductWithQuantity> products;
}
