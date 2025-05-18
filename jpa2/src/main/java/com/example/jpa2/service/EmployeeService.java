package com.example.jpa2.service;

import com.example.jpa2.EmployeeSpecification;
import com.example.jpa2.dto.EmployeeRequestDto;
import com.example.jpa2.dto.EmployeeResponseDto;
import com.example.jpa2.entity.EmployeeEntity;
import com.example.jpa2.entity.Roles;
import com.example.jpa2.exception.EmployeeAlreadyExistsException;
import com.example.jpa2.exception.EmployeeNotFoundException;
import com.example.jpa2.exception.InvalidPasswordException;
import com.example.jpa2.mapper.EmployeeMapper;
import com.example.jpa2.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.jpa2.mapper.EmployeeMapper.mapToEntity;
import static com.example.jpa2.mapper.EmployeeMapper.mapToResponse;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeResponseDto addEmployee(EmployeeRequestDto dto) {
        EmployeeEntity employee = mapToEntity(dto);
        if (employeeRepository.existsByEmail(dto.getEmail())) {
            throw new EmployeeAlreadyExistsException("Сотрудник с таким email уже существует");
        }
        if (dto.getPassword().length() < 6) {
            throw new InvalidPasswordException("Пароль должен содержать минимум 6 символов");

        }
        EmployeeEntity entity = employeeRepository.save(employee);
        return mapToResponse(entity);
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
                .map(EmployeeMapper::mapToResponse);
    }

    public EmployeeResponseDto updateEmployee( UUID id,  EmployeeRequestDto dto) {
        EmployeeEntity employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник с таким id не найден"));
        employee.setName(dto.getName());
        employee.setSurname(dto.getSurname());
        employee.setEmail(dto.getEmail());
        employee.setPassword(dto.getPassword());
        employee.setRole(dto.getRole());
        return mapToResponse(employeeRepository.save(employee));
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
        return mapToResponse(employeeOptional.get());
    }
}
