package com.example.jpa2.dto;

import com.example.jpa2.entity.Roles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.management.relation.Role;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequestDto {
    private String name;
    private String surname;
    private String email;
    private String password;
    private Roles role;
}
