package com.example.jpa2.controller;

import com.example.jpa2.dto.EmployeeRequestDto;
import com.example.jpa2.dto.EmployeeResponseDto;
import com.example.jpa2.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("/employees")
    public EmployeeResponseDto addEmployee(@RequestBody EmployeeRequestDto newEmployee){
       return employeeService.addEmployee(newEmployee);
    }

    @GetMapping("/employees")
    public Page<EmployeeResponseDto> getEmployee(@RequestParam(required = false) String name,
                                                 @RequestParam(required = false) String surname,
                                                 @RequestParam(required = false) String email,
                                                 @RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "5") int size,
                                                 @RequestParam(defaultValue = "name") String sortField,
                                                 @RequestParam(defaultValue = "asc") String sortDirection

    ){
        Sort.Direction direction = sortDirection.equalsIgnoreCase("asc")
                ? Sort.Direction.ASC
                : Sort.Direction.DESC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortField));
        return employeeService.getAllEmployees(name, surname, email, pageable);
    }

    @PutMapping("/employee/{id}")
    public EmployeeResponseDto updateEmployee(@PathVariable UUID id, @RequestBody EmployeeRequestDto newEmployee){
        return employeeService.updateEmployee(id, newEmployee);
    }

    @DeleteMapping(("/employee/{id}"))
    public void deleteEmployee(@PathVariable UUID id){
        employeeService.deleteEmployee(id);
    }

    @GetMapping("/employee/{id}")
    public EmployeeResponseDto getEmployeeById(@PathVariable UUID id){
        return employeeService.getEmployeeById(id);
    }

}
