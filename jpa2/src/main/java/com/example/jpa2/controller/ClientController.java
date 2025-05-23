package com.example.jpa2.controller;

import com.example.jpa2.dto.ClientRequestDto;
import com.example.jpa2.dto.ClientResponseDto;
import com.example.jpa2.service.ClientService;
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
public class ClientController {
    private final ClientService clientService;



    @PostMapping("/clients")
    public ClientResponseDto addClient(@RequestBody ClientRequestDto dto){
        return clientService.addClient(dto);
    }

    @GetMapping("/clients")
    public Page<ClientResponseDto> getAllClient(@RequestParam(required = false) String name,
                                                @RequestParam(required = false) String surname,
                                                @RequestParam(required = false) String email,
                                                @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "5") int size,
                                                @RequestParam(defaultValue = "name") String sortField,
                                                @RequestParam(defaultValue = "asc") String sortDirection
    ) {
        Sort.Direction direction = sortDirection.equals("asc")
                ? Sort.Direction.ASC
                : Sort.Direction.DESC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortField));
        return clientService.getAllClients(name, surname, email, pageable);
    }

    @PutMapping("/clients/{id}")
    public ClientResponseDto updateClient(@PathVariable UUID id, @RequestBody ClientRequestDto dto){
        return clientService.updateClient(id, dto);
    }

    @DeleteMapping("/clients/{id}")
    public void deleteClient(@PathVariable UUID id)  {
        clientService.deleteClient(id);
    }

    @GetMapping("/clients/{id}")
    public ClientResponseDto getClientById(@PathVariable UUID id){
        return clientService.getClientById(id);
    }
}
