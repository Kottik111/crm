package com.example.jpa2.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ClientResponseDto {
    private UUID id;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private List<OrderResponseDto> order;
}
