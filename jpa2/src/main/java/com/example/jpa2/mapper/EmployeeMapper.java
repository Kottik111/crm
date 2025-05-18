package com.example.jpa2.mapper;

import com.example.jpa2.dto.EmployeeRequestDto;
import com.example.jpa2.dto.EmployeeResponseDto;
import com.example.jpa2.entity.EmployeeEntity;

public class EmployeeMapper {
    public static EmployeeResponseDto mapToResponse(EmployeeEntity entity){
        EmployeeResponseDto dto = new EmployeeResponseDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setRole(entity.getRole());
        return dto;
    }

    public static EmployeeEntity mapToEntity(EmployeeRequestDto dto){
        EmployeeEntity entity = new EmployeeEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setRole(dto.getRole());
        return entity;
    }
}
