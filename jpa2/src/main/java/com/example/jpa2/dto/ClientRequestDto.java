package com.example.jpa2.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientRequestDto {
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
}
