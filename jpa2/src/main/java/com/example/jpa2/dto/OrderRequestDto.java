package com.example.jpa2.dto;

import com.example.jpa2.entity.Status;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
public class OrderRequestDto {
    private Status status;
    private UUID clientId;
}
