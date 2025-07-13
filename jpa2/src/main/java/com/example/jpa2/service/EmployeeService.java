package com.example.jpa2.service;

import com.example.jpa2.specification.EmployeeSpecification;
import com.example.jpa2.dto.EmployeeRequestDto;
import com.example.jpa2.dto.EmployeeResponseDto;
import com.example.jpa2.entity.EmployeeEntity;
import com.example.jpa2.exception.EmployeeAlreadyExistsException;
import com.example.jpa2.exception.EmployeeNotFoundException;
import com.example.jpa2.exception.InvalidPasswordException;
import com.example.jpa2.mapperInterf.EmployeeMapper1;
import com.example.jpa2.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper1 employeeMapper;

    public EmployeeResponseDto addEmployee(EmployeeRequestDto dto) {
        EmployeeEntity employee = employeeMapper.toEntity(dto);
        if (employeeRepository.existsByEmail(dto.getEmail())) {
            throw new EmployeeAlreadyExistsException("Сотрудник с таким email уже существует");
        }
        if (dto.getPassword().length() < 6) {
            throw new InvalidPasswordException("Пароль должен содержать минимум 6 символов");
        }
        EmployeeEntity entity = employeeRepository.save(employee);
        return employeeMapper.toDto(entity);
    }

    public Page<EmployeeResponseDto> getAllEmployees(String name, String surname, String email, Pageable pageable) {
        Specification<EmployeeEntity> spec = Specification.where(
                EmployeeSpecification.hasName(name)
        ).and(
                EmployeeSpecification.hasSurname(surname)
        ).and(
                EmployeeSpecification.containsEmail(email)
        );
        return employeeRepository.findAll(spec, pageable)
                .map(employeeMapper::toDto);
    }

    public EmployeeResponseDto updateEmployee( UUID id,  EmployeeRequestDto dto) {
        EmployeeEntity employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник с таким id не найден"));
        employee.setName(dto.getName());
        employee.setSurname(dto.getSurname());
        employee.setEmail(dto.getEmail());
        employee.setPassword(dto.getPassword());
        employee.setRole(dto.getRole());
        return employeeMapper.toDto(employeeRepository.save(employee));
    }

    public void deleteEmployee( UUID id){
        if(!employeeRepository.existsById(id)){
            throw new EmployeeNotFoundException("Сотрудник не найден!");
        }
        employeeRepository.deleteById(id);
    }

    public EmployeeResponseDto getEmployeeById( UUID id){
        Optional<EmployeeEntity> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isEmpty()){
            throw new EmployeeNotFoundException("Сотрудник с таким id не найден");
        }
        return employeeMapper.toDto(employeeOptional.get());
    }
}
