package com.example.jpa2.service;

import com.example.jpa2.ClientSpecification;
import com.example.jpa2.dto.ClientRequestDto;
import com.example.jpa2.dto.ClientResponseDto;
import com.example.jpa2.dto.EmployeeRequestDto;
import com.example.jpa2.entity.ClientEntity;
import com.example.jpa2.exception.ClientAlreadyExistsException;
import com.example.jpa2.exception.ClientNotFoundException;
import com.example.jpa2.mapper.ClientMapper;
import com.example.jpa2.repository.ClientRepository;
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

import static com.example.jpa2.mapper.ClientMapper.mapToEntity;
import static com.example.jpa2.mapper.ClientMapper.mapToResponse;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientResponseDto addClient(ClientRequestDto dto){
        ClientEntity client= mapToEntity(dto);
        if (clientRepository.existsByEmail(dto.getEmail())) {
            throw new ClientAlreadyExistsException("Клиент с таким email уже существует");
        }
        clientRepository.save(client);
        return mapToResponse(client);
    }

    public ClientResponseDto updateClient( UUID id, ClientRequestDto dto) {
        Optional<ClientEntity> client = clientRepository.findById(id);
        if (client.isEmpty()){
            throw new ClientNotFoundException("Клиент с таким id не найден");
        }
        client.get().setName(dto.getName());
        client.get().setSurname(dto.getSurname());
        client.get().setEmail(dto.getEmail());
        client.get().setPhoneNumber(dto.getPhoneNumber());
        return mapToResponse(clientRepository.save(client.get()));
    }

    public void deleteClient(UUID id){
        if (!clientRepository.existsById(id)) {
            throw new ClientNotFoundException("Клиент с таким id не найден");
        }
        clientRepository.deleteById(id);
    }

    public Page<ClientResponseDto> getAllClients(String name, String surname, String email, Pageable pageable) {
        Specification<ClientEntity> spec = Specification.where(
                ClientSpecification.hasName(name)
        ).and(
                ClientSpecification.hasSurname(surname)
        ).and(
                ClientSpecification.containsEmail(email)
        );
        return clientRepository.findAll(spec, pageable)
                .map(ClientMapper::mapToResponse);
    }

    public ClientResponseDto getClientById(UUID id){
        Optional<ClientEntity> client = clientRepository.findById(id);
        if (client.isEmpty()){
            throw new ClientNotFoundException("Клиент с таким id не найден");
        }
        return mapToResponse(client.get());
    }
}
