package com.example.jpa2.mapperInterf;

import com.example.jpa2.dto.EmployeeRequestDto;
import com.example.jpa2.dto.EmployeeResponseDto;
import com.example.jpa2.entity.EmployeeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper1 {
    EmployeeEntity toEntity(EmployeeRequestDto employeeRequestDto);
    EmployeeResponseDto toDto(EmployeeEntity employeeEntity);
}
