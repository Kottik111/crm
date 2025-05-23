package com.example.jpa2.dto;

import com.example.jpa2.entity.Roles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponseDto {
    private UUID id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Roles role;
}
